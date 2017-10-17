package com.csy.module.wx.service.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.csy.module.wx.dto.BfailureReportUser;
import com.csy.module.wx.entity.BFailureReport;
import com.csy.module.wx.entity.BFailureReportExample;
import com.csy.util.spring.BaseDao;

public interface BFailureReportService  extends BaseDao<BFailureReport, BFailureReportExample>{

  int addFailureReport(BFailureReport report);



	List<BfailureReportUser> selectByParams(BfailureReportUser report,
			RowBounds rb);



	int countByParams(BfailureReportUser report);
}
