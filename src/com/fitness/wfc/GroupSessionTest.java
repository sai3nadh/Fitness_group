package com.fitness.wfc;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupSessionTest {

	@Test
	public void bookSession() {
		System.out.println("Testing Book a session method");
		GroupSession gs = new GroupSession();
		boolean g = gs.bookASession();
		assertEquals(g, true);
		
	}

}
