package com.csy.module.wx.service.service;

import com.csy.module.wx.entity.BIllegalTipoff;
import com.csy.module.wx.entity.BIllegalTipoffExample;
import com.csy.util.spring.BaseDao;

public interface BIllegalTipoffService extends BaseDao<BIllegalTipoff, BIllegalTipoffExample>{
  
  int addTipOff(BIllegalTipoff tipoff);
}
