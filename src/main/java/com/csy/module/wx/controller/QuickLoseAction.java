package com.csy.module.wx.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csy.module.wx.dto.DriverAccident;
import com.csy.module.wx.entity.BWxFkyj;
import com.csy.module.wx.service.service.BAccidentInfoService;
import com.csy.module.wx.service.service.BWxFkyjService;
import com.csy.util.JSONUtil;
import com.csy.util.TimeFormatUtil;

@Controller
public class QuickLoseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private BAccidentInfoService bAccidentInfoService;
	
	@Resource
	private BWxFkyjService bWxFkyjService;
	/**
	 * 说明:快处快赔查询页面
	 * 创建时间：2017-02-20 09:00
	 * @author wangyonghui
	 * @return
	 */
	@RequestMapping("/lose")
	public ModelAndView register(String param){
		  Map<String, Object> paramsMap  = new HashMap<String, Object>();
		  Calendar calendar = Calendar.getInstance();
		  String formatTime = TimeFormatUtil.timeToStr(calendar, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		  calendar.add(Calendar.DATE, - 7);  
		  String formatTime1 = TimeFormatUtil.timeToStr(calendar, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		  paramsMap.put("startTime", formatTime1);
		  paramsMap.put("endTime", formatTime);
		  paramsMap.put("param", param);
		  return new ModelAndView("lose/kckpcx",paramsMap);
	}
	/**
	 * 说明：根据事故发生时间、微信号、号牌号码去查询驾驶员、事故、微信用户信息
	 * 创建时间：2017-02-21 15:00
	 * @author wangyonghui
	 */
	@RequestMapping("/kckpcx")
	public void findByParam(HttpServletRequest request, HttpServletResponse response,DriverAccident accident){
		List<DriverAccident> list = new ArrayList<DriverAccident>();
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("error", "");
	    try {
	    	list = bAccidentInfoService.selectByExample(accident);
	    } catch (Exception e) {
	      jsonObject.put("error", e);
	      JSONUtil.writeJSONObjectToResponse(response, jsonObject);
	      e.printStackTrace();
	    }
	    jsonObject.put("data", list);
	    JSONUtil.writeJSONObjectToResponse(response, jsonObject);
	}
	/**
	 * 说明:反馈意见页面
	 * 创建时间：2017-03-13 10:00
	 * @author wangyonghui
	 * @return
	 */
	@RequestMapping("/fkyj")
	public ModelAndView queryFkyj(String param){
		 Map<String, Object> paramsMap  = new HashMap<String, Object>();
		  Calendar calendar = Calendar.getInstance();
		  String formatTime = TimeFormatUtil.timeToStr(calendar, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		  calendar.add(Calendar.DATE, - 7);  
		  String formatTime1 = TimeFormatUtil.timeToStr(calendar, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		  paramsMap.put("startTime", formatTime1);
		  paramsMap.put("endTime", formatTime);
		  return new ModelAndView("lose/fkyjcx",paramsMap);
	}
	/**
	 * 说明：根据条件查询反馈意见内容信息
	 * @param request
	 * @param response
	 * @param bwfkyj
	 * @author wangyonghui
	 * @since 2017-03-13 15:12
	 */
	@RequestMapping("/fkyjcx")
	public void fkyjcx(HttpServletRequest request, HttpServletResponse response,BWxFkyj bwfkyj){
		List<BWxFkyj> list = new ArrayList<BWxFkyj>();
	    JSONObject jsonObject = new JSONObject();
	    String page = request.getParameter("page");// 当前页
		String rows = request.getParameter("rows");// 每页显示数目
		int pageNum = 1;
		int pageSize = 10;
		int total  = 0;		
		if (page != null) {
			pageNum = Integer.parseInt(page);
		}
		if (rows != null) {
			pageSize = Integer.parseInt(rows);
		}
	    jsonObject.put("error", "");
	    RowBounds rowBounds = new RowBounds((pageNum-1)*pageSize,pageSize);
	    bwfkyj.setDescription(bwfkyj.getKssj());
	    bwfkyj.setImage(bwfkyj.getJssj());
	    bwfkyj.setId(bwfkyj.getNickName());
	    try {
	    	total = bWxFkyjService.countByMap(bwfkyj);
	    	list = bWxFkyjService.queryAllInfo(bwfkyj,rowBounds);
	    } catch (Exception e) {
	      jsonObject.put("error", e);
	      JSONUtil.writeJSONObjectToResponse(response, jsonObject);
	      e.printStackTrace();
	    }
	    jsonObject.put("rows", list);
	    jsonObject.put("total", total);
	    JSONUtil.writeJSONObjectToResponse(response, jsonObject);
	}
}
