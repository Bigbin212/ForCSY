package com.csy.module.wx.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BFailureReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BFailureReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidIsNull() {
            addCriterion("fk_wx_openid is null");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidIsNotNull() {
            addCriterion("fk_wx_openid is not null");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidEqualTo(String value) {
            addCriterion("fk_wx_openid =", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidNotEqualTo(String value) {
            addCriterion("fk_wx_openid <>", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidGreaterThan(String value) {
            addCriterion("fk_wx_openid >", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("fk_wx_openid >=", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidLessThan(String value) {
            addCriterion("fk_wx_openid <", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidLessThanOrEqualTo(String value) {
            addCriterion("fk_wx_openid <=", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidLike(String value) {
            addCriterion("fk_wx_openid like", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidNotLike(String value) {
            addCriterion("fk_wx_openid not like", value, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidIn(List<String> values) {
            addCriterion("fk_wx_openid in", values, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidNotIn(List<String> values) {
            addCriterion("fk_wx_openid not in", values, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidBetween(String value1, String value2) {
            addCriterion("fk_wx_openid between", value1, value2, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andFkWxOpenidNotBetween(String value1, String value2) {
            addCriterion("fk_wx_openid not between", value1, value2, "fkWxOpenid");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterion("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadPositionIsNull() {
            addCriterion("upload_position is null");
            return (Criteria) this;
        }

        public Criteria andUploadPositionIsNotNull() {
            addCriterion("upload_position is not null");
            return (Criteria) this;
        }

        public Criteria andUploadPositionEqualTo(String value) {
            addCriterion("upload_position =", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionNotEqualTo(String value) {
            addCriterion("upload_position <>", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionGreaterThan(String value) {
            addCriterion("upload_position >", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionGreaterThanOrEqualTo(String value) {
            addCriterion("upload_position >=", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionLessThan(String value) {
            addCriterion("upload_position <", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionLessThanOrEqualTo(String value) {
            addCriterion("upload_position <=", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionLike(String value) {
            addCriterion("upload_position like", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionNotLike(String value) {
            addCriterion("upload_position not like", value, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionIn(List<String> values) {
            addCriterion("upload_position in", values, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionNotIn(List<String> values) {
            addCriterion("upload_position not in", values, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionBetween(String value1, String value2) {
            addCriterion("upload_position between", value1, value2, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andUploadPositionNotBetween(String value1, String value2) {
            addCriterion("upload_position not between", value1, value2, "uploadPosition");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionIsNull() {
            addCriterion("fault_description is null");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionIsNotNull() {
            addCriterion("fault_description is not null");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionEqualTo(String value) {
            addCriterion("fault_description =", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotEqualTo(String value) {
            addCriterion("fault_description <>", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionGreaterThan(String value) {
            addCriterion("fault_description >", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("fault_description >=", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionLessThan(String value) {
            addCriterion("fault_description <", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionLessThanOrEqualTo(String value) {
            addCriterion("fault_description <=", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionLike(String value) {
            addCriterion("fault_description like", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotLike(String value) {
            addCriterion("fault_description not like", value, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionIn(List<String> values) {
            addCriterion("fault_description in", values, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotIn(List<String> values) {
            addCriterion("fault_description not in", values, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionBetween(String value1, String value2) {
            addCriterion("fault_description between", value1, value2, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultDescriptionNotBetween(String value1, String value2) {
            addCriterion("fault_description not between", value1, value2, "faultDescription");
            return (Criteria) this;
        }

        public Criteria andFaultImagesIsNull() {
            addCriterion("fault_images is null");
            return (Criteria) this;
        }

        public Criteria andFaultImagesIsNotNull() {
            addCriterion("fault_images is not null");
            return (Criteria) this;
        }

        public Criteria andFaultImagesEqualTo(String value) {
            addCriterion("fault_images =", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesNotEqualTo(String value) {
            addCriterion("fault_images <>", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesGreaterThan(String value) {
            addCriterion("fault_images >", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesGreaterThanOrEqualTo(String value) {
            addCriterion("fault_images >=", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesLessThan(String value) {
            addCriterion("fault_images <", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesLessThanOrEqualTo(String value) {
            addCriterion("fault_images <=", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesLike(String value) {
            addCriterion("fault_images like", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesNotLike(String value) {
            addCriterion("fault_images not like", value, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesIn(List<String> values) {
            addCriterion("fault_images in", values, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesNotIn(List<String> values) {
            addCriterion("fault_images not in", values, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesBetween(String value1, String value2) {
            addCriterion("fault_images between", value1, value2, "faultImages");
            return (Criteria) this;
        }

        public Criteria andFaultImagesNotBetween(String value1, String value2) {
            addCriterion("fault_images not between", value1, value2, "faultImages");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("audit_status like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("audit_status not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNull() {
            addCriterion("process_status is null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNotNull() {
            addCriterion("process_status is not null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusEqualTo(String value) {
            addCriterion("process_status =", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotEqualTo(String value) {
            addCriterion("process_status <>", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThan(String value) {
            addCriterion("process_status >", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThanOrEqualTo(String value) {
            addCriterion("process_status >=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThan(String value) {
            addCriterion("process_status <", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThanOrEqualTo(String value) {
            addCriterion("process_status <=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLike(String value) {
            addCriterion("process_status like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotLike(String value) {
            addCriterion("process_status not like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIn(List<String> values) {
            addCriterion("process_status in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotIn(List<String> values) {
            addCriterion("process_status not in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusBetween(String value1, String value2) {
            addCriterion("process_status between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotBetween(String value1, String value2) {
            addCriterion("process_status not between", value1, value2, "processStatus");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}