package com.csy.module.wx.dto;

public class BfailureReportUser {
	private String kssj;//开始时间
	private String jssj;//结束时间
	private String auditstatus;//审核状态
	private String processstatus;//处理状态
	private String nickname;//微信昵称
	private String openid;//微信openid
	private String uploadtime;//上报时间
	private String uploadposition;//上报地点
	private String longitude;//经度
	private String latitude;//纬度
	private String faultImages;//上传图片
	private String faultDescription;//故障描述
	private String phone;//电话号码
	private String wxheadimage;//微信头像
	private String imagepath;//前缀
	private String faultid;//id
	public String getFaultid() {
		return faultid;
	}
	public void setFaultid(String faultid) {
		this.faultid = faultid;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getWxheadimage() {
		return wxheadimage;
	}
	public void setWxheadimage(String wxheadimage) {
		this.wxheadimage = wxheadimage;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getprocessstatus() {
		return processstatus;
	}
	public void setprocessstatus(String processstatus) {
		this.processstatus = processstatus;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getUploadposition() {
		return uploadposition;
	}
	public void setUploadposition(String uploadposition) {
		this.uploadposition = uploadposition;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getFaultImages() {
		return faultImages;
	}
	public void setFaultImages(String faultImages) {
		this.faultImages = faultImages;
	}
	public String getFaultDescription() {
		return faultDescription;
	}
	public void setFaultDescription(String faultDescription) {
		this.faultDescription = faultDescription;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
