package com.csy.module.wx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.apache.tools.ant.taskdefs.Nice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csy.module.wx.dao.BFailureReportMapper;
import com.csy.module.wx.dao.BWxUserMapper;
import com.csy.module.wx.dto.BfailureReportUser;
import com.csy.module.wx.entity.BFailureReport;
import com.csy.module.wx.entity.BFailureReportExample;
import com.csy.module.wx.entity.BWxUser;
import com.csy.module.wx.service.service.BFailureReportService;
import com.csy.util.StringUtils;
import com.csy.util.TimeFormatUtil;
import com.csy.util.XtpzUtil;
import com.csy.util.spring.BaseService;
import com.csy.util.wx.en.WxFileEnum;
import com.csy.util.wx.queue.FileQueue;
import com.csy.util.wx.queue.FileQueue.FileDescription;

@Service
public class BFailureReportServiceImpl extends
		BaseService<BFailureReport, BFailureReportExample> implements
		BFailureReportService {
   @Autowired
   private BFailureReportMapper reportdao;
   @Autowired
   private BWxUserMapper userdao;
	@Override
	public int addFailureReport(BFailureReport report) {
		// 设置ID
		report.setId(UUID.randomUUID().toString());
		// 图片存储,及图片名称重编号
		String s_imgUrls = report.getFaultImages();
		if (StringUtils.notTrimEmpty(s_imgUrls)) {
			// 图片文件转换
			String[] liveImages = s_imgUrls.split(",");
			List<String> newLiveImages = new ArrayList<String>();
			Date curDate = new Date();
			for (String liveImage : liveImages) {
				String newLiveImage = new StringBuffer()
						.append(UUID.randomUUID().toString()).append(".jpg")
						.toString();
				FileDescription fileDetail = FileQueue.getInstance().new FileDescription(
						liveImage, report.getFkWxOpenid(), newLiveImage,
						WxFileEnum.IMAGE, curDate);
				FileQueue.getInstance().add(fileDetail);
				newLiveImages.add(newLiveImage);
			}
			report.setFaultImages(StringUtils.join(newLiveImages));
		}
		return _dao.insertSelective(report);
	}



	public boolean isNotNullOrEmpty(String resStr) {
		boolean ret = true;
		if (null == resStr || resStr.isEmpty()) {
			ret = false;
		}
		return ret;
	}

	@Override
	public List<BfailureReportUser> selectByParams(BfailureReportUser reportUser,
			RowBounds rb) {
		List<BfailureReportUser> reportuserlists = new ArrayList<BfailureReportUser>();
		List<BFailureReport> reportlists = new ArrayList<BFailureReport>();
		String kssj = null;
		String jssj = null;
		String auditstatus = null;
		String processstatus = null;
		String nickName = null;
		if (isNotNullOrEmpty(reportUser.getKssj())) {
			kssj = reportUser.getKssj();
		}
		if (isNotNullOrEmpty(reportUser.getJssj())) {
			jssj = reportUser.getJssj();
		}
		if (isNotNullOrEmpty(reportUser.getAuditstatus())) {
			auditstatus = reportUser.getAuditstatus();
		}
		if (isNotNullOrEmpty(reportUser.getprocessstatus())) {
			processstatus = reportUser.getprocessstatus();
		}
		if(isNotNullOrEmpty(reportUser.getNickname())){
			nickName = reportUser.getNickname();
		}
	

		reportlists  = reportdao.selectByParams(kssj,jssj,auditstatus,processstatus,rb);
		for(int i = 0;i<reportlists.size();i++)
		{
			BFailureReport report = reportlists.get(i);
			BfailureReportUser reportuser = new BfailureReportUser();
			String openid = null;
			BWxUser bwxUser=new BWxUser();
			//获得openid
			if(isNotNullOrEmpty(report.getFkWxOpenid()))
			{
				openid = report.getFkWxOpenid();
				bwxUser.setOpenid(openid);
				reportuser.setOpenid(openid);
			}
			//根据openid获得用户
			BWxUser user = userdao.selectByParam(bwxUser);
	    
	     	//时间
	     	if(report.getUploadTime() != null)
	     	{
	     		Date uploadtime=report.getUploadTime();
	     		reportuser.setUploadtime(TimeFormatUtil.dateToString(uploadtime));
	     	}
	     	//地点
	     	if(isNotNullOrEmpty(report.getUploadPosition()))
	     	{
	     		reportuser.setUploadposition(report.getUploadPosition());
	     	}
	     	//经度
	     	if(isNotNullOrEmpty(report.getLongitude()))
	     	{
	     		reportuser.setLongitude(report.getLongitude());
	     	}
	     	//纬度
	     	if(isNotNullOrEmpty(report.getLatitude()))
	     	{
	     		reportuser.setLatitude(report.getLatitude());
	     	}
	     	//现场图片
	     	if(isNotNullOrEmpty(report.getFaultImages()))
	     	{
	     		reportuser.setFaultImages(report.getFaultImages());
	     	}
	     	//故障描述
	     	if(isNotNullOrEmpty(report.getFaultDescription()))
	     	{
	     		reportuser.setFaultDescription(report.getFaultDescription());
	     	}
	     	//号码
	     	if(isNotNullOrEmpty(report.getPhone()))
	     	{
	     		reportuser.setPhone(report.getPhone());
	     	}
	     	//id
	     	if(isNotNullOrEmpty(report.getId()))
	     	{
	     		reportuser.setFaultid(report.getId());
	     	}
	     	//审核状态
	     	if(isNotNullOrEmpty(report.getAuditStatus()))
	     	{
	     		reportuser.setAuditstatus(report.getAuditStatus());
	     	}
	     	//审核状态
	     	if(isNotNullOrEmpty(report.getAuditStatus()))
	     	{
	     		reportuser.setprocessstatus(report.getProcessStatus());
	     	}
	    	//图片路径前缀
	     	reportuser.setImagepath(XtpzUtil.getXtpzByName("wxResource").getVal());
	     	if(user !=null)
	        {
			  //微信账号
			   if(user.getNickname() !=null&&user.getNickname() !="")
			   {
			      String nickname=user.getNickname();
			      reportuser.setNickname(nickname);

			   }
			//微信头像
					if(user.getHeadimgurl() != null && user.getHeadimgurl() != ""){
					String wxtx=user.getHeadimgurl().substring(0, user.getHeadimgurl().lastIndexOf("/")+1)+64;
					reportuser.setWxheadimage(wxtx);
			     
					}
	     	}
	     	if(reportuser.getNickname() !=null && nickName != null)
	     	{	
	     		String name = reportuser.getNickname();
	     		if(name.indexOf(nickName) != -1)
	     		{
	     	         reportuserlists .add(reportuser);
	     		}
	     	}else  if(nickName ==null)
	     	{
	     
	     			  reportuserlists .add(reportuser);
	     	
	     			
	     	}
	    
	     		
	     	
			
					
		}
	

		return reportuserlists;
	}



	@Override
	public int countByParams(BfailureReportUser reportUser) {
	
		String kssj = null;
		String jssj = null;
		String auditstatus = null;
		String processstatus = null;
		if (isNotNullOrEmpty(reportUser.getKssj())) {
			kssj = reportUser.getKssj();
		}
		if (isNotNullOrEmpty(reportUser.getJssj())) {
			jssj = reportUser.getJssj();
		}
		if (isNotNullOrEmpty(reportUser.getAuditstatus())) {
			auditstatus = reportUser.getAuditstatus();
		}
		if (isNotNullOrEmpty(reportUser.getprocessstatus())) {
			processstatus = reportUser.getprocessstatus();
		}
		return reportdao.countByParams(kssj,jssj,auditstatus,processstatus);
	

		
	}
}
