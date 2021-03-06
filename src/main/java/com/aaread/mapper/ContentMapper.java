package com.aaread.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aaread.model.Content;
import com.aaread.model.ContentExample;

public interface ContentMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int countByExample(ContentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int insert(Content record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int insertSelective(Content record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	List<Content> selectByExampleWithBLOBs(ContentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	List<Content> selectByExample(ContentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	Content selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int updateByPrimaryKeySelective(Content record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int updateByPrimaryKeyWithBLOBs(Content record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table content
	 * @mbggenerated  Mon Jan 16 17:53:41 CST 2017
	 */
	int updateByPrimaryKey(Content record);

	List<Content> list(@Param("topicId")Long topicId, 
			@Param("uid")String uid, 
			@Param("start")Integer start, 
			@Param("limit")Integer limit);

	int updateDianzan(Long id);
}