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
		System.out.println("Enter Customer ID");
		String custID= sc.next();
		CustomerInfo cinfo= new CustomerInfo();
		System.out.println("is valid"+cinfo.isCustValid(custID));
		while(!cinfo.isCustValid(custID)) {
			System.out.println("Enter Valid Customer ID");
			custID= sc.next();
			System.out.println("is valid"+cinfo.isCustValid(custID));

		}
		String csvFile="sample.csv";
		
		//System.out.println("enter Customer ID");
		String customerID= custID;//sc.next();
		System.out.println("Avaiable bookings");
		
		System.out.println("**********************");
		System.out.println("**********************");
		CSVOperatins csvoper = new CSVOperatins();
		// call method and get records with particular customer ID
		List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
		System.out.println("Booking ID \t\t Fitness Type ");
		System.out.println("=================================");
		int count=0;
		for (String custData : testList)
		{

			if(custData.split(",")[6].equals("booked")) {
				//System.out.println("custData Values values"+custData);
				System.out.println(custData.split(",")[0]+"\t\t"+custData.split(",")[7]);
				count++;
			}
		}
		System.out.println("=================================");
		if(count==0) {
			System.out.println("You don't have any classes to attend");
			return false;
		}
		System.out.println("Enter Booking ID of class to attend");
		String BookingID = sc.next();
		String compare[],custDataArr[]=null;
		for (String custData : testList)
		{
			System.out.println("custtttdataa"+custData);
			compare= custData.split(",");
			if (compare[0].equals(BookingID)) {
			//System.out.println("custData Values values"+custData);
				custDataArr=custData.split(",");
				break;// element found
			}
		}
		//System.out.println("custDataArrrrr-->"+custDataArr.toString());
		//System.out.println("valuess in stringggggg");
		/*
		 * for (String s:custDataArr) { System.out.println(s); }
		 */
		System.out.println("enter Session Rating rangin 1 - 5");
		int Rating = sc.nextInt();
		System.out.println("enter review for the session");
		String review = "";//sc.next();
		review = sc.nextLine();
		review = review +""+sc.nextLine();
			
		System.out.println("string review-->"+review);
		int a = sc.nextInt();
		System.out.println("review is"+review);
		 //booking_ID, review , amount, class name, month
		String attendData = String.valueOf(Rating)+"," +review+","+
		 custDataArr[0]+","+custDataArr[1]+","+custDataArr[2]+","
				 +custDataArr[3]+","+custDataArr[6]+","+custDataArr[7];
		//System.out.println("Attendddd Data is---"+attendData);
		attendData =  custDataArr[7] +","+ custDataArr[5]+","+review+","+ Rating+","+"attended"
						+","+custDataArr[3]+","+custDataArr[4];
		
		//System.out.println("attendData is "+ attendData);
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
