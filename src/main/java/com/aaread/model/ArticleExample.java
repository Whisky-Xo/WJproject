package com.aaread.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public ArticleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNull() {
            addCriterion("read_num is null");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNotNull() {
            addCriterion("read_num is not null");
            return (Criteria) this;
        }

        public Criteria andReadNumEqualTo(Integer value) {
            addCriterion("read_num =", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotEqualTo(Integer value) {
            addCriterion("read_num <>", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThan(Integer value) {
            addCriterion("read_num >", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_num >=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThan(Integer value) {
            addCriterion("read_num <", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThanOrEqualTo(Integer value) {
            addCriterion("read_num <=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumIn(List<Integer> values) {
            addCriterion("read_num in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotIn(List<Integer> values) {
            addCriterion("read_num not in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumBetween(Integer value1, Integer value2) {
            addCriterion("read_num between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("read_num not between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andZanNumIsNull() {
            addCriterion("zan_num is null");
            return (Criteria) this;
        }

        public Criteria andZanNumIsNotNull() {
            addCriterion("zan_num is not null");
            return (Criteria) this;
        }

        public Criteria andZanNumEqualTo(Integer value) {
            addCriterion("zan_num =", value, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumNotEqualTo(Integer value) {
            addCriterion("zan_num <>", value, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumGreaterThan(Integer value) {
            addCriterion("zan_num >", value, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("zan_num >=", value, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumLessThan(Integer value) {
            addCriterion("zan_num <", value, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumLessThanOrEqualTo(Integer value) {
            addCriterion("zan_num <=", value, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumIn(List<Integer> values) {
            addCriterion("zan_num in", values, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumNotIn(List<Integer> values) {
            addCriterion("zan_num not in", values, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumBetween(Integer value1, Integer value2) {
            addCriterion("zan_num between", value1, value2, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZanNumNotBetween(Integer value1, Integer value2) {
            addCriterion("zan_num not between", value1, value2, "zanNum");
            return (Criteria) this;
        }

        public Criteria andZuozheIsNull() {
            addCriterion("zuozhe is null");
            return (Criteria) this;
        }

        public Criteria andZuozheIsNotNull() {
            addCriterion("zuozhe is not null");
            return (Criteria) this;
        }

        public Criteria andZuozheEqualTo(String value) {
            addCriterion("zuozhe =", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheNotEqualTo(String value) {
            addCriterion("zuozhe <>", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheGreaterThan(String value) {
            addCriterion("zuozhe >", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheGreaterThanOrEqualTo(String value) {
            addCriterion("zuozhe >=", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheLessThan(String value) {
            addCriterion("zuozhe <", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheLessThanOrEqualTo(String value) {
            addCriterion("zuozhe <=", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheLike(String value) {
            addCriterion("zuozhe like", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheNotLike(String value) {
            addCriterion("zuozhe not like", value, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheIn(List<String> values) {
            addCriterion("zuozhe in", values, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheNotIn(List<String> values) {
            addCriterion("zuozhe not in", values, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheBetween(String value1, String value2) {
            addCriterion("zuozhe between", value1, value2, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andZuozheNotBetween(String value1, String value2) {
            addCriterion("zuozhe not between", value1, value2, "zuozhe");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andIdescIsNull() {
            addCriterion("idesc is null");
            return (Criteria) this;
        }

        public Criteria andIdescIsNotNull() {
            addCriterion("idesc is not null");
            return (Criteria) this;
        }

        public Criteria andIdescEqualTo(String value) {
            addCriterion("idesc =", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescNotEqualTo(String value) {
            addCriterion("idesc <>", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescGreaterThan(String value) {
            addCriterion("idesc >", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescGreaterThanOrEqualTo(String value) {
            addCriterion("idesc >=", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescLessThan(String value) {
            addCriterion("idesc <", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescLessThanOrEqualTo(String value) {
            addCriterion("idesc <=", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescLike(String value) {
            addCriterion("idesc like", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescNotLike(String value) {
            addCriterion("idesc not like", value, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescIn(List<String> values) {
            addCriterion("idesc in", values, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescNotIn(List<String> values) {
            addCriterion("idesc not in", values, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescBetween(String value1, String value2) {
            addCriterion("idesc between", value1, value2, "idesc");
            return (Criteria) this;
        }

        public Criteria andIdescNotBetween(String value1, String value2) {
            addCriterion("idesc not between", value1, value2, "idesc");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table article
     *
     * @mbggenerated do_not_delete_during_merge Mon Jan 16 17:53:41 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table article
     *
     * @mbggenerated Mon Jan 16 17:53:41 CST 2017
     */
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