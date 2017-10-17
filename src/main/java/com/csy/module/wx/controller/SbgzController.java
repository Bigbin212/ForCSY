package com.csy.module.wx.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csy.module.wx.dto.BfailureReportUser;
import com.csy.module.wx.entity.BFailureReport;
import com.csy.module.wx.service.service.BFailureReportService;
import com.csy.util.JSONUtil;
import com.csy.util.StringUtils;
import com.csy.util.TimeFormatUtil;

@Controller
public class SbgzController {
	  
		/**
		 * 说明:设备故障上报页面 创建时间：2017-09-05
		 * 
		 * @author guwenwen
		 * @return
		 * @throws Exception
		 */
	private static String Param = "";
	  @Autowired
	  private BFailureReportService failureReportService;
	  @RequestMapping("/SBGZSB")
		public ModelAndView  sbgz(String param, HttpServletRequest req)
		{
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Calendar calendar = Calendar.getInstance();
			String formatTime = TimeFormatUtil.timeToStr(calendar,
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			calendar.add(Calendar.DATE, -7);
			String formatTime1 = TimeFormatUtil.timeToStr(calendar,
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			paramsMap.put("startTime", formatTime1);
			paramsMap.put("endTime", formatTime);
			if (!StringUtils.isNull(param)) {
				Param = param;
			}
			paramsMap.put("param", Param);
			return new ModelAndView("sbgzsb/sbgzsb", paramsMap);
		}
		
		
		@RequestMapping("sbgzcx.do")
		public  void findByParams(HttpServletRequest request,
				HttpServletResponse response, BfailureReportUser report)		
		{

			// 获取分页条件
			int total = 0;
			int limit = 10; //每页显示数目
			int offset = 1;//当前页

			String sOffset = request.getParameter("offset");// 当前页
			String sLimit =request.getParameter("limit");// 每页显示数目

			if (sLimit != null) {
				limit = Integer.parseInt(sLimit);
			}
			if (sOffset  != null) {
				offset = Integer.parseInt(sOffset );
			}
			RowBounds rb = new RowBounds((offset - 1) * limit ,limit);
			List<BfailureReportUser> list = new ArrayList<BfailureReportUser>();
			JSONObject jsonObject = new  JSONObject();
			jsonObject.put("error", "");
			try {
				total = failureReportService.countByParams(report);
			list = failureReportService.selectByParams(report,rb);
			} catch (Exception e) {
				jsonObject.put("error", e);
				JSONUtil.writeJSONObjectToResponse(response, jsonObject);
				e.printStackTrace();
			}
			jsonObject.put("rows", list);
			jsonObject.put("total", total);
	
			JSONUtil.writeJSONObjectToResponse(response, jsonObject);
			
			
		}
		
		
		

		@RequestMapping("auditcheck.do")
		public void auditCheck(HttpServletRequest request,
            HttpServletResponse response, BfailureReportUser report)
		{
		    
	        BFailureReport falurereport = new BFailureReport();
	       falurereport.setId(report.getFaultid());
	       falurereport.setAuditStatus(report.getAuditstatus());
	       failureReportService.updateByPrimaryKeySelective(falurereport);
	       
		    
		}
		  @RequestMapping("processcheck.do")
	        public void processCheck(HttpServletRequest request,
	            HttpServletResponse response, BfailureReportUser report)
	        {
	          
	            BFailureReport falurereport = new BFailureReport();
	           falurereport.setId(report.getFaultid());
	           falurereport.setProcessStatus(report.getprocessstatus());
	           failureReportService.updateByPrimaryKeySelective(falurereport);
	           
	            
	        }
}
