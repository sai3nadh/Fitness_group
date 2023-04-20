package com.fitness.wfc;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AttendSession {
	
	AttendSession(){
	}
	String customerID;
	String BookingID,review;
	int Rating;
	public boolean attendASession() {
		/***
		 * customer ID
		 * check available classes
		 * ask for BookingID to attend
		 * then change booking status to attended
		 * write it to attend csv file
		 * 
		 */
		String csvFile="wfc_booking.csv";
		
		String compare[],custDataArr[]=null;
		
		Scanner sc = new Scanner(System.in);	
		System.out.println("Enter Customer ID (sample input: C001,C002,C003,....C014,C015)");
		String custID= sc.next().toUpperCase();
		CustomerInfo cinfo= new CustomerInfo();
		//System.out.println("is valid"+cinfo.isCustValid(custID));
		while(!cinfo.isCustValid(custID)) {
			System.out.println("Enter Valid Customer ID(sample input: C001,C002,C003,....C014,C015)");
			custID= sc.next().toUpperCase();
			System.out.println("is valid"+cinfo.isCustValid(custID));

		}
		customerID= custID;
		
		
		System.out.println("Avaiable bookings");
		
		System.out.println("**********************");
		System.out.println("**********************");
		CSVController csvoper = new CSVController();
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
		// return to main menu if you don't have any existing classes
		if(count==0) {
			System.out.println("You don't have any classes to attend");
			return false;
		}
		System.out.println("Enter Booking ID of class to attend");
		BookingID = sc.next();
		
		for (String custData : testList)
		{
			compare= custData.split(",");
			if (compare[0].equals(BookingID)) {
				custDataArr=custData.split(",");
				break;// element found
			}
		}

		System.out.println("enter Session Rating rangin 1 - 5"
				+ "\n(1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied)");
		Rating = sc.nextInt();
		 while (Rating < 1 || Rating > 5) {
			  System.out.println("enter valid Rating (range between: 1 to 5)"); 
			  Rating = sc.nextInt(); 
		  }
		System.out.println("enter review for the session");
		review = "";//sc.next();
		review = sc.nextLine();
		review = review +""+sc.nextLine();
			

		 //booking_ID, review , amount, class name, month
		String attendData = String.valueOf(Rating)+"," +review+","+
		 custDataArr[0]+","+custDataArr[1]+","+custDataArr[2]+","
				 +custDataArr[3]+","+custDataArr[6]+","+custDataArr[7];
		
		attendData =  custDataArr[7] +","+ custDataArr[5]+","+review+","+ Rating+","+"attended"
						+","+custDataArr[3]+","+custDataArr[4]+","+custDataArr[8];
		

		
		try {
			csvoper.updateAttendStatus(BookingID);
			csvoper.attendSession(BookingID, attendData);
			//custDataArr[7] +"," custDataArr[5]+","+Review+","+ Rating+","+"attended";
			//BookingID	class_name	amount	review	rating	status

		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
		
		System.out.println("Attend a session is successful ");
		return true;
	}

}
