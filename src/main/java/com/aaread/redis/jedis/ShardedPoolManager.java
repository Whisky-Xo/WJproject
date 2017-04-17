package com.aaread.redis.jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;

import com.aaread.redis.util.HashAlgorithm;

/**
 * jedis pool 管理类，shardding实现
 * @title ShardedPoolManager
 * @description TODO 
 * @author Clark
 * @date 2014年7月8日
 * @version 1.0
 */
public class ShardedPoolManager {
	private Logger log = LoggerFactory.getLogger(ShardedPoolManager.class);
	public static final int FAIL_OVER_SENTINEL = 1;

	public static final int FAIL_OVER_NONE = 0;

	public static final int FAIL_OVER_KEEPALIVED = 2;

	public static final int NATIVE_HASH = 0; // 求余哈希

	public static final int CONSISTENT_HASH = 1; // 一致性哈希

	public static final String REDIS_WEIGHT = "redisWeight";

	public static final String POOL_SINGEL_KEY = "singel";

	private TreeMap<Long, String> consistentBuckets;

	private Map<String, Pool> socketPool;

	private List<String> buckets;

	private String[] servers;

	private Integer[] weights;

	private TreeMap<Integer, Map<String,String>> serverNodes;

	private int totalWeight = 0;

	boolean initialized = false;

	private int hashingAlg = NATIVE_HASH;

	private List<Map<String, String>> confList;

	private Integer failOver;

	public ShardedPoolManager(List<Map<String, String>> confList, int failOver,
			int hashingAlg) {
		this.confList = confList;
		this.failOver = failOver;
		this.hashingAlg = hashingAlg;
		log.info("++++++++ confList : " + confList + ",failOver : " + failOver
				+ ",hashingAlg : " + hashingAlg);
	}

	public void initialize() {
		socketPool = new HashMap<String, Pool>(confList.size());
		serverNodes = new TreeMap<Integer, Map<String,String>>();
		servers = new String[confList.size()];
		weights = new Integer[confList.size()];
		for (int i = 0; i < confList.size(); i++) {
			Map<String, String> confMap = confList.get(i);
			servers[i] = confMap.get(Pool.REDIS_SERVER);
			weights[i] = confMap.get(Pool.REDIS_WEIGHT) != null ? Integer
					.parseInt(confMap.get(Pool.REDIS_WEIGHT)) : null;
			if (servers[i] == null) {
				log.error("++++ no server found," + confMap.toString());
				throw new IllegalStateException("++++ no server found");
			}

			if (this.hashingAlg == NATIVE_HASH) {
				if (weights[i] == null) {
					log.error("++++ no weight found," + confMap.toString());
					throw new IllegalStateException("++++ no weight found");
				}
				serverNodes.put(weights[i], confMap);
			}
		}
		if (this.hashingAlg == CONSISTENT_HASH) {
			populateConsistentBuckets();
		} else {
			populateBuckets();
		}
		this.initialized = true;
	}

	private void populateBuckets() {
		buckets = new ArrayList<String>();
		Pool jp = null;
		int lastWeight = 0;
		log.info("+++ serverNodes : " + serverNodes);
		for (Entry<Integer, Map<String,String>> entry : serverNodes.entrySet()) {
			for (int k = lastWeight; k < entry.getKey(); k++) {
				buckets.add(entry.getValue().get(Pool.REDIS_SERVER));
			}

			if (this.failOver == FAIL_OVER_SENTINEL) {
				jp = new SentinelPool(entry.getValue());
			} else {
				jp = new CommonPool(entry.getValue());
			}
			socketPool.put(entry.getValue().get(Pool.REDIS_SERVER), jp);
			lastWeight = entry.getKey();
		}
		log.info("+++ populateBuckets list : " + buckets);
	}

