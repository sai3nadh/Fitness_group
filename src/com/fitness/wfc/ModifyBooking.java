package com.fitness.wfc;

//import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ModifyBooking {

	ModifyBooking(){
		
	}
	String csvFile="wfc_booking.csv";
	Scanner sc= new Scanner(System.in);
	
	public boolean cancelBooking() throws IOException {
		Scanner read= new Scanner(System.in);
		System.out.println("Enter Customer ID: (sample input: C001,C002,C003,....C014,C015)");
		String custID= read.next().toUpperCase();
		CustomerInfo cinfo= new CustomerInfo();
		//System.out.println("is valid"+cinfo.isCustValid(custID));
		while(!cinfo.isCustValid(custID)) {
			System.out.println("Enter Valid Customer ID: (sample input: C001,C002,C003,....C014,C015)");
			custID= read.next().toUpperCase();
			System.out.println("is valid"+cinfo.isCustValid(custID));

		}

		String customerID= custID;
			
				try {
				
					System.out.println("**********************");
					System.out.println("**********************");
					CSVController csvoper = new CSVController();
					// call method and get records with particular customer ID
					List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
					
					System.out.println("available sessions on customer booking");
					System.out.println("================================");
					
					System.out.println("fitID\t\t type\t status");
					System.out.println("================================");
					int count = 0;
					for (String add : testList)
					{
						 
						if(add.split(",")[6].equals("booked")) {
							System.out.println(add.split(",")[0]+"\t"+
												add.split(",")[7]+"\t"+
												add.split(",")[6]);
						count++;
						}
					}
					
					System.out.println("================================");
					
					// take  user input --> class id
					if(count==0) {
						System.out.println("You don't have any available bookings");
						return false;
					}
					System.out.println("enter Fitness ID ");
					String cancel_id=sc.next();
					count=0;
					String class_id="";
					// to get the existing count 
					for (String add : testList)
					{
						//System.out.println(add);
						// 
						if(add.split(",")[0].equals(cancel_id)) {
							count = -Integer.valueOf( add.split(",")[4]);
							class_id = add.split(",")[2];
							break;
						}
					}
					
					
					boolean cancel=csvoper.updateCancelStatus(cancel_id);
					if(cancel) {
						//update count in fitness classes
						
						csvoper.updaterecord(class_id, count);
						System.out.println("Cancellation Successful");
					}
					else {
						System.out.println("Failed Cancellation");
						return false;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					return false;
				}

			
		
		System.out.println("Cancelled Class Successfully!!!");
		return true;
	}
	public boolean changeBooking() throws IOException {
		/**
		 * cust id
		 * 	fetch existing classes
		 * 	ask user input for class to change
		 * 	
		 * 	input new classes-- want to join
		 * 	------
		 * 		check availability
		 * 	
		 * 		if (available)
		 * 			change status of existing record
		 * 			enroll for new class
		 * 			update the current count value
		 * 	----------
		 * 		else(no space)
		 * 			change is not successful
		 * 		
		 * 	----
		 * 
		 */
		
		// reschedule session
		// check available session on particular candidate
		System.out.println("**********************");
		System.out.println("**********************");

		Scanner read = new Scanner(System.in);
		System.out.println("Enter Customer ID: (sample input: C001,C002,C003,....C014,C015)");
		String custID= read.next().toUpperCase();
		CustomerInfo cinfo= new CustomerInfo();
		//System.out.println("is valid"+cinfo.isCustValid(custID));
		while(!cinfo.isCustValid(custID)) {
			System.out.println("Enter Valid Customer ID: (sample input: C001,C002,C003,....C014,C015)");
			custID= read.next().toUpperCase();
			System.out.println("is valid"+cinfo.isCustValid(custID));

		}
		String customerID= custID;
		
		CSVController csvoper = new CSVController();
		// call method and get records with particular customer ID
		List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
		System.out.println("available sessions on customer booking");
		System.out.println("------------------------------------------");
		System.out.println("fitID\t\t type\t status");
		System.out.println("------------------------------------------");
		int cont=0;
		for (String add : testList)
		{
			
			if(add.split(",")[6].equals("booked")) {
				System.out.println(add.split(",")[0]+"\t"+
									add.split(",")[7]+"\t"+
									add.split(",")[6]);
				cont++;
			}
		}
		if(cont==0) {
			System.out.println("You don't have any bookings");
			return false;
		}
		System.out.println("------------------------------------------");
		// take  user input --> class id
		System.out.println("enter Fitness ID ");
		String change_id=sc.next();
		int updt_count=0;
		for (String add : testList)
		{
			
			if(add.split(",")[0].equals(change_id)) {
				updt_count = -Integer.valueOf(add.split(",")[4]);
				break;
			}
		}
			// cancel class
		// show available sessions to change booking-- new class
		String classesCSVFile="fitness_classes.csv";
		List<String> ClassesList = csvoper.getClassRecords(",", classesCSVFile);
		System.out.println("\n\nAvailable Classess sessions on customer booking");
		for (String clist : ClassesList)
		{
			System.out.println(clist);
		}
		// ask for customer to choose class
		System.out.println("enter your classID to join");
		String chooseClass= sc.next();
		//get class corresponding row to append
		String record = "";
		for (String clist : ClassesList)
		{
			String[] bookingID=clist.split(",");
			if (bookingID[0].equals(chooseClass)) {
				record= clist;
				break;
			}
		
		}
		String cname = record.split(",")[1];
		System.out.println("enter number of attendees");
		int count=sc.nextInt();
		//update timetable
		//validate if it has count to 5
		int enroll_count= csvoper.getEnrollCount(chooseClass);
		//validate if he is already enrolled in same class
		if (csvoper.isEnrolled(chooseClass, customerID,cname)) {
			System.out.println("Alread you enrolled for this class..");
			return false;
		}
		else {
			System.out.println("Not Enrolleddd");
			// so we r enrolling
			if(enroll_count<5) {
				//System.out.println("update time table.. with count value increased by one");
				//System.out.println(chooseClass+"choo"+count);
				csvoper.updaterecord(chooseClass,count);// increment  the time table csv file count value
				//csvoper.updaterecord(chooseClass,-1);// increment  the time table csv file count value
				//System.out.println("update modify status");
				csvoper.updateModifyStatus(String.valueOf(change_id));
				//1,Yoga,11am,1,400,3/11/2023===> Yoga,11am,400
				String enrollData=record.split(",")[1]+","+record.split(",")[2]+","+record.split(",")[4]+","+record.split(",")[5];
				csvoper.enrollCustomer( custID, chooseClass, count, enrollData);
				csvoper.updaterecord(change_id, updt_count);
			}
			else {
				System.out.println("class is already full... please Choose different Class ");
				return false;
			}
		}
		System.out.println("Modifed Booking Successfully!!!");
		return true;
	}
}
	 
	 