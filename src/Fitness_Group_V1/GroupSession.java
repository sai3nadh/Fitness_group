package fitness_Group_V1;
import java.io.IOException;
import java.util.*;

public class GroupSession {

	
	GroupSession(){	
	}
	public boolean bookASession() {
		Scanner read = new Scanner(System.in);
		Scanner read_csv = null;
		String line = "";  
		String splitBy = ",";
		
		// printing the empty lines/pages 
		for(int i=0;i<=9;i++) {
			System.out.println(System.lineSeparator());
		}
		System.out.println("Available Goup Sessions & Prices are ");
		/***
		 *enter customer ID
		 *show available classes to customerID
		 *enter class ID(FitnessID)
		 *then take count(i.e, no attendees for the class)
		 *
		 *validate whether user is already enrolled for this class
		 *now check available seats in particular class
		 *
		 *if criteria accepts then write data to file
		 *booking ID is yyDDMMHHSS
		 *
		 */
		System.out.println("enter customer ID");
		String CustomerID = read.next();
		CSVOperatins csvoper =new CSVOperatins();
		
		String classesCSVFile="C:\\Users\\HP\\Desktop\\fitness_classes.csv";
		List<String> ClassesList = csvoper.getClassRecords(",", classesCSVFile);
		System.out.println("\n\nAvailable Classess sessions on customer booking");
		for (String clist : ClassesList)
		{
			System.out.println(clist);
		}
		System.out.println("enter Class ID to join ");
		String classID = read.next();
		//get data from classes
		String[] compare;
		String []FitnessData= null;
		for (String clist : ClassesList)
		{
			compare = clist.split(",");
			//System.out.println(clist);
			if(compare[0].equals(classID)) {
				FitnessData=clist.split(",");
			}
		}
		System.out.println("class details are---"+FitnessData.toString());
		String enrollData = FitnessData[1]+","+FitnessData[2]+","+FitnessData[4];
		boolean isEnrolled = csvoper.isEnrolled(classID, CustomerID);
		if(!isEnrolled) {
			int enrollcount=csvoper.getEnrollCount(classID);
			System.out.println("Enter number of attendees for the session");
			int count = read.nextInt();
			if ((enrollcount+count)<=5) {
				
				String bookingInfo="";
				System.out.println("enrolllll customerrrrr steppp!!!!!");
				try {
					
					csvoper.enrollCustomer(bookingInfo, CustomerID, classID, count, enrollData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("Don't have enough seats to register");
			}
			
		}else {
			System.out.println("Already you have enrolled for this class");
		}
		
		
		
		
		// reading the csv file to print the prices and  fitness 
		  
//		try{  
//		//parsing a CSV file into BufferedReader class constructor  
//			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\sample.csv"));
//			while ((line = br.readLine()) != null) {   //returns a Boolean value  
//				String[] Prices = line.split(splitBy);    // use comma as separator  
//				System.out.println(" " + Prices[0] + "\t\t" + Prices[1]);  
//			}  
//		}   
//		catch (IOException e){  
//		e.printStackTrace();  
//		}  
//		
		System.out.println(System.lineSeparator()+"The Session is Booked Successfully");
		
		return true;
	}
}