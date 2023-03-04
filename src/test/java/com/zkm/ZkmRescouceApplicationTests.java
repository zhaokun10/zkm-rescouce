package com.zkm;

import com.zkm.mapper.FriendMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZkmRescouceApplicationTests {

	@Autowired
	FriendMapper friendMapper;

	@Test
	void contextLoads() {
		 friendMapper.getAllFriendByUserId(1).forEach(user-> System.out.println( user.toString()));

	}

}
