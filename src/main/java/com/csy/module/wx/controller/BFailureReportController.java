package com.csy.module.wx.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.csy.module.wx.entity.BFailureReport;
import com.csy.module.wx.entity.BWxUser;
import com.csy.module.wx.service.service.BFailureReportService;
import com.csy.util.JSONUtil;
import com.csy.util.StringUtils;
import com.csy.util.TimeFormatUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("wx/failureReport")
public class BFailureReportController {


  @Autowired
  private BFailureReportService failureReportService;

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @RequestMapping("index")
  public ModelAndView index(){
    ModelAndView mv = new ModelAndView("weixin/failureReport");
    return mv;
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public void post(HttpServletRequest request, HttpServletResponse response, @RequestBody BFailureReport failureReport){
    JSONObject rst = null;
    BWxUser wxUser = (BWxUser) request.getSession(true).getAttribute("bWxUser");
    if(wxUser == null){
      rst = new JSONObject();
      rst.put("errMsg", "未抓取到上传用户信息");
    }else{
      rst = new JSONObject();  
      try{
        failureReport.setFkWxOpenid(wxUser.getOpenid());
        failureReportService.addFailureReport(failureReport);
        rst.put("errMsg", "");
      }catch(Exception e){
        rst.put("errMsg", e.getMessage());
        e.printStackTrace();
        logger.error(e.getMessage());
      }
    }
    JSONUtil.writeJSONObjectToResponse(response, rst);
  }
  
  

}
