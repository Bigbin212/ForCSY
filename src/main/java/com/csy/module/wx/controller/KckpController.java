package com.csy.module.wx.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csy.module.wx.bean.result.WxAccessToken;
import com.csy.module.wx.bean.result.WxError;
import com.csy.module.wx.dto.KckpUploadInfo;
import com.csy.module.wx.entity.BWxUser;
import com.csy.module.wx.service.service.BAccidentInfoService;
import com.csy.module.wx.service.service.BWxUserService;
import com.csy.util.XtpzUtil;
import com.csy.util.wx.AccessTokenUtil;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wx")
public class KckpController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final int PAGE_INDEX = 1;
	private static final int PAGE_SIZE = 5;
	@Autowired
	private BWxUserService wxUserService;
	@Autowired
	private BAccidentInfoService accidentService;
	
	/**
	 * 微信主界面
	 * 授权成功后，session中bWxUser存放该微信对象
	 * @param request
	 * @param response
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView kckp(HttpServletRequest request, 
			HttpServletResponse response, String code, String state){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession(true);
		
		BWxUser wxUser = (BWxUser)session.getAttribute("bWxUser");
		//已经静默授权过一次了成功，也就是只在session重新获取才进行用户更新操作。
		if(wxUser != null){
			//正常进入逻辑页面
			return new ModelAndView("/weixin/index", map);
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
				return new ModelAndView("/weixin/index", map);
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
	
	@RequestMapping("/kckp")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response){
		return new ModelAndView("/weixin/kckp");
	}
	
	/**
	 * 首先进入静默授权页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/baseAuthorize")
	public ModelAndView baseAuthorize(HttpServletRequest request, 
			HttpServletResponse response){
		return new ModelAndView("/weixin/baseAuthorize");
	}
	
	@RequestMapping("/kckpTest")
	public ModelAndView kckpTest(HttpServletRequest request,
			HttpServletResponse response){
		HttpSession session = request.getSession(true);
		if(session.getAttribute("bWxUser") != null){
			
		}else{
			BWxUser bWxUser = wxUserService.selectByPrimaryKey("orbiHuLPLAwip8l4fl2C-kmTexSg");
			request.getSession(true).setAttribute("bWxUser", bWxUser);
		}
		return new ModelAndView("/weixin/kckp");
	}
	
	/**
	 * 微信主界面
	 * @return
	 */
	@RequestMapping("/myRecord")
	public ModelAndView kckpIndex(HttpServletRequest request,HttpServletResponse response, ModelMap map){
		map.put("wxResource", XtpzUtil.getXtpzByName("wxResource").getVal());
		return new ModelAndView("/weixin/myRecord");
	}
	
	@RequestMapping("/getKckpPageInfo")
	public void getKckpPageInfo(HttpServletRequest request, HttpServletResponse response, Integer pageNum) throws IOException{
		HttpSession session = request.getSession(true);
		BWxUser wxUser = (BWxUser)session.getAttribute("bWxUser");
		if(pageNum==null) pageNum = PAGE_INDEX;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fk_wx_openid", wxUser.getOpenid());
		PageInfo page = accidentService.selectPageInfo(pageNum, PAGE_SIZE, map);
		response.getWriter().print(JSONObject.fromObject(page));
	}
	
	
	
	@RequestMapping("/addKckpInfo")
	public void addKckpInfo( HttpServletRequest request, HttpServletResponse response, 
			@RequestBody KckpUploadInfo uploadInfo) throws IOException{
		//返回的json数据
		JSONObject rst = null;
		BWxUser wxUser = (BWxUser)request.getSession(true).getAttribute("bWxUser");
		if(wxUser==null){
			rst = new JSONObject();
			rst.put("errMsg", "未抓取到上传用户信息");
		}else{
			try {
				//额外的事件信息
				uploadInfo.setUploadTime(new Date());
				uploadInfo.setFkWxOpenid(wxUser.getOpenid());
				
				rst = accidentService.insertAccident(uploadInfo);
			} catch (Exception e) {
				rst = new JSONObject();
				rst.put("errMsg", e.getMessage());
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		response.getWriter().print(rst);
	};
	
	
}
