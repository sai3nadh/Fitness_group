package fitness_Group_V1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttendSessionTest {

	@Test
	public void attendTest() {
		AttendSession as = new AttendSession();
		Boolean attend=as.attendASession();
		assertEquals(attend, true);
		//fail("Not yet implemented");
	}

}