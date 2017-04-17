package com.aaread.redis.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @title RedisCache
 * @description Redis 指令封装接口，支持安自定义键值进行Sharding - shardKey
 * @author Clark
 * @date 2014年7月2日
 * @version 1.0
 */
public interface RedisCache {
	
	/**
	 * 写入一个无时限、可序列化的对象，Redis
	 * @param key
	 * @param value
	 */
	<T> String put(String key, T value, Class<T> clazz);
	<T> String put(String key, T value, Class<T> clazz, String shardKey);
	
	/**
	 * 写入一个有时限、可序列化的对象，Redis
	 * @param key
	 * @param value
	 * @param seconds 设定一个key的活动时间（s）
	 */
	<T> String put(String key, T value, Class<T> clazz, int seconds);
	<T> String put(String key, T value, Class<T> clazz, int seconds, String shardKey);
	
	/**
	 * 名称为key的string增加integer
	 * @param key
	 * @param value
	 * @return
	 */
	Long incrby(String key, Long value);
	Long incrby(String key, Long value, String shardKey);
	
	/**
	 * 名称为key的string减少integer
	 * @param key
	 * @param value
	 * @return
	 */
	Long decrby(String key, Long value);
	Long decrby(String key, Long value, String shardKey);
	
	/**
	 * 名称为key的string增1操作
	 * @param key
	 * @return
	 */
	Long incr(String key);
	Long incr(String key, String shardKey);
	
	/**
	 * 名称为key的string减1操作
	 * @param key
	 * @return
	 */
	Long decr(String key);
	Long decr(String key, String shardKey);
	
	/**
	 * 读出一个对象,Redis
	 * @param key
	 * @return
	 */
	<T> T get(String key, Class<T> clazz);
	<T> T get(String key, Class<T> clazz, String shardKey);
	
	/**
	 * 写入一个有时限的字符串，Redis
	 * @param key
	 * @param value
	 * @param seconds 设定一个key的活动时间（s）
	 */
	String putString(String key,String value,int seconds);
	String putString(String key,String value,int seconds, String shardKey);
	
	/**
	 * 写入一个无时限的字符串，Redis
	 * @param key
	 * @param value
	 */
	String putString(String key,String value);
	String putString(String key,String value, String shardKey);
	
	/**
	 * 读出一个字符串，Redis
	 * @param key
	 * @return
	 */
	String getString(String key);
	String getString(String key,String shardKey);
	
	/**
	 * 写入一个无时限的字符串，Redis的List集合 ，向key这个list添加元素，在尾部/头部添加
	 * @param key
	 * @param entry
	 * @param isR true=尾部，false=头部
	 * @return 推送操作后的列表内的元素的数目
	 */
	Long addToList(String key, String entry, boolean isR);
	Long addToList(String key, String entry, boolean isR, String shardKey);
	
	/**
	 * 写入一个有时限的字符串，Redis的List集合 ，向key这个list添加元素，在尾部/头部添加
	 * @param key
	 * @param entry
	 * @param isR true=尾部，false=头部
	 * @param seconds 设定一个key的活动时间（s）
	 * @return 推送操作后的列表内的元素的数目
	 */
	Long addToList(String key, String entry, boolean isR,int seconds);
	Long addToList(String key, String entry, boolean isR,int seconds, String shardKey);
	
	/**
	 * 写入一个无时限Redis的List集合，元素值为可序列化的对象，向key这个list添加元素，在尾部/头部添加
	 * @param key
	 * @param entry
	 * @param isR true=尾部，false=头部
	 * @param seconds 设定一个key的活动时间（s）
	 * @return 推送操作后的列表内的元素的数目
	 */
	<T> Long addToList(String key, T object, Class<T> clazz, boolean isR);
	<T> Long addToList(String key, T object, Class<T> clazz, boolean isR, String shardKey);
	
	/**
	 * 写入一个有时限Redis的List集合，元素值为可序列化的对象， 向key这个list添加元素，在尾部/头部添加
	 * @param key
	 * @param entry
	 * @param isR true=尾部，false=头部
	 * @param seconds 设定一个key的活动时间（s）
	 * @return 推送操作后的列表内的元素的数目
	 */
	<T> Long addToList(String key, T object, Class<T> clazz, boolean isR,int seconds);
	<T> Long addToList(String key, T object, Class<T> clazz, boolean isR,int seconds, String shardKey);
	
	/**
	 * 读出一个字符串，Redis的List集合，返回并删除名称为key的list中的首/尾元素
	 * @param key
	 * @param isR true=尾部，false=头部
	 * @return
	 */
	String getStringFromList(String key,boolean isR);
	String getStringFromList(String key,boolean isR, String shardKey);
	
	/**
	 * 读出一个可序列化的对象，Redis的List集合，返回并删除名称为key的list中的首/尾元素
	 * @param key
	 * @param isR true=尾部，false=头部
	 * @return
	 */
	<T> T getFromList(String key, Class<T> clazz, boolean isR);
	<T> T getFromList(String key, Class<T> clazz, boolean isR, String shardKey);
	
