package com.thirtySix.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thirtySix.core.Buffer;
import com.thirtySix.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService = null;
	
	@Autowired
	private Buffer buffer = null;
	
	
}
