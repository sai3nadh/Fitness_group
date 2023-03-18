package fitness_Group_V1;

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

//import org.w3c.dom.css.CSSValue;

public class ModifyBooking {

	ModifyBooking(){
		
	}
	String csvFile="C:\\Users\\HP\\Desktop\\sample.csv";
	Scanner sc= new Scanner(System.in);
	
	public boolean cancelBooking() throws IOException {
//		String line = "";  
//		String splitBy = ",";
		//ask for his id
		//Scanner sc= new Scanner(System.in);
		System.out.println("enter booking ID");
		String customerID= sc.next();
			System.out.println("avaiable bookings");
		
				try {
				
					System.out.println("**********************");
					System.out.println("**********************");
					CSVOperatins csvoper = new CSVOperatins();
					// call method and get records with particular customer ID
					List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
					for (String add : testList)
					{
						System.out.println("inside values"+add);
					}
					int deleteLine = csvoper.getRecordID(customerID, ",", csvFile);
					System.out.println("executed getRecordID"+deleteLine);
					//calling deleteRow method
					System.out.println("calling deleteRow method");
					int deleteStatus= csvoper.deleteRow( ",", csvFile, deleteLine);
					if (deleteStatus== 1) {
						System.out.println("cancellation Successful");
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			//System.out.println("enter ID or enter X to terminate");
			//customerID=sc.next();
			
		
		System.out.println("canelling the booking ");
		return true;
	}
	public boolean changeBooking(String custID) throws IOException {
		System.out.println("the session has been rescheduled successfuly");
		// reschedule session
		// check available session on particular candidate
		System.out.println("**********************");
		System.out.println("**********************");
//		System.out.println("please enter customerID");
		String customerID= custID;//sc.next();
		
		CSVOperatins csvoper = new CSVOperatins();
		// call method and get records with particular customer ID
		List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
		System.out.println("available sessions on customer booking");
		for (String add : testList)
		{
			System.out.println(add);
		}
		// take  user input --> class id
		
			// cancel class
		// show available sessions to change booking-- new class
		String classesCSVFile="C:\\Users\\HP\\Desktop\\fitness_classes.csv";
		List<String> ClassesList = csvoper.getClassRecords(",", classesCSVFile);
		System.out.println("\n\nAvailable Classess sessions on customer booking");
		for (String clist : ClassesList)
		{
			System.out.println(clist);
		}
		// ask for customer to choose class
		System.out.println("adding new class to file");
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
			//System.out.println(clist);
		}
		
		// confirm booking
		String filepath = "C:\\Users\\HP\\Desktop\\sampletest.csv";
//		csvoper.putRecord(filepath, record);
		int count=1;
		//update timetable
		System.out.println("validate if it has count to 5");
		int enroll_count= csvoper.getEnrollCount(chooseClass);
		System.out.println("validate if he is already enrolled in same class");
		if (csvoper.isEnrolled(chooseClass, customerID)) {
			System.out.println("already enrolled");
		}
		else {
			System.out.println("Not Enrolleddd");
		}
		if(enroll_count<5) {
			System.out.println("update time table.. with count value increased by one");
			csvoper.updaterecord(chooseClass,count);
		}
		else {
			System.out.println("class is already full... please ");
		}
		/***
		 * 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		    System.out.println(dateFormat.format(new Date()));
			
		 */
		
		return true;
	}
}
	 class CSVOperatins{
		public List<String> getRecords(String custID, String splitBy, String fileLocation) {
			
			List<String> recordsList= new ArrayList<String>();
			System.out.println("avaiable bookings");
			try (// show available bookings
					BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
					String line;
					while ((line = br.readLine()) != null) {   //returns a Boolean value  
						String[] Prices = line.split(splitBy);    // use comma as separator  
						if(Prices.length>0) { // to skip the empty record
							if(custID.equals(Prices[1])) {
								recordsList.add(line);
								System.out.println(" " + Prices[0] + "\t\t" + Prices[1] +"count value");//+count); 
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
			String path="C:\\Users\\HP\\Desktop\\attendSession.csv";
			BufferedWriter bw=new BufferedWriter(new FileWriter(path, true));
	       System.out.println("bookingdata is "+bookingData); 
		    String []enrollDataArr= bookingData.split(",");
		    System.out.println("booking data is :" +bookingData);
			String append= bookingID +","+enrollDataArr[0]+","
							+enrollDataArr[1]+","+enrollDataArr[2]+","
							+enrollDataArr[3]+","+enrollDataArr[4]+"\n";
									
			//BookingID	class_name	amount	review	rating	status

			System.out.println("appned string is-->>>"+append);
	        bw.write(append);
			bw.close();
			
			return false;			
		}
		
		public boolean isEnrolled(String FitnessID, String CustomerID) {
			String fileLocation  = "C:\\Users\\HP\\Desktop\\sample.csv";//"C:\\Users\\HP\\Desktop\\fitness_classes.csv";;
			System.out.println("avaiable bookings");
			try (
					BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
					String line,FitID[];
					while ((line = br.readLine()) != null) {   //returns a Boolean value  
						FitID=line.split(",");
						if (FitID[2].equals(FitnessID) && FitID[1].equals(CustomerID)) {
						return true;// returns true if user is already enrolled in class
						}	
					}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return false;
		}
		public int getEnrollCount(String FitnessID) {
			String fileLocation  = "C:\\Users\\HP\\Desktop\\fitness_classes.csv";;
			//List<String> recordsList= new ArrayList<String>();
			System.out.println("avaiable bookings");
			try (// show available bookings
					BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
					String line,FitID[];
					
					
					
					while ((line = br.readLine()) != null) {   //returns a Boolean value  
						// append all records to list
						
						FitID=line.split(",");
						
						if (FitID[0].equals(FitnessID)) {
						return Integer.valueOf(FitID[3]);
						}					
							
						//recordsList.add(line);
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
						//BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\sample.csv"))) {
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
						/*
						 * // append all records to list recordsList.add(line);
						 */
						String checkMonth= line.split(",")[5];
//						System.out.println("compare months:"+checkMonth+"-"+Month);
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
//						System.out.println("compare months:"+checkMonth+"-"+Month);
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

		
		public int deleteRow( String splitBy,  String csvFile, int deleteLine) {
			//String line = "";
			String filepath="temp.csv";
			File oldfile = new File(csvFile);
			File tempfile= new File(filepath);
			try {
				FileWriter fw= new FileWriter(tempfile,true);
				BufferedWriter bw= new BufferedWriter(fw);
				PrintWriter pw= new PrintWriter(bw);
				
				FileReader fr = new FileReader(csvFile);
				BufferedReader br= new BufferedReader(fr);
				int line=0;
				String Currentline;
				while ((Currentline = br.readLine()) != null) {
					
					line++;
					
					if (deleteLine != line) {
						pw.println(Currentline);
					}
				}
				
				pw.flush();
				pw.close();
				fr.close();
				br.close();
				fw.close();
				bw.close();
				
				  oldfile.delete(); File dump= new File(csvFile); tempfile.renameTo(dump);
				  
				System.out.println("deletion ends");
				System.out.println("**********************");
				return 1;
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		
		public boolean putRecord(String path, String chooseClass) throws IOException {
			BufferedWriter bw=new BufferedWriter(new FileWriter(path, true));
	        // bw.write("S.no,Name,Fitness_Type,Price"); // table heading
	        bw.write("class,"+"adfasdf----"+chooseClass+"----,tested filesssssssss"); // append the datat to csv file
	        bw.close();
			return false;
		}
		
		public boolean updaterecord(String FitnessID, int count) {
			String filepath="C:\\Users\\HP\\Desktop\\fittemp2.csv";
			//timetable path to update booking count
			String timetable = "C:\\Users\\HP\\Desktop\\fitness_classes.csv";
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
								apnd+","+FitID[4];
						System.out.println("new vlaue--->>\n"+Currentline);
						pw.println(Currentline);
						
					}else {
						System.out.println("else vlaue--->>"+Currentline);
						
						pw.println(Currentline);						
					}
					
					/*
					 * line++; if (deleteLine != line) { pw.println(Currentline); }
					 */
				}
				
				pw.flush();
				pw.close();
				fr.close();
				br.close();
				fw.close();
				bw.close();
				
				//oldfile.delete(); File dump= new File(csvFile); tempfile.renameTo(dump);
				oldfile.delete(); 
				File dump= new File(timetable); 
				tempfile.renameTo(dump);
					  
				System.out.println("update file ends");
				System.out.println("**********************");
			}
			catch(Exception e ){
				e.printStackTrace();
				return false;
			}
			
			
			return true;
		}
		
		public boolean updateAttendStatus(String BookingID) {
			String filepath="C:\\Users\\HP\\Desktop\\custTblTemp.csv";
			//timetable path to update booking count
			String custTable = "C:\\Users\\HP\\Desktop\\sample.csv"; //"C:\\Users\\HP\\Desktop\\fitness_classes.csv";
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
									+"attended"+","+BookingData[7];
						System.out.println("new vlaue--->>\n"+Currentline);
						pw.println(Currentline);
						
					}else {
						System.out.println("else vlaue--->>"+Currentline);
						
						pw.println(Currentline);						
					}
					
					/*
					 * line++; if (deleteLine != line) { pw.println(Currentline); }
					 */
				}
				
				pw.flush();
				pw.close();
				fr.close();
				br.close();
				fw.close();
				bw.close();
				
				//oldfile.delete(); File dump= new File(csvFile); tempfile.renameTo(dump);
				oldfile.delete(); 
				File dump= new File(custTable); 
				tempfile.renameTo(dump);
					  
				System.out.println("update file ends");
				System.out.println("**********************");
			}
			catch(Exception e ){
				e.printStackTrace();
				return false;
			}
			
			
			return true;
		}
		
		public boolean enrollCustomer(String bookingInfo,String CustomerID, String ClassID, int count, String enrollData) throws IOException {
			String path="C:\\Users\\HP\\Desktop\\sample.csv";
			BufferedWriter bw=new BufferedWriter(new FileWriter(path, true));
	        // bw.write("S.no,Name,Fitness_Type,Price"); // table heading
			// construct string with all values
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		    System.out.println(dateFormat.format(new Date()));
		    String BookingID = String.valueOf(dateFormat.format(new Date()));
			
			//	bookingID	CustomerID	classID	timings	booking_count	amount	status	class name
			//		22	2	1	Vooiking ID	342	3445	booked	
			// BookingID+","+CustomerID + "," + ClassID + "," + timings + count + amount
		    String []enrollDataArr= enrollData.split(",");
			String append= BookingID +","+ CustomerID+","+ClassID +","+(enrollDataArr[1].replaceAll("pm", "")).replaceAll("am", "")+","+count + ","+enrollDataArr[2]+",booked,"+enrollDataArr[0]+"\n";
			System.out.println("appned string is-->>>"+append);
	        bw.write(append);
			bw.close();
			return true;
			
		}


		public List<String> getMonthRecords(int month) {
			// TODO Auto-generated method stub
			List<String> recordsList= new ArrayList<String>();
			System.out.println("avaiable bookings");
			String fileLocation="C:\\Users\\HP\\Desktop\\attendSession.csv";
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

	 