package com.fitness.wfc;

import static org.junit.Assert.*;

import org.junit.Test;

public class MonthlyChampionTest {

	@Test
	public void monthChampTest() {
		System.out.println("Testing Monthly Champion Report");
		MonthlyChampion mcr =new MonthlyChampion();
		//C005 - 2 - zumba - 13 - 4
		Boolean mr =mcr.monthlyChamReport(03);;
		assertEquals(mr,true);
		
		//fail("Not yet implemented");
	}

}