	private void populateConsistentBuckets() {
		consistentBuckets = new TreeMap<Long, String>();
		Pool jp = null;
		// MessageDigest md5 = MD5.get();
		if (this.totalWeight <= 0 && this.weights != null) {
			for (int i = 0; i < this.weights.length; i++)
				this.totalWeight += (this.weights[i] == null) ? 1
						: this.weights[i];
		} else if (this.weights == null) {
			this.totalWeight = this.servers.length;
		}

		for (int i = 0; i < servers.length; i++) {
			int thisWeight = 1;
			if (this.weights != null && this.weights[i] != null)
				thisWeight = this.weights[i];

			double factor = Math.floor(((double) (40 * this.servers.length * thisWeight)) / (double) this.totalWeight);
			
			log.debug("factor=" + factor); 
			for (long j = 0; j < factor; j++) {
				byte[] d = HashAlgorithm.computeMd5((servers[i] + "-" + j));
				for (int h = 0; h < 4; h++) {
					Long k = ((long) (d[3 + h * 4] & 0xFF) << 24)
							| ((long) (d[2 + h * 4] & 0xFF) << 16)
							| ((long) (d[1 + h * 4] & 0xFF) << 8)
							| ((long) (d[0 + h * 4] & 0xFF));

					consistentBuckets.put(k, servers[i]);
					log.debug("k="+k+",server="+servers[i]);
				}
			}

			if (this.failOver == FAIL_OVER_SENTINEL) {
				jp = new SentinelPool(confList.get(i));
			} else {
				jp = new CommonPool(confList.get(i));
			}
			socketPool.put(servers[i], jp);
			log.debug("+++ confList" + i + " : " + confList.get(i));
		}
		log.info("+++consistentBuckets=" + consistentBuckets.toString());
		log.info("+++ consistentBuckets.size : " + consistentBuckets.size());
	}
	
	public Map<String, Pool> getJedisPool(){
		return socketPool;
	}

	public JedisPool getJedisPool(String key) {
		if (!this.initialized) {
			log.error("attempting to get SockIO from uninitialized pool!");
			return null;
		}

		int size = 0;
		if ((this.hashingAlg == CONSISTENT_HASH && consistentBuckets.size() == 0)
				|| (buckets != null && (size = buckets.size()) == 0))
			return null;
		else if (size == 1) {
			JedisPool sock = (this.hashingAlg == CONSISTENT_HASH) ? socketPool
					.get(consistentBuckets.get(consistentBuckets.firstKey()))
					.getJedisPool() : socketPool.get(buckets.get(0))
					.getJedisPool();

			return sock;
		}

		Set<String> tryServers = new HashSet<String>(Arrays.asList(servers));
		long bucket = getBucket(key, null);
//		log.info("bucket===" + bucket);
		String server = (this.hashingAlg == CONSISTENT_HASH) ? consistentBuckets
				.get(bucket) : buckets.get((int) bucket);
		while (!tryServers.isEmpty()) {
			Pool jedisPool = socketPool.get(server);
			if (jedisPool != null) {
				log.debug("+++ shard redis name=" + jedisPool.getRedisName());
				return jedisPool.getJedisPool();
			}

			tryServers.remove(server);
			if (tryServers.isEmpty())
				break;

			int rehashTries = 0;
			while (!tryServers.contains(server)) {
				String newKey = new StringBuffer().append(rehashTries).append(key).toString();
				bucket = getBucket(newKey, null);
				server = (this.hashingAlg == CONSISTENT_HASH) ? consistentBuckets.get(bucket) : buckets.get((int) bucket);
				rehashTries++;
			}
		}
		return null;
	}

	private final long getBucket(String key, Integer hashCode) {
		long hc = getHash(key, hashCode);

		if (this.hashingAlg == CONSISTENT_HASH) {
			return findPointFor(hc);
		} else {
			long bucket = hc % buckets.size();
			if (bucket < 0)
				bucket *= -1;
			return bucket;
		}
	}

	private final long getHash(String key, Integer hashCode) {

		if (hashCode != null) {
			if (hashingAlg == CONSISTENT_HASH)
				return hashCode.longValue() & 0xffffffffL;
			else
				return hashCode.longValue();
		} else {
			switch (hashingAlg) {
			case NATIVE_HASH:
				return (long) HashAlgorithm.CRC32_HASH.hash(key);
			case CONSISTENT_HASH:
				return HashAlgorithm.KETAMA_HASH.hash(key);
			default:
				hashingAlg = NATIVE_HASH;
				return (long) key.hashCode();
			}
		}
	}

	private final Long findPointFor(Long hv) {
		SortedMap<Long, String> tmap = this.consistentBuckets.tailMap(hv);
		return (tmap.isEmpty()) ? this.consistentBuckets.firstKey() : tmap.firstKey();
	}

	public void destory() {
		log.info("+++ SockIO pool destory!");
		if (socketPool != null) {
			for (Entry<String, Pool> entryPool : socketPool.entrySet()) {
				Pool jedisPool = entryPool.getValue();
				if (jedisPool != null) {
					jedisPool.destory();
				}
			}
			socketPool.clear();
		}
		if (consistentBuckets != null) {
			consistentBuckets.clear();
		}
		if (buckets != null) {
			buckets.clear();
		}
		initialized = false;
	}

}
