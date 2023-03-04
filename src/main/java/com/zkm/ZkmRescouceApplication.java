package com.zkm;

import com.zkm.server.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan(value = "com.zkm.mapper")
public class ZkmRescouceApplication implements CommandLineRunner {


	@Autowired
	private NettyServer nettyServer;

	public static void main(String[] args) {
		SpringApplication.run(ZkmRescouceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		nettyServer.start();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowCredentials(true).allowedOriginPatterns("*");
			}
		};
	}

}
