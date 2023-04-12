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
	String csvFile="sample.csv";
	Scanner sc= new Scanner(System.in);
	
	public boolean cancelBooking() throws IOException {
//		String line = "";  
//		String splitBy = ",";
		//ask for his id
		Scanner read= new Scanner(System.in);
		System.out.println("Enter Customer ID");
		String custID= read.next();
		CustomerInfo cinfo= new CustomerInfo();
		System.out.println("is valid"+cinfo.isCustValid(custID));
		while(!cinfo.isCustValid(custID)) {
			System.out.println("Enter Valid Customer ID");
			custID= read.next();
			System.out.println("is valid"+cinfo.isCustValid(custID));

		}
		//System.out.println("enter booking ID");
		String customerID= custID;//sc.next();
			
				try {
				
					System.out.println("**********************");
					System.out.println("**********************");
					System.out.println("avaiable bookings");
//					System.out.println("please enter customerID");
					
					CSVOperatins csvoper = new CSVOperatins();
					// call method and get records with particular customer ID
					List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
					

					System.out.println("available sessions on customer booking");
					System.out.println("================================");
					
					System.out.println("fitID\t\t type\t status");
					System.out.println("================================");
					int count = 0;
					for (String add : testList)
					{
						//System.out.println(add);
						// 
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
					boolean cancel=csvoper.updateCancelStatus(cancel_id);
					if(cancel) {
						System.out.println("Cancellation Successful");
					}
					else {
						System.out.println("Failed Cancellation");
					}
						// cancel class
					
					//======================
					/*
					 * System.out.println("**********************");
					 * System.out.println("**********************"); CSVOperatins csvoper = new
					 * CSVOperatins(); // call method and get records with particular customer ID
					 * List<String> testList = csvoper.getRecords(customerID, ",", csvFile); for
					 * (String add : testList) { System.out.println("inside values"+add); } int
					 * deleteLine = csvoper.getRecordID(customerID, ",", csvFile);
					 * System.out.println("executed getRecordID"+deleteLine); //calling deleteRow
					 * method System.out.println("calling deleteRow method"); int deleteStatus=
					 * csvoper.deleteRow( ",", csvFile, deleteLine); if (deleteStatus== 1) {
					 * System.out.println("cancellation Successful"); }
					 */
				}
				catch(Exception e) {
					e.printStackTrace();
					return false;
				}
			//System.out.println("enter ID or enter X to terminate");
			//customerID=sc.next();
			
		
		System.out.println("canelling the booking ");
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
//		System.out.println("please enter customerID");
		Scanner read = new Scanner(System.in);
		System.out.println("Enter Customer ID");
		String custID= read.next();
		CustomerInfo cinfo= new CustomerInfo();
		System.out.println("is valid"+cinfo.isCustValid(custID));
		while(!cinfo.isCustValid(custID)) {
			System.out.println("Enter Valid Customer ID");
			custID= read.next();
			System.out.println("is valid"+cinfo.isCustValid(custID));

		}
		String customerID= custID;
		
		CSVOperatins csvoper = new CSVOperatins();
		// call method and get records with particular customer ID
		List<String> testList = csvoper.getRecords(customerID, ",", csvFile);
		System.out.println("available sessions on customer booking");
		System.out.println("fitID\t type\t status");
		for (String add : testList)
		{
			//System.out.println(add);
			// 
			if(!add.split(",")[6].equals("attended")) {
				System.out.println(add.split(",")[0]+"\t"+
									add.split(",")[7]+"\t"+
									add.split(",")[6]);
			}
		}
		// take  user input --> class id
		System.out.println("enter Fitness ID ");
		int change_id=sc.nextInt();
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
		String filepath = "sampletest.csv";
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
			// so we r enrolling
			if(enroll_count<5) {
				System.out.println("update time table.. with count value increased by one");
				System.out.println(chooseClass+"choo"+count);
				csvoper.updaterecord(chooseClass,count);// increment  the time table csv file count value
				csvoper.updaterecord(chooseClass,-1);// increment  the time table csv file count value
				System.out.println("update modify status");
				csvoper.updateModifyStatus(String.valueOf(change_id));
				//1,Yoga,11am,1,400,3/11/2023===> Yoga,11am,400
				String enrollData=record.split(",")[1]+","+record.split(",")[2]+","+record.split(",")[4]+","+record.split(",")[5];
				csvoper.enrollCustomer( custID, chooseClass, count, enrollData);
			}
			else {
				System.out.println("class is already full... please ");
			}
		}
		
		return true;
	}
}
	 class CSVOperatins{
		public List<String> getRecords(String custID, String splitBy, String fileLocation) {
			
			List<String> recordsList= new ArrayList<String>();
			//System.out.println("avaiable bookings");
			try (// show available bookings
					BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
					String line;
					while ((line = br.readLine()) != null) {   //returns a Boolean value  
						String[] Prices = line.split(splitBy);    // use comma as separator  
						if(Prices.length>0) { // to skip the empty record
							if(custID.equals(Prices[1])) {
								recordsList.add(line);
								//System.out.println(" " + Prices[0] + "\t\t" + Prices[1] +"count value");//+count); 
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
	       System.out.println("bookingdata is "+bookingData); 
		    String []enrollDataArr= bookingData.split(",");
		    System.out.println("booking data is :" +bookingData);
			String append= bookingID +","+enrollDataArr[0]+","
							+enrollDataArr[1]+","+enrollDataArr[2]+","
							+enrollDataArr[3]+","+enrollDataArr[4]+","
							+enrollDataArr[5]+","+enrollDataArr[6]+
							"\n";
									
			//BookingID	class_name	amount	review	rating	status

			//System.out.println("appned string is-->>>"+append);
	        bw.write(append);
			bw.close();
			
			return false;			
		}
		
		public boolean isEnrolled(String FitnessID, String CustomerID) {
			String fileLocation  = "sample.csv";//"fitness_classes.csv";;
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
			String fileLocation  = "fitness_classes.csv";;
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
			String filepath="custTblTemp.csv";
			//timetable path to update booking count
			String custTable = "sample.csv"; //"fitness_classes.csv";
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
						//System.out.println("new vlaue--->>\n"+Currentline);
						pw.println(Currentline);
						
					}else {
						//System.out.println("else vlaue--->>"+Currentline);
						
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

		public boolean updateModifyStatus(String BookingID) {
			String filepath="custTblTemp.csv";
			//timetable path to update booking count
			String custTable = "sample.csv"; //"fitness_classes.csv";
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
									+"modified"+","+BookingData[7];
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


		public boolean updateCancelStatus(String BookingID) {
			String filepath="custTblTemp.csv";
			//timetable path to update booking count
			String custTable = "sample.csv"; //"fitness_classes.csv";
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
									+"Cancelled"+","+BookingData[7];
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

		public boolean enrollCustomer(String CustomerID, String ClassID, int count, String enrollData) throws IOException {
			String path="sample.csv";
			BufferedWriter bw=new BufferedWriter(new FileWriter(path, true));
	        // bw.write("S.no,Name,Fitness_Type,Price"); // table heading
			// construct string with all values
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MddHHmms");
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
//			String fileLocation="attendSession.csv";
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

	 