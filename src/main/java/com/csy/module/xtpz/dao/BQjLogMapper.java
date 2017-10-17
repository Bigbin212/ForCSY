package com.csy.module.xtpz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.access.method.P;

import com.csy.module.xtpz.entity.BQjLog;
import com.csy.module.xtpz.entity.BQjLogExample;
import com.csy.util.spring.BaseDao;

public interface BQjLogMapper extends BaseDao<BQjLog, BQjLogExample>{

	List<BQjLog> selectCzrzByqueryParams(@Param("kssj")String kssj,@Param("jssj") String jssj,
			@Param("operrst")String operrst,@Param("faccount") String faccount, RowBounds rb);

	int countByParams(@Param("kssj")String kssj,@Param("jssj") String jssj,
			@Param("operrst")String operrst,@Param("faccount") String faccount);
  
}