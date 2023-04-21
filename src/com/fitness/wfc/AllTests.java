package com.fitness.wfc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GroupSessionTest.class, ModifyBookingTest.class, AttendSessionTest.class, MonthlyChampionTest.class,
		MonthlyReportTest.class })
public class AllTests {

}
