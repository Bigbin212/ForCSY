package com.csy.module.wx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csy.module.wx.dao.BIllegalTipoffMapper;
import com.csy.module.wx.dao.BWxUserMapper;
import com.csy.module.wx.dto.BIllegalTipoffUser;
import com.csy.module.wx.entity.BIllegalTipoff;
import com.csy.module.wx.entity.BIllegalTipoffExample;
import com.csy.module.wx.entity.BWxUser;
import com.csy.module.wx.service.service.BIllegalTipoffService;
import com.csy.util.StringUtils;
import com.csy.util.TimeFormatUtil;
import com.csy.util.XtpzUtil;
import com.csy.util.spring.BaseService;
import com.csy.util.wx.en.WxFileEnum;
import com.csy.util.wx.queue.FileQueue;
import com.csy.util.wx.queue.FileQueue.FileDescription;

@Service
public class BIllegalTipoffServiceImpl extends BaseService<BIllegalTipoff, BIllegalTipoffExample> 
implements BIllegalTipoffService{
  
  @Autowired
  private BIllegalTipoffMapper tipoffDao;
  
  @Autowired
  private BWxUserMapper userdao;
  
  
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

	/**
	 * 说明：根据时间查询违法记录、举报人、微信信息
	 */
@Override
public List<BIllegalTipoffUser> selectByExample(BIllegalTipoffUser tipoffUser) {
	List<BIllegalTipoffUser> lists=new ArrayList<BIllegalTipoffUser>();
	List<BIllegalTipoff> tipofflists=new ArrayList<BIllegalTipoff>();
    String kssj = null;
    String jssj = null;
    String status=null;
	if(tipoffUser.getKssj() != null && tipoffUser.getKssj() != "")
	{
	   kssj=tipoffUser.getKssj();
	}
	if(tipoffUser.getJssj() !=null && tipoffUser.getJssj() != "")
	{
		jssj=tipoffUser.getJssj();
	}
	if(tipoffUser.getWfstate() !=null&&tipoffUser.getWfstate() !="")
	{
		status=tipoffUser.getWfstate();
	}
	tipofflists=tipoffDao.selectTipoffBysj(kssj,jssj,status);
	for(int i=0;i<tipofflists.size();i++)
	{
		BIllegalTipoffUser btu=new BIllegalTipoffUser();
		BIllegalTipoff tipoff=tipofflists.get(i);
		//号牌号码
		if(tipoff.getPlateNumber() !=null&&tipoff.getPlateNumber() !="")
		{
		String hphm=tipoff.getPlateNumber();

		btu.setHphm(hphm);
		}
		String openid;
		BWxUser bwxUser=new BWxUser();
		//通过openid获得微信用户
		if(tipoff.getFkWxOpenid() !=null&&tipoff.getFkWxOpenid() !="")
		{
         openid=tipoff.getFkWxOpenid();
         bwxUser.setOpenid(openid);
 		btu.setWxid(openid);
		}
     	BWxUser user=userdao.selectByParam(bwxUser);
     	if(user !=null)
        {
		  //微信账号
		   if(user.getNickname() !=null&&user.getNickname() !="")
		   {
		   String wxh=user.getNickname();
	
		    btu.setWxzh(wxh);
		   }
		//微信头像
				if(user.getHeadimgurl() != null && user.getHeadimgurl() != ""){
				String wxtx=user.getHeadimgurl().substring(0, user.getHeadimgurl().lastIndexOf("/")+1)+64;
		     	btu.setWxtx(wxtx);
				}
     	}
		//违法时间
		if(tipoff.getOccurrenceTime() !=null)
		{
			Date occurrenceTime=tipoff.getOccurrenceTime();
			btu.setWfsj(TimeFormatUtil.dateToString(occurrenceTime));
		}

		//违法地点
		if(tipoff.getIllegalPosition() !=null && tipoff.getIllegalPosition() !="")
		{
		String wfdd=tipoff.getIllegalPosition();
		btu.setWfdd(wfdd);
		}
		//违法描述--违法行为
		if(tipoff.getIllegalAct() !=null && tipoff.getIllegalAct() !="")
		{
		String wfms=tipoff.getIllegalAct();
		btu.setWfxw(wfms);
		}
		//举报人
		if(tipoff.getName() !=null && tipoff.getName() !="")
		{
		String name=tipoff.getName();
		btu.setJbr(name);
		}
		//举报人电话
		if(tipoff.getPhone() !=null  && tipoff.getPhone() !="")
		{
		String lxdh=tipoff.getPhone();
		btu.setLxdh(lxdh);
		}
		//举报人身份证号
		if(tipoff.getIdcard() !=null && tipoff.getIdcard() !="")
		{
		String sfzh=tipoff.getIdcard();
		btu.setSfzh(sfzh);
		}
		
		//违法经度
		if(tipoff.getLongitude() !=null && tipoff.getLongitude() !="")
		{
		String wfjd=tipoff.getLongitude();
		btu.setWfjd(wfjd);
		}
		//违法纬度
		if(tipoff.getLatitude() !=null && tipoff.getLatitude() !="")
		{
		String wfwd=tipoff.getLatitude();
		btu.setWfwd(wfwd);
		}
		//现场图片
		if(tipoff.getIllegalImages() !=null &&tipoff.getIllegalImages() !="")
		{
		String wftp=tipoff.getIllegalImages();
		btu.setWftp(wftp);
		}
		//违法id
		if(tipoff.getId() !=null && tipoff.getId() !="")
		{
		String wfid=tipoff.getId();
		btu.setWfid(wfid);
		}
		//处理状态
		if(tipoff.getStatus() !=null && tipoff.getStatus() !="")
		{
		String wfstate=tipoff.getStatus();
	    btu.setWfstate(wfstate);
		}
	//图片路径前缀
		btu.setImagepath(XtpzUtil.getXtpzByName("wxResource").getVal());
	
		lists.add(btu);
	}
	return lists;

}









/*	@Override
	public void updateState(String wfid, String wfstate) {
		// TODO Auto-generated method stub
		tipoffDao.updateState(wfid,wfstate);
		
	}*/
}
