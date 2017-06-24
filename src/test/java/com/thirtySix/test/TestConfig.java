package com.thirtySix.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/spring/spring-datasource.xml")
@ComponentScan({ "com.thirtySix.service", "com.thirtySix.core" })
public class TestConfig {

}
