package com.csy.module.xtpz.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csy.module.xtpz.dao.BQjLogMapper;
import com.csy.module.xtpz.entity.BQjLog;
import com.csy.module.xtpz.entity.BQjLogExample;
import com.csy.module.xtpz.service.service.BQjLogService;
import com.csy.util.spring.BaseService;


@Service("BQjLogService")
public class BQjLogServiceImpl extends BaseService<BQjLog, BQjLogExample> implements BQjLogService {
	
	@Autowired
	private BQjLogMapper logdao;

	@Override
	public List<BQjLog> selectCzrzByqueryParams(String kssj, String jssj,
			String operrst, String faccount, RowBounds rb) {
		// TODO Auto-generated method stub
		return logdao.selectCzrzByqueryParams( kssj,  jssj, operrst, faccount,rb);
	}

	@Override
	public int countByqueryParams(String kssj, String jssj, String operrst,
			String faccount) {
		// TODO Auto-generated method stub
		return logdao.countByParams(kssj,jssj,operrst,faccount);
	}

}