	/**
	 * Redis的List集合，返回名称为key的list中的start到end的元素列表，性能较差，谨慎使用
	 *  key start end返回列表key中指定区间内的元素，区间以偏移量start和end指定。
	 * 下标(index)参数start和stop都以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。
	 * 也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。 
	 * @return
	 */
	<T> List<T> getFromList(String key, int start, int end, Class<T> clazz);
	<T> List<T> getFromList(String key, int start, int end, Class<T> clazz,String shardKey);
	
	/**
	 * 读出一个字符串List，Redis的List集合，从第几个元素到第几个元素
	 * key start end返回列表key中指定区间内的元素，区间以偏移量start和end指定。
	 * 下标(index)参数start和stop都以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。
	 * 也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。 
	 * @param key
	 * @param start 开始下标
	 * @param end 结束下标
	 * @return
	 */
	List<String> getStringFromList(String key,long start,long end);
	List<String> getStringFromList(String key,long start,long end,String shardKey);
	
	/**
	 * 删除一个key对应的数据
	 * @param key
	 */
	Long remove(String key);
	Long remove(String key,String shardKey);
	
	/**
	 * 检查key是否存在
	 * 
	 * @param key
	 * @return
	 */
	boolean exists(String key);
	boolean exists(String key,String shardKey);
	
	/**
	 * 为给定key设置生命周期
	 * @param key
	 * @param seconds 生命周期 秒为单位
	 */
	void expire(String key, int seconds);
	void expire(String key, int seconds,String shardKey);
	
	/**
	 * 写入一个无时限的Redis的Map，元素值为字符串 ，向key这个map添加元素field
	 * @param key
	 * @param field
	 * @param value
	 * @return 如果字段已经存在，update操作，返回0；如果一个新的字段，insert操作，返回1。
	 */
	Long putToMap(String key, String field, String value);
	Long putToMap(String key, String field, String value,String shardKey);
	
	Long putToMap(String key, String field, String value, int seconds);
	Long putToMap(String key, String field, String value, int seconds,String shardKey);
	
	/**
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	String getStringFromMap(String key, String field);
	String getStringFromMap(String key, String field,String shardKey);
	
	/**
	 * 删除名称为key的hash中键为field的域
	 * @param key
	 * @param field
	 * @return
	 */
	Long delStringFromMap(String key, String field);
	Long delStringFromMap(String key, String field,String shardKey);
	
	/**
	 * 将名称为key的hash中field的value增加integer
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	Long hincrby(String key, String field, long value);
	Long hincrby(String key, String field, long value, String shardKey);
	
	/**
	 * 返回名称为key的hash中元素个数
	 * @param key
	 * @return
	 */
	Long hlen(String key);
	Long hlen(String key, String shardKey);
	
	/**
	 * 名称为key的hash中是否存在键为field的域
	 * @param key
	 * @param field
	 * @return
	 */
	boolean hexists(String key, String field);
	boolean hexists(String key, String field, String shardKey);
	
	/**
	 * Return all the fields and associated values in a hash.
	 * @param key
	 * @return
	 */
	Map<String,String> getMapFromMap(String key);
	Map<String,String> getMapFromMap(String key,String shardKey);
	
	/**
	 * 向名称为key的set中添加元素member
	 * @param key
	 * @param member
	 * @return
	 */
	Long sadd(String key,String member);
	Long sadd(String key,String member,String shardKey);
	Long sadd(String key,String member,String shardKey,int seconds);
	
	/**
	 * 删除名称为key的set中的元素member
	 * @param key
	 * @param member
	 * @return
	 */
	Long srem(String key,String member);
	Long srem(String key,String member,String shardKey);
	
	/**
	 * 返回名称为key的set的所有元素
	 * @param key
	 * @return
	 */
	Set<String> getStrsFromSet(String key);
	Set<String> getStrsFromSet(String key,String shardKey);
	
	/**
	 * 获取所有实际节点的keys
	 * @param pattern
	 * @return
	 */
	Set<String> keys(String pattern);
	
	/**
	 * 自定义hash测试方法，用于性能测试
	 * 
	 *  long start=System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			cache.getPoolTest("zta" + i);
		}
		System.out.println(System.currentTimeMillis()-start);
		
		=2540ms
	 * @param shardKey
	 */
	void getPoolTest(String shardKey);
	
	
	/**
	 * 写入一个有时限、可序列化的对象，Redis
	 * @param key
	 * @param value
	 * @param seconds 设定一个key的活动时间（s）
	 */
	<T> String putList(String key, List<T> value, Class<T> clazz, int seconds);
	<T> String putList(String key, List<T> value, Class<T> clazz, int seconds, String shardKey);
	
	<T> List<T> getList(String key, Class<T> clazz);
	<T> List<T> getList(String key, Class<T> clazz, String shardKey);
	boolean sismember(String key, String member);
	boolean sismember(String key, String member, String shardKey);
	Long scard(String key);
	Long scard(String key, String shardKey);
	
}
