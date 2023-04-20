package com.fitness.wfc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AttendSessionTest.class, GroupSessionTest.class, ModifyBookingTest.class, MonthlyChampionTest.class,
		MonthlyReportTest.class, TestClass.class })
public class AllTests {

}
