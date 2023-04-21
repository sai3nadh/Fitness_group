package com.fitness.wfc;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttendSessionTest {

	@Test
	public void attendTest() {
		System.out.println("Testing Attend Session method");
		AttendSession as = new AttendSession();
		Boolean attend=as.attendASession();
		assertEquals(attend, true);
		
	}

}
