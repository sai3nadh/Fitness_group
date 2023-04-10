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
		CustomerInfo cinfo= new CustomerInfo();
		try (Scanner operation = new Scanner(System.in)) {
			
			/*
			 * // To take the input Scanner scanner = new Scanner(System.in);
			 * System.out.println("Enter the Date (dd-MMM-yyyy)");
			 * 
			 * String date = scanner.nextLine();
			 * System.out.println("date is:"+date.toString());
			 * 
			 * SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MMM-YYYY"); Date
			 * date2=null; try { //Parsing the String date2 = dateFormat.parse(date);
			 * System.out.println("Day is "+date2.toString().substring(3)); } catch
			 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
			 * } System.out.println("date entered is"+date2);
			 * 
			 */
			
			/*
			 * SimpleDateFormat dateFormat = new SimpleDateFormat("MddHHmms");
			 * System.out.println(dateFormat.format(new Date()));
			 */
		    
			System.out.println("Enter User ID");
			String custID= operation.next();
			System.out.println("is valid"+cinfo.isCustValid(custID));
			while(!cinfo.isCustValid(custID)) {
				System.out.println("Enter Valid Customer ID");
				custID= operation.next();
				System.out.println("is valid"+cinfo.isCustValid(custID));

			}
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
					System.out.println(gs.bookASession(custID));
					break;
				case 2: 
					System.out.println("call the Change \\ Cancel a Session");
					System.out.println(mb.changeBooking(custID));
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
				case 6: System.out.println("exit system");
				break;
				}
				if (option!=6) {
					System.out.println("entered to main menu please choose option");
					option =operation.nextInt();
				}
			}
			System.out.println("Exit Sucessful!!!");
		}
	}


}
