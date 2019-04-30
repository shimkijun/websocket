package com.web.test;

import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;


public class LoggerTest {


	@Test
	@Ignore
	public void logTest() {
		Random ran = new Random(); 
		int ucode =ran.nextInt(8999)+1000;
		System.out.println(ucode);
	}
}
