package com.fitness.wfc;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ModifyBookingTest {

	@Test
	public void modifyTest() throws IOException {
		System.out.println("Testing Modify Booking method");
		ModifyBooking mb =new ModifyBooking();
		
		Boolean b =mb.changeBooking();
		assertEquals(b,true);
		
	}
	
	@Test
	public void cancelTest() throws IOException {
		System.out.println("Testing Cancel booking method");
		ModifyBooking mb =new ModifyBooking();
		
		Boolean b =mb.cancelBooking();
		assertEquals(b,true);
		
	}

}
