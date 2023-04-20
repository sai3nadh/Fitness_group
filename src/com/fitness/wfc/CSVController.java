package com.fitness.wfc;

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

public class CSVController {
	public List<String> getRecords(String custID, String splitBy, String fileLocation) {
		
		List<String> recordsList= new ArrayList<String>();
		
		try (// show available bookings
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
				String line;
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					String[] Prices = line.split(splitBy);    // use comma as separator  
					if(Prices.length>0) { // to skip the empty record
						if(custID.equals(Prices[1])) {
							recordsList.add(line);
							 
						}
					}
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return recordsList;
	}

	public boolean attendSession(String bookingID, String bookingData) throws IOException {
		String path="attendSession.csv";
		BufferedWriter bw=new BufferedWriter(new FileWriter(path, true));
        
	    String []enrollDataArr= bookingData.split(",");
	    
		String append= bookingID +","+enrollDataArr[0]+","
						+enrollDataArr[1]+","+enrollDataArr[2]+","
						+enrollDataArr[3]+","+enrollDataArr[4]+","
						+enrollDataArr[5]+","+enrollDataArr[6]+","+
						enrollDataArr[7]+
						"\n";
								
		//BookingID	class_name	amount	review	rating	status

        bw.write(append);
		bw.close();
		
		return false;			
	}
	
	public boolean isEnrolled(String FitnessID, String CustomerID,String className) {
		String fileLocation  = "wfc_booking.csv";//"fitness_classes.csv";;
		//System.out.println("avaiable bookings");
		try (
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
				String line,FitID[];
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					FitID=line.split(",");
					if ((FitID[2].equals(FitnessID) && FitID[1].equals(CustomerID))||
							(FitID[7].equals(className) && FitID[1].equals(CustomerID)))
					
					{
					return true;// returns true if user is already enrolled in class
					}	
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//to get the count of already enrolled customers
	public int getEnrollCount(String FitnessID) {
		String fileLocation  = "fitness_classes.csv";;
		try (// show available bookings
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
				String line,FitID[];
				
				
				
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					// append all records to list
					
					FitID=line.split(",");
					
					if (FitID[0].equals(FitnessID)) {
					return Integer.valueOf(FitID[3]);
					}	
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	public int getRecordID(String BookingID, String splitBy,  String fileLocation) {
		
		int count =0;
			System.out.println("finding record ID in bookings");
			try (// show available bookings
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
							
				String line;
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					String[] Prices = line.split(splitBy);    // use comma as separator  
					count++;
					if(BookingID.equals(Prices[0])) {
						// break the loop if we found Booking ID row
						break;	
					}
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return count;
		}
	public List<String> getClassRecords( String splitBy, String fileLocation) {
		
		List<String> recordsList= new ArrayList<String>();
		System.out.println("avaiable bookings");
		try (// show available bookings
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
				String line;
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					// append all records to list
					recordsList.add(line);
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return recordsList;
	}

	public List<String> getRecordsByMonth( String splitBy, String fileLocation, String Month) {
		
		List<String> recordsList= new ArrayList<String>();
		System.out.println("avaiable bookings");
		try (// show available bookings
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
				String line;
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					
					String checkMonth= line.split(",")[5];

					if(checkMonth.equals(Month)) {
						// append all records to list
						recordsList.add(line);
						
					}
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return recordsList;
	}

	public List<String> getRecordsByFitness( String splitBy, String fileLocation, String Fitness) {
		
		List<String> recordsList= new ArrayList<String>();
		System.out.println("avaiable bookings");
		try (// show available bookings
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
				String line;
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					/*
					 * // append all records to list recordsList.add(line);
					 */
					String checkFitness= line.split(",")[1];
//					System.out.println("compare months:"+checkMonth+"-"+Month);
					if(checkFitness.equalsIgnoreCase(Fitness)) {
						// append all records to list
						recordsList.add(line);
						
					}
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return recordsList;
	}

	
	
	
	public boolean updaterecord(String FitnessID, int count) {
		String filepath="fittemp2.csv";
		//timetable path to update booking count
		String timetable = "fitness_classes.csv";
		File oldfile = new File(timetable);
		File tempfile= new File(filepath);
		try {
			FileWriter fw= new FileWriter(tempfile,true);
			BufferedWriter bw= new BufferedWriter(fw);
			PrintWriter pw= new PrintWriter(bw);
			
			FileReader fr = new FileReader(timetable);
			BufferedReader br= new BufferedReader(fr);
			String Currentline;
			String[] FitID=null;
			while ((Currentline = br.readLine()) != null) {
				//read line and 
				FitID=Currentline.split(",");
				
				if (FitID[0].equals(FitnessID)) {
					String apnd= String.valueOf(Integer.valueOf(FitID[3])+count);
					// append line by incrementing by one
					
					Currentline= FitID[0]+","+FitID[1]+","+ FitID[2]+ ","+
							apnd+","+FitID[4]+","+FitID[5];
					//System.out.println("new vlaue--->>\n"+Currentline);
					pw.println(Currentline);
					
				}else {
					//System.out.println("else vlaue--->>"+Currentline);
					
					pw.println(Currentline);						
				}
				
			}
			
			pw.flush();
			pw.close();
			fr.close();
			br.close();
			fw.close();
			bw.close();
			
			oldfile.delete(); 
			File dump= new File(timetable); 
			tempfile.renameTo(dump);
		}
		catch(Exception e ){
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	public boolean updateAttendStatus(String BookingID) {
		String filepath="custTblTemp.csv";
		//timetable path to update booking count
		String custTable = "wfc_booking.csv"; //"fitness_classes.csv";
		File oldfile = new File(custTable);
		File tempfile= new File(filepath);
		try {
			FileWriter fw= new FileWriter(tempfile,true);
			BufferedWriter bw= new BufferedWriter(fw);
			PrintWriter pw= new PrintWriter(bw);
			
			FileReader fr = new FileReader(custTable);
			BufferedReader br= new BufferedReader(fr);
			String Currentline;
			String[] BookingData=null;
			while ((Currentline = br.readLine()) != null) {
				//read line and 
				BookingData=Currentline.split(",");
				
				if (BookingData[0].equals(BookingID)) {
					// reconstruct line with attended status
					Currentline= BookingData[0]+","+BookingData[1]+","+ BookingData[2]+ ","+
								BookingData[3]+","+BookingData[4]+","+BookingData[5]+","
								+"attended"+","+BookingData[7]+","+BookingData[8];
					
					pw.println(Currentline);
					
				}else {
					
					pw.println(Currentline);						
				}
				
				
			}
			
			pw.flush();
			pw.close();
			fr.close();
			br.close();
			fw.close();
			bw.close();
			
			oldfile.delete(); 
			File dump= new File(custTable); 
			tempfile.renameTo(dump);
				  
			
		}
		catch(Exception e ){
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}

	public boolean updateModifyStatus(String BookingID) {
		String filepath="custTblTemp.csv";
		//timetable path to update booking count
		String custTable = "wfc_booking.csv"; //"fitness_classes.csv";
		File oldfile = new File(custTable);
		File tempfile= new File(filepath);
		try {
			FileWriter fw= new FileWriter(tempfile,true);
			BufferedWriter bw= new BufferedWriter(fw);
			PrintWriter pw= new PrintWriter(bw);
			
			FileReader fr = new FileReader(custTable);
			BufferedReader br= new BufferedReader(fr);
			String Currentline;
			String[] BookingData=null;
			while ((Currentline = br.readLine()) != null) {
				//read line and 
				BookingData=Currentline.split(",");
				
				if (BookingData[0].equals(BookingID)) {
					// reconstruct line with attended status
					Currentline= BookingData[0]+","+BookingData[1]+","+ BookingData[2]+ ","+
								BookingData[3]+","+BookingData[4]+","+BookingData[5]+","
								+"changed"+","+BookingData[7]+","+BookingData[8];
					
					pw.println(Currentline);
					
				}else {
					
					pw.println(Currentline);						
				}
				
			}
			
			pw.flush();
			pw.close();
			fr.close();
			br.close();
			fw.close();
			bw.close();
			
			oldfile.delete(); 
			File dump= new File(custTable); 
			tempfile.renameTo(dump);
				  
			
		}
		catch(Exception e ){
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}


	public boolean updateCancelStatus(String BookingID) {
		String filepath="custTblTemp.csv";
		//timetable path to update booking count
		String custTable = "wfc_booking.csv"; //"fitness_classes.csv";
		File oldfile = new File(custTable);
		File tempfile= new File(filepath);
		try {
			FileWriter fw= new FileWriter(tempfile,true);
			BufferedWriter bw= new BufferedWriter(fw);
			PrintWriter pw= new PrintWriter(bw);
			
			FileReader fr = new FileReader(custTable);
			BufferedReader br= new BufferedReader(fr);
			String Currentline;
			String[] BookingData=null;
			while ((Currentline = br.readLine()) != null) {
				//read line and 
				BookingData=Currentline.split(",");
				
				if (BookingData[0].equals(BookingID)) {
					// reconstruct line with attended status
					Currentline= BookingData[0]+","+BookingData[1]+","+ BookingData[2]+ ","+
								BookingData[3]+","+BookingData[4]+","+BookingData[5]+","
								+"Cancelled"+","+BookingData[7]+","+BookingData[8];

					pw.println(Currentline);
					
				}else {
					//System.out.println("else vlaue--->>"+Currentline);
					
					pw.println(Currentline);						
				}
				
			}
			
			pw.flush();
			pw.close();
			fr.close();
			br.close();
			fw.close();
			bw.close();
			
			oldfile.delete(); 
			File dump= new File(custTable); 
			tempfile.renameTo(dump);
				  
			
		}
		catch(Exception e ){
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}

	public boolean enrollCustomer(String CustomerID, String ClassID, int count, String enrollData) throws IOException {
		String path="wfc_booking.csv";
		BufferedWriter bw=new BufferedWriter(new FileWriter(path, true));
        // construct string with all values
		SimpleDateFormat dateFormat = new SimpleDateFormat("MddHHmms");
		//System.out.println(dateFormat.format(new Date()));
	    String BookingID = String.valueOf(dateFormat.format(new Date()));
		
		//	bookingID	CustomerID	classID	timings	booking_count	amount	status	class name
		//		22	2	1	Vooiking ID	342	3445	booked	
		// BookingID+","+CustomerID + "," + ClassID + "," + timings + count + amount
	    String []enrollDataArr= enrollData.split(",");
		String append= BookingID +","+ CustomerID+","+ClassID +","+enrollDataArr[3].split("/")[0]
	    //(enrollDataArr[1].replaceAll("pm", "")).replaceAll("am", "")
				+","+count + ","+enrollDataArr[2]+",booked,"+enrollDataArr[0]
						+","+enrollDataArr[1]+"\n";
		
        bw.write(append);
		bw.close();
		return true;
		
	}


	public List<String> getMonthRecords(int month) {
		List<String> recordsList= new ArrayList<String>();
		
		String fileLocation="attendSession.csv";
		try (// show available bookings
				BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
				String line,checkMonth[];
				while ((line = br.readLine()) != null) {   //returns a Boolean value  
					// append all records to list
					checkMonth= line.split(",");
					if(checkMonth[6].equals(String.valueOf(month))) {
						recordsList.add(line);
					}
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return recordsList;
	}
 
}
