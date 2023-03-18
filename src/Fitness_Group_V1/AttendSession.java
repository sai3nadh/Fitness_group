package fitness_Group_V1;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AttendSession {
	
	AttendSession(){
	}
	
	public boolean bookASession() {
		/***
		 * customer ID
		 * check available classes
		 * ask for BookingID to attend
		 * then change booking status to attended
		 * write it to attend csv file
		 * 
		 */
		Scanner sc = new Scanner(System.in);	
		String csvFile="C:\\Users\\HP\\Desktop\\sample.csv";
		
		System.out.println("enter Customer ID");
		String customerID= sc.next();
		System.out.println("avaiable bookings");
		
		System.out.println("**********************");
		System.out.println("**********************");
		CSVOperatins csvoper = new CSVOperatins();
		// call method and get records with particular customer ID
		List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
		for (String custData : testList)
		{
			System.out.println("custData Values values"+custData);
		}
			
		System.out.println("Enter Booking ID of class to attend");
		String BookingID = sc.next();
		String compare[],custDataArr[]=null;
		for (String custData : testList)
		{
			compare= custData.split(",");
			if (compare[0].equals(BookingID)) {
			System.out.println("custData Values values"+custData);
				custDataArr=custData.split(",");
				break;// element found
			}
		}
		System.out.println("custDataArrrrr-->"+custDataArr.toString());
		System.out.println("enter Session Rating rangin 1 - 5");
		int Rating = sc.nextInt();
		System.out.println("enter review for the session\n");
		String Review = sc.next();
		System.out.println("review is"+Review);
		 //booking_ID, review , amount, class name, month
		String attendData = String.valueOf(Rating)+"," +Review+","+
		 custDataArr[0]+","+custDataArr[1]+","+custDataArr[2]+","
				 +custDataArr[3]+","+custDataArr[6]+","+custDataArr[7];
		System.out.println("Attendddd Data is---"+attendData);
		attendData =  custDataArr[7] +","+ custDataArr[5]+","+Review+","+ Rating+","+"attended"
						+","+custDataArr[3];
		
		System.out.println("attendData is "+ attendData);
		try {
			csvoper.updateAttendStatus(BookingID);
			csvoper.attendSession(BookingID, attendData);
			//custDataArr[7] +"," custDataArr[5]+","+Review+","+ Rating+","+"attended";
			//BookingID	class_name	amount	review	rating	status

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Attend a session is successful ");
		return true;
	}

}
