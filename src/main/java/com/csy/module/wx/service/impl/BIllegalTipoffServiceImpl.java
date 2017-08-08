package com.csy.module.wx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csy.module.wx.dao.BIllegalTipoffMapper;
import com.csy.module.wx.entity.BIllegalTipoff;
import com.csy.module.wx.entity.BIllegalTipoffExample;
import com.csy.module.wx.service.service.BIllegalTipoffService;
import com.csy.util.StringUtils;
import com.csy.util.spring.BaseService;
import com.csy.util.wx.en.WxFileEnum;
import com.csy.util.wx.queue.FileQueue;
import com.csy.util.wx.queue.FileQueue.FileDescription;

@Service
public class BIllegalTipoffServiceImpl extends BaseService<BIllegalTipoff, BIllegalTipoffExample> 
implements BIllegalTipoffService{
  
  @Autowired
  private BIllegalTipoffMapper tipoffDao;
  
  @Override
  public int addTipOff(BIllegalTipoff tipoff){
    
    Date curDate = new Date();
    tipoff.setOccurrenceTime(curDate);
    tipoff.setId(UUID.randomUUID().toString());
    String s_imgUrls = tipoff.getIllegalImages();
    if(StringUtils.notTrimEmpty(s_imgUrls)){
     //图片文件转换
     String[] liveImages = s_imgUrls.split(",");
     List<String> newLiveImages = new ArrayList<String>();
     for(String liveImage : liveImages){
      String newLiveImage = new StringBuffer().append(UUID.randomUUID().toString()).append(".jpg").toString();
      FileDescription fileDetail = FileQueue.getInstance()
        .new FileDescription(liveImage, tipoff.getFkWxOpenid(), newLiveImage, WxFileEnum.IMAGE, curDate);
      FileQueue.getInstance().add(fileDetail);
      newLiveImages.add(newLiveImage);
     }
     tipoff.setIllegalImages(StringUtils.join(newLiveImages));
    }
    return tipoffDao.insertSelective(tipoff);
  }
}
