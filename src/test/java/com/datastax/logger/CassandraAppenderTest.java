package com.datastax.logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraAppenderTest {

	private static Logger logger = LoggerFactory.getLogger(CassandraAppenderTest.class); 
	
	@Test
	public void testAppender(){
		logger.info("Hello");
	}
	
}
