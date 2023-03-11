package fitness_Group_V1;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
			System.out.println("*******************************************");
			System.out.println(" Choose the operation to perform ");

			
			// read the operation to perform  
			int variable = operation.nextInt();
			System.out.println("you have choosen "+variable);
			
			switch (variable) {
			case 1: 
				System.out.println("call the book a group session funciton or class");
				System.out.println(gs.bookASession());
				break;
			case 2: 
				System.out.println("call the Change \\ Cancel a Session");
				System.out.println(mb.changeBooking(9,12));
				//skipping cancel for testing purpose
				//System.out.println(mb.cancelBooking());
				break;
			case 3: 
				System.out.println("call Attend a Lession");
				System.out.println(as.bookASession());
				break;
			case 4: 
				System.out.println("call Monthly Lession Report");
//				System.out.println(gs.bookASession());
				mr.monthLessonReport();
				break;
			case 5: 
				System.out.println("call Monthly Chapion Fitness Report");
				//System.out.println(gs.bookASession(4, "Yoga"));
				mcr.monthlyChamReport();
				break;
			}
		}
	}

}
