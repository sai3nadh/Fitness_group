package com.fitness.wfc;
import java.io.IOException;
import java.util.Scanner;
public class GroupFitness {


	public static void main(String[] args) throws IOException {
		GroupSession gs = new GroupSession() ;
		ModifyBooking mb = new ModifyBooking();
		AttendSession as = new AttendSession();
		MonthlyReport mr = new MonthlyReport();
		MonthlyChampion mcr= new MonthlyChampion();
		try (Scanner operation = new Scanner(System.in)) {
			System.out.println("*******************************************");
			System.out.println("\tWeekend Fitness Club");
			
			// the main functionalities 
			System.out.println("*******************************************");
			System.out.println("1) Book a Group Session ");
			System.out.println("2) Change / Cancel a Session ");
			System.out.println("3) Attend a Lession  ");
			System.out.println("4) Monthly Lession Report ");
			System.out.println("5) Monthly Chapion Fitness Report ");
			System.out.println("6) Exit System");
			System.out.println("*******************************************");
			
			System.out.println(" Choose the operation to perform ");
			System.out.println("Enter Operation ID:");
			// read the operation to perform  
			int option = operation.nextInt();
			
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
					}
					else if(op==2) {
						mb.cancelBooking();
					}
					break;
				case 3: 
					as.attendASession();
					break;
				case 4: 
					
					  System.out.println("enter month"); 
					  int month = operation.nextInt(); 
					  while (month < 1 || month > 12) {
						  System.out.println("enter valid month (range between: 1 to 12)"); 
						  month = operation.nextInt(); 
					  }
					 
					mr.monthLessonReport(month);
					break;
				case 5: 
					//System.out.println("call Monthly Champion Fitness Report");
					System.out.println("enter month(range between: 1 to 12)"); 
					   month = operation.nextInt(); 
					  while (month < 1 || month > 12) {
						  System.out.println("enter valid month (range between: 1 to 12)"); 
						  month = operation.nextInt(); 
					  }
					mcr.monthlyChamReport(month);
					break;
				case 6: System.out.println("exit system");
				break;
				}
				if (option!=6) {
					System.out.println("*******************************************");
					System.out.println("Entered to main menu please choose option");
					System.out.println("*******************************************");
					System.out.println("1) Book a Group Session ");
					System.out.println("2) Change / Cancel a Session ");
					System.out.println("3) Attend a Lession  ");
					System.out.println("4) Monthly Lession Report ");
					System.out.println("5) Monthly Chapion Fitness Report ");
					System.out.println("6) Exit System");
					System.out.println("*******************************************");
					System.out.println(" Choose the operation to perform ");
					option =operation.nextInt();
				}
			}
			System.out.println("Exit Sucessful!!!");
		}
	}


}
