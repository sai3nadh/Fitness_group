package Fitness_Group_V1;

public class ModifyBooking {

	ModifyBooking(){
		
	}
	public boolean cancelBooking() {
		System.out.println("canelling the booking ");
		return true;
	}
	public boolean changeBooking(int from , int to ) {
		System.out.println("the session has been rescheduled successfuly");
		return true;
	}
}