package fitness_Group_V1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ModifyBookingTest {

	@Test
	public void modifyTest() throws IOException {
		System.out.println("Testing Modify Booking method");
		ModifyBooking mb =new ModifyBooking();
		//C005 - 2 - zumba - 13 - 4
		Boolean b =mb.changeBooking();
		assertEquals(b,false);
		
	}
	
	@Test
	public void cancelTest() throws IOException {
		System.out.println("Testing Cancel booking method");
		ModifyBooking mb =new ModifyBooking();
		//C005 - 2 - zumba - 13 - 4
		Boolean b =mb.cancelBooking();
		assertEquals(b,false);
		
	}

}
