package com.aaread.redis.sentinels;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.exceptions.JedisDataException;

/**
 * Sentinel 外层实例类
 * @author 张树伟
 * 2013-8-23
 */
public class Sentinel {

	private SentinelClient client;

	public Sentinel(final String host, final int port) {
		client = new SentinelClient(host, port);
	}
	
	/**
     * <pre>
     * redis 127.0.0.1:26381> sentinel get-master-addr-by-name mymaster
     * 1) "127.0.0.1"
     * 2) "6379"
     * </pre>
     * 
     * @param masterName
     * @return two elements list of strings : host and port.
     */
	public List<String> getMasterIp(String sentinelMonitor) {
		checkIsInMulti();
		client.getMasterIp(sentinelMonitor);
		return client.getMultiBulkReply();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getSlaves(String sentinelMonitor) {
		checkIsInMulti();
		client.getSlaves(sentinelMonitor);
		List<Object> list = client.getObjectMultiBulkReply();
		List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
		for(Object obj : list){
			Map<String,String> mSlave = new HashMap<String,String>();
			List<byte[]> slave = (List<byte[]>)obj;
			for(int i=0 ; i< slave.size()/2 ; i++){
				mSlave.put(new String(slave.get(i*2)), new String(slave.get(i*2+1)));
			}
			ret.add(mSlave);
		}
		return ret;
	}
	
	/**
     * <pre>
     * redis 127.0.0.1:26381> sentinel reset mymaster
     * (integer) 1
     * </pre>
     * 
     * @param pattern
     * @return
     */
    public Long reset(String pattern) {
    	checkIsInMulti();
    	client.sentinelReset(pattern);
    	return client.getIntegerReply();
    }

	protected void checkIsInMulti() {
		if (client.isInMulti()) {
			throw new JedisDataException(
					"Cannot use Sentinel when in Multi. Please use JedisTransaction instead.");
		}
	}
	
	public Object bytes2Object(byte[] objBytes) throws Exception {
		if (objBytes == null || objBytes.length == 0) {
			return null;
		}
		ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
		ObjectInputStream oi = new ObjectInputStream(bi);
		Object obj = oi.readObject();
		bi.close();
		oi.close();
		return obj;
	}
	
	public static void main(String args[]){
		Sentinel sentinel = new Sentinel("172.16.10.116",26379);
		try{
			System.out.println(sentinel.getSlaves("mymaster").toString());

		}catch(Exception e){
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
}
