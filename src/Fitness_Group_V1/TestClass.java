package fitness_Group_V1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import junit.framework.Assert;

public class TestClass {

	@Test
	public void test() {
		System.out.println("test method");
		//fail("Not yet implemented");
		MonthlyReport mr = new MonthlyReport();
		
		mr.monthLessonReport();
		
	}
	
	@Test
	public void BookClass() {
		System.out.println("Testing Booking a class method");
		GroupSession gs =new GroupSession();
		//C005 - 2 - zumba - 13 - 4
		Boolean b =gs.bookASession();
		assertEquals(b,false);
	}
	
	@Test
	public void MonthChampReport() {
		System.out.println("Testing Monthly Champion Report method");
		MonthlyChampion mcr =new MonthlyChampion();
		//C005 - 2 - zumba - 13 - 4
		Boolean mr =mcr.monthlyChamReport();;
		assertEquals(mr,true);
	}
	@Test
	public void MonthlyReport() {
		System.out.println("Testing Monthly Report method");
		MonthlyChampion mcr =new MonthlyChampion();
		//C005 - 2 - zumba - 13 - 4
		Boolean mr =mcr.monthlyChamReport();;
		assertEquals(mr,true);
	}
	@Test
	public void cancel() throws IOException {
		System.out.println("Testing Cancel booking method");
		ModifyBooking mb =new ModifyBooking();
		//C005 - 2 - zumba - 13 - 4
		Boolean b =mb.cancelBooking();
		assertEquals(b,false);
	}
	
	@Test
	public void modify() throws IOException {
		System.out.println("Testing Modify Booking method");
		ModifyBooking mb =new ModifyBooking();
		//C005 - 2 - zumba - 13 - 4
		Boolean b =mb.changeBooking();
		assertEquals(b,false);
	}
	
	

}
