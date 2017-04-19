package com.csy.module.wx.dao;

import com.csy.module.wx.entity.BWxUser;
import com.csy.module.wx.entity.BWxUserExample;
import com.csy.util.spring.BaseDao;

public interface BWxUserMapper extends BaseDao<BWxUser, BWxUserExample>{
	//���΢�ź�ȥ��ѯ΢����Ϣ
	BWxUser selectByParam(BWxUser bWxUser);
	
}