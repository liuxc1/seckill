package com.liuxc.www;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring-boot启动类
 * 在tomcat里面发布启动类必须继承SpringBootServletInitializer 该覆写SpringApplicationBuilder()方法
 */

@SpringBootApplication
@EnableTransactionManagement//开启注解支持
public class StartSpringBootMain extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StartSpringBootMain.class);
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(StartSpringBootMain.class, args);
	}
}