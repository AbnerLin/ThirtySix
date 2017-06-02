package com.thirtySix.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/spring/spring-datasource.xml")
@ComponentScan({ "com.thirtySix.service" })
public class TestConfig {

//	@Bean
//	@Scope("singleton")
//	public Buffer getBuffer() {
//		return new Buffer();
//	}

	// @Bean
	// @Scope("singleton")
	// public DBManager getDBManager() {
	// return new DBManager();
	// }

}
