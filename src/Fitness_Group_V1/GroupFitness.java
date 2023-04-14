package fitness_Group_V1;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
public class GroupFitness {


	public static void main(String[] args) throws IOException {
		GroupSession gs = new GroupSession() ;
		ModifyBooking mb = new ModifyBooking();
		AttendSession as = new AttendSession();
		MonthlyReport mr = new MonthlyReport();
		MonthlyChampion mcr= new MonthlyChampion();
		try (Scanner operation = new Scanner(System.in)) {
			
			
			// the main functionalities 
			System.out.println("*******************************************");
			System.out.println("1) Book a Group Session ");
			System.out.println("2) Change / Cancel a Session ");
			System.out.println("3) Attend a Lession  ");
			System.out.println("4) Monthly Lession Report ");
			System.out.println("5) Monthly Chapion Fitness Report ");
			System.out.println("6 Exit System");
			System.out.println("*******************************************");
			System.out.println(" Choose the operation to perform ");

			System.out.println(" Choose the operation to perform ");
			System.out.println("ENter Operation ID:");
			// read the operation to perform  
			int option = operation.nextInt();
			System.out.println("you have choosen "+option);
			while (option!=6) {
				switch (option) {
				case 1: 
					System.out.println("call the book a group session funciton or class");
					gs.bookASession();
					break;
				case 2: 
					
					System.out.println("1-Change Booking \n2-Cancel booking\n"
							+ "Choose option to perform");
					int op = operation.nextInt();
					if(op==1) {
						mb.changeBooking();
					}//skipping cancel for testing purpose
					else if(op==2) {
						mb.cancelBooking();
					}
					break;
				case 3: 
					System.out.println("call Attend a Lession");
					as.attendASession();
					break;
				case 4: 
					System.out.println("call Monthly Lession Report");
					mr.monthLessonReport();
					break;
				case 5: 
					System.out.println("call Monthly Chapion Fitness Report");
					mcr.monthlyChamReport();
					break;
				case 6: System.out.println("exit system");
				break;
				}
				if (option!=6) {
					System.out.println("entered to main menu please choose option");
					System.out.println("*******************************************");
					System.out.println("1) Book a Group Session ");
					System.out.println("2) Change / Cancel a Session ");
					System.out.println("3) Attend a Lession  ");
					System.out.println("4) Monthly Lession Report ");
					System.out.println("5) Monthly Chapion Fitness Report ");
					System.out.println("6 Exit System");
					System.out.println("*******************************************");
					System.out.println(" Choose the operation to perform ");
					option =operation.nextInt();
				}
			}
			System.out.println("Exit Sucessful!!!");
		}
	}


}
