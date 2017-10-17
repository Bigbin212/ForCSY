package com.csy.module.wx.entity;

import java.util.Date;

public class BFailureReport {
    private String id;

    private String fkWxOpenid;

    private String phone;

    private Date uploadTime;

    private String uploadPosition;

    private String longitude;

    private String latitude;

    private String faultDescription;

    private String faultImages;

    private String auditStatus;

    private String processStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFkWxOpenid() {
        return fkWxOpenid;
    }

    public void setFkWxOpenid(String fkWxOpenid) {
        this.fkWxOpenid = fkWxOpenid == null ? null : fkWxOpenid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadPosition() {
        return uploadPosition;
    }

    public void setUploadPosition(String uploadPosition) {
        this.uploadPosition = uploadPosition == null ? null : uploadPosition.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getFaultDescription() {
        return faultDescription;
    }

    public void setFaultDescription(String faultDescription) {
        this.faultDescription = faultDescription == null ? null : faultDescription.trim();
    }

    public String getFaultImages() {
        return faultImages;
    }

    public void setFaultImages(String faultImages) {
        this.faultImages = faultImages == null ? null : faultImages.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }
}