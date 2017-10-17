package com.csy.module.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.csy.module.wx.entity.BFailureReport;
import com.csy.module.wx.entity.BFailureReportExample;
import com.csy.util.spring.BaseDao;

public interface BFailureReportMapper extends BaseDao<BFailureReport, BFailureReportExample>{

	List<BFailureReport> selectByParams(@Param("kssj")String kssj,@Param("jssj") String jssj, @Param("auditstatus")String auditstatus,
			@Param("processstatus")String processstatus, RowBounds rb);

	int countByParams(@Param("kssj")String kssj,@Param("jssj") String jssj, @Param("auditstatus")String auditstatus,
			@Param("processstatus")String processstatus);
  
}