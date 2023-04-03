package com.zkm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ZkmRescouceApplicationTests {


	@Test
	void contextLoads() {
		System.out.println(new BCryptPasswordEncoder().matches("456", "2a$10$Kf/wtrWbNmUyPSqmWIH0w.8u0IBDp.itvSHjqbnfd8dfn16a8mpge"));
	}

}
