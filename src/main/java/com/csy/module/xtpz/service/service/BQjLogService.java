package com.csy.module.xtpz.service.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.csy.module.xtpz.entity.BQjLog;
import com.csy.module.xtpz.entity.BQjLogExample;
import com.csy.util.spring.BaseDao;

public interface BQjLogService extends BaseDao<BQjLog,BQjLogExample>{


	List<BQjLog> selectCzrzByqueryParams(String kssj, String jssj,
			String operrst, String faccount, RowBounds rb);

	int countByqueryParams(String kssj, String jssj, String operrst,
			String faccount);

}
