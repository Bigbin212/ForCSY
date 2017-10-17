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

import com.csy.module.user.entity.BUserAccount;
import com.csy.module.wx.dto.BIllegalTipoffUser;
import com.csy.module.wx.entity.BIllegalTipoff;
import com.csy.module.wx.service.service.BAccidentInfoService;
import com.csy.module.wx.service.service.BIllegalTipoffService;
import com.csy.module.xtpz.entity.BQjLog;
import com.csy.module.xtpz.service.service.BQjLogService;
import com.csy.util.JSONUtil;
import com.csy.util.StringUtils;
import com.csy.util.TimeFormatUtil;
import com.csy.util.exception.account.UserNotFoundException;
import com.csy.util.log.LogUtil;

@Controller
public class WfjbglAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private BAccidentInfoService bAccidentInfoService;

	@Resource
	private BIllegalTipoffService bIllegalTipooffService;
	
	@Resource
	private BQjLogService bQjLogService;

	private static String Param = "";

	/**
	 * 说明:违法举报查询页面 创建时间：2017-08-15
	 * 
	 * @author guwenwen
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/WFJBGL")
	public ModelAndView wfjbgl(String param, HttpServletRequest req)
			throws Exception {
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
		return new ModelAndView("wfjlgl/wfjbcx", paramsMap);
	}

	/**
	 * 说明：根据事故发生时间、微信号、号牌号码去查询事故、微信用户信息 创建时间：2017-08-15
	 * 
	 * @author guwenwen
	 */
	@RequestMapping("/wfjlcx.do")
	public void findByParam(HttpServletRequest request,
			HttpServletResponse response, BIllegalTipoffUser tipoff) {

		List<BIllegalTipoffUser> list = new ArrayList<BIllegalTipoffUser>();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("error", "");
		try {
			list = bIllegalTipooffService.selectByExample(tipoff);
		} catch (Exception e) {
			jsonObject.put("error", e);
			JSONUtil.writeJSONObjectToResponse(response, jsonObject);
			e.printStackTrace();
		}
		jsonObject.put("data", list);
		JSONUtil.writeJSONObjectToResponse(response, jsonObject);

	}

	@RequestMapping("/wfcheck.do")
	public void wfcheck(HttpServletRequest request,
			HttpServletResponse response, BIllegalTipoff btipoff,
			BIllegalTipoffUser tipoff) throws UserNotFoundException {
		String wfid = request.getParameter("wfid");
		String wfstate = request.getParameter("wfstate");
		btipoff.setId(wfid);
		btipoff.setStatus(wfstate);
		bIllegalTipooffService.updateByPrimaryKeySelective(btipoff);
		/* bIllegalTipooffService.updateState(wfid,wfstate); */
		String model = "1";// 操作模块 审核
		String operType = "1";// 操作类型 更新
		String name;
		String operRst = wfstate;// 操作结果
		if(tipoff.getWxzh() != null && tipoff.getWxzh() != "")
		{
         name = tipoff.getWxzh();
		}
		else
		{
			name  = null;
		}
	/*	String state = statusMean(wfstate);*/
		String time = tipoff.getWfsj();
		/* String description = "审核了id="+wfid+",上传者="+name+"的记录,审核状态变为"+state; */
		/*String description = "审核上传数据[事故编号,微信昵称,举报时间,审核状态][" + wfid + "," + name
				+ "," + time + "," + state + "]";*/
		String description = "对事故编号为"+wfid+"的数据进行了审核";
		LogUtil.insert(request, model, operType, operRst, description);
	}

	// 审核状态
/*	public String statusMean(String wfstate) {
		if (wfstate.equals("1")) {
			wfstate = "已确认";
		} else if (wfstate.equals("2")) {
			wfstate = "已忽略";
		} else {
			wfstate = "未处理";
		}
		return wfstate;
	}*/

	// 操作日志记录

	@RequestMapping("/showCzrz")
	public ModelAndView showCzrz(String param, HttpServletRequest req) {
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

		return new ModelAndView("wfjlgl/wfjbCzrz", paramsMap);

	}

	@RequestMapping("/loadTable.do")
	public void loadCzrzTable(HttpServletRequest request,
			HttpServletResponse response) {

		// 获取分页条件
		int total = 0;
		int pageNum = 1; 
		int pageSize = 10;

		String page = request.getParameter("page");// 当前页
		String rows =request.getParameter("rows");// 每页显示数目

		if (page != null) {
			pageNum = Integer.parseInt(page);
		}
		if (rows != null) {
			pageSize = Integer.parseInt(rows);
		}
		RowBounds rb = new RowBounds((pageNum - 1) * pageSize, pageSize);

		// 根据查询条件查找

		String kssj = request.getParameter("kssj");// 开始时间 
		String jssj =	request.getParameter("jssj");// 结束时间 
		String operrst =request.getParameter("operrst");// 审核结果
		String faccount = request.getParameter("faccount");//审核人
	
		List<BQjLog> list = new ArrayList<BQjLog>();
		JSONObject jsonObject = new JSONObject();
		list = bQjLogService.selectCzrzByqueryParams(kssj,jssj,operrst,faccount,rb);
		total = bQjLogService.countByqueryParams(kssj,jssj,operrst,faccount);

		jsonObject.put("rows", list);
		jsonObject.put("total", total);
		JSONUtil.writeJSONObjectToResponse(response, jsonObject);

	}

}
