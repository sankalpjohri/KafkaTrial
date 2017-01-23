package com.coviam.kafkaTrial.DAO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class OtpDao {
	JedisPool jedisPool = null;
	Jedis connection = null;
	
	private OtpDao(){
		
	}
	
	public static OtpDao getInstance(){
		return OtpInstance.INSTANCE;
	}
	
	private Jedis getConnection(JedisPool resourcePool) throws Exception{
		return resourcePool.getResource();
	}
	
	public String getValue(String key){
		String value = "";
		try{
			jedisPool = getResourcePool("localhost", 6379);
			connection = getConnection(jedisPool);
			value = connection.get(key);
		} catch (Exception e){
			if (connection != null){
				returnResource(jedisPool, connection);
				connection = null;
			}
		} finally {
			jedisPool.destroy();
			connection.close();
		}
		return value;
	}
	
	public void delValue(String key){
		try{
			jedisPool = getResourcePool("localhost", 6379);
			connection = getConnection(jedisPool);
			connection.del(key);
		} catch (Exception e){
			if (connection != null){
				returnResource(jedisPool, connection);
				connection = null;
			}
		} finally {
			jedisPool.destroy();
			connection.close();
		}
	}
	
	private void returnResource(JedisPool jedisPool, Jedis jedisConnection){
		jedisPool.returnBrokenResource(jedisConnection);
	}
	
	private JedisPool getResourcePool(String location, int port) throws Exception{
		return new JedisPool(location, port);
	}
	
	private static class OtpInstance{
		private static final OtpDao INSTANCE = new OtpDao();
		public OtpInstance() {
			// TODO Auto-generated constructor stub
		}
	}
}
