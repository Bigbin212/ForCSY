package com.csy.module.wx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csy.module.wx.bean.result.WxAccessToken;
import com.csy.module.wx.bean.result.WxError;
import com.csy.module.wx.entity.BWxUser;
import com.csy.module.wx.service.service.BWxUserService;
import com.csy.util.wx.AccessTokenUtil;

@Controller
@RequestMapping("/wx")
public class KckpController {
	
	@Autowired
	private BWxUserService wxUserService;
	
	/**
	 * 首先进入静默授权页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView baseAuthorize(HttpServletRequest request, 
			HttpServletResponse response){
		return new ModelAndView("/weixin/baseAuthorize");
	}
	
	@RequestMapping("/kckpTest")
	public ModelAndView kckpTest(HttpServletRequest request,
			HttpServletResponse response){
		HttpSession session = request.getSession(true);
		if(session.getAttribute("bWxUser") == null){
			
		}else{
			BWxUser bWxUser = wxUserService.selectByPrimaryKey("orbiHuLPLAwip8l4fl2C-kmTexSg");
			request.getSession(true).setAttribute("bWxUser", bWxUser);
		}
		return new ModelAndView("/weixin/kckp");
	}
	
	/**
	 * 授权成功后，session中bWxUser存放该微信对象
	 * @param request
	 * @param response
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping("/kckp")
	public ModelAndView kckp(HttpServletRequest request, 
			HttpServletResponse response, String code, String state){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession(true);
		
		BWxUser wxUser = (BWxUser)session.getAttribute("bWxUser");
		//已经静默授权过一次了成功，也就是只在session重新获取才进行用户更新操作。
		if(wxUser != null){
			//正常进入逻辑页面
			return new ModelAndView("/weixin/kckp", map);
		}
		//未静默授权成功
		else{
			Object object = AccessTokenUtil.getOAuth2AccessToken(code);
			//如果验证正常
			if(object instanceof WxAccessToken){
				WxAccessToken accessToken = (WxAccessToken)object;
				Object object2 = AccessTokenUtil.getWxUserInfo(accessToken);
				//如果用户信息获取失败
				if(object2 instanceof WxError){
					WxError wxError = (WxError)object2;
					//如果授权过期或者未授权
					if(42001 == wxError.getErrcode() || 48001 == wxError.getErrcode()){
						return new ModelAndView("/weixin/userinfoAuthorize");
					}else{
						return null;
					}
				}
				//如果成功获取到用户信息，则新增或者更新用户信息
				else{
					BWxUser wxUserUpdated = (BWxUser)object2;
					wxUser = wxUserService.selectByPrimaryKey(accessToken.getOpenid());
					//如果从未存储过该用户,新增该用户
					if(wxUser==null){
						wxUserService.insert(wxUserUpdated);
					}
					//如果用户已经存在，更新用户信息
					else{
						wxUserService.updateByPrimaryKey(wxUserUpdated);
					}
					session.setAttribute("bWxUser", wxUserUpdated);
				}
				return new ModelAndView("/weixin/kckp", map);
			}
			//验证不通过直接抛回原来的页面吧
			else{
				WxError wxError = (WxError)object;
				//如果收code过期,或者已近被使用, 抛回静默授权页面
				if(42003 == wxError.getErrcode() || 40163 == wxError.getErrcode()
						|| 40029 == wxError.getErrcode()){
					return new ModelAndView("/weixin/baseAuthorize");
				}else{
					response.setStatus(500);
					return null;
				}
				
			}
		}
	}
}
