package com.aaread.mapper;

import java.util.List;

import com.aaread.model.Topic;
import com.aaread.model.TopicExample;

public interface TopicMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int countByExample(TopicExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int insert(Topic record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int insertSelective(Topic record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	List<Topic> selectByExample(TopicExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	Topic selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int updateByPrimaryKeySelective(Topic record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table topic
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int updateByPrimaryKey(Topic record);

	List<Topic> list();

	int updateDingyue(long tid);

	int quxiaoDingyue(int tid);
	
}