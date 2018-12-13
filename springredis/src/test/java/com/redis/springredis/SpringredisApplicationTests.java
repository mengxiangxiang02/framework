package com.redis.springredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringredisApplicationTests {

	@Autowired
	private RedisUtils redisUtils;


	@Test
	public void contextLoads() {
		String key = (String)redisUtils.get("TOKEN:04c84c5158ddaa86230a582cee64614f");
		Map entries = redisUtils.getRedisTemplate().opsForHash().entries("PROMOTER:0000000003");
		System.out.println(entries);
	}

}

