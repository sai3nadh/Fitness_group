package fitness_Group_V1;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GroupSession {

	
	GroupSession(){	
	}
	public boolean bookASession(String CustID) {
		Scanner read = new Scanner(System.in);
		Scanner read_csv = null;
		String line = "";  
		String splitBy = ",";
		
		// printing the empty lines/pages 
//		for(int i=0;i<=9;i++) {
//			System.out.println(System.lineSeparator());
//		}
//		System.out.println("Available Goup Sessions & Prices are ");
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
		/**
		 * 
		 * how do you wanna get timetable?
		 * 1-date, 2-Fitness Type
		 * 
		 * if->Date
		 * 		input-date	
		 * 		validate date
		 * 		get all available lessons 
		 * 
		 * else->FitnessType 
		 * 		input- Fitness Type
		 * 		validate
		 * 		get all available lessons
		 * 
		 */
		System.out.println("enter date MM/DD/YYYY");
//		SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateInput = new SimpleDateFormat("MM/dd/yyyy");
		Scanner input = new Scanner(System.in);

		/*
		 * String strDate="";// = input.nextLine();
		 * 
		 * try { boolean flag= true; while (flag) { strDate = input.nextLine(); Date
		 * date = dateInput.parse(strDate); System.out.println(new
		 * SimpleDateFormat("MM/dd/yyyy").format(date)); Calendar c =
		 * Calendar.getInstance(); c.setTime(date); int dayOfWeek =
		 * c.get(Calendar.DAY_OF_WEEK); System.out.println("daysss->"+dayOfWeek);
		 * if(dayOfWeek==7) { System.out.println("saturday");
		 * 
		 * flag = false; }else if (dayOfWeek==1) { System.out.println("sunday");
		 * flag=false; }else {
		 * System.out.println("Date Must and should be saturday or sunday!!!");
		 * System.out.
		 * println("Because we have sessions on weekends only!!! \n please enter valida date"
		 * ); } } } catch (ParseException e) { System.out.println("Parce Exception"); }
		 */
		
		CSVOperatins csvoper =new CSVOperatins();
		//get classes on particular date
		String classesCSVFile="C:\\Users\\HP\\Desktop\\fitness_classes.csv";
		
		/*
		 * List<String>
		 * ClassesListByMonth=csvoper.getRecordsByMonth(splitBy,classesCSVFile ,
		 * strDate); for(String clsMonth:ClassesListByMonth) {
		 * System.out.println("--->"+clsMonth); }
		 */
		/*
		 * System.out.println("enter FitnessType"); String FitnessType = read.next();
		 * List<String>
		 * ClassesListByFitness=csvoper.getRecordsByFitness(splitBy,classesCSVFile ,
		 * FitnessType); for(String clsFitness:ClassesListByFitness) {
		 * System.out.println("--->"+clsFitness); } System.out.println("pauseeeeee");
		 */
		
		
		
		System.out.println("please choose option to get your time table");
		System.out.println("1-> By Date\n2-> By FitnessType");
		
		boolean checkFlag=true;
		List<String> ClassesListByMonth,ClassesList = null,ClassesListByFitness;
		while (checkFlag) {
			int choice= read.nextInt();
			if(choice==1) {
				checkFlag=false;
				String strDate="";// = input.nextLine();

				try
				{
					boolean flag= true;
					while (flag) {
						strDate = input.nextLine();
					   Date date = dateInput.parse(strDate);
					   System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(date));
					   Calendar c = Calendar.getInstance();
				        c.setTime(date);
				        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				        System.out.println("daysss->"+dayOfWeek);
				        if(dayOfWeek==7) {
				        	System.out.println("saturday");
				        	
				        	flag = false;
				        }else if (dayOfWeek==1) {
				        	System.out.println("sunday");
				        	flag=false;
				        }else {
				        	System.out.println("Date Must and should be saturday or sunday!!!");
				        	System.out.println("Because we have sessions on weekends only!!! \n please enter valida date");
				        }
			        }
					//List<String> 
					ClassesListByMonth=csvoper.getRecordsByMonth(splitBy,classesCSVFile , strDate);
					System.out.println("FitID\t Fitness\t timings \t price	\tDate");
					for(String clsMonth:ClassesListByMonth) {
						System.out.println(clsMonth.split(",")[0]+"\t"
								+ clsMonth.split(",")[1]+"\t\t"
								+ clsMonth.split(",")[2]+"\t\t"
								+ clsMonth.split(",")[4]+"\t\t"
								+ clsMonth.split(",")[5]
								);
					}
					ClassesList=ClassesListByMonth;
				} 
				catch (ParseException e) 
				{
				   System.out.println("Parce Exception");
				}
				
			}
			else if(choice==2) {
				checkFlag=false;
				System.out.println("enter FitnessType");
				String FitnessType = read.next();
//				List<String>
				ClassesListByFitness=csvoper.getRecordsByFitness(splitBy,classesCSVFile , FitnessType);
				System.out.println("FitID\t Fitness\t timings \t price	\tDate");
				for(String clsFitness:ClassesListByFitness) {
//					System.out.println("--->"+clsFitness);
					System.out.println(clsFitness.split(",")[0]+"\t"
							+ clsFitness.split(",")[1]+"\t\t"
							+ clsFitness.split(",")[2]+"\t\t"
							+ clsFitness.split(",")[4]+"\t\t"
							+ clsFitness.split(",")[5]
							);
				}
				ClassesList=ClassesListByFitness;
				//System.out.println("pauseeeeee");
			}else {
				System.out.println("Enter Valid option");
			}
		}
		
		
		
		
		
		
		
		
		/**
		 * 
		 * 
		 */
		//int a = read.nextInt();
		
//		System.out.println("enter customer ID");
		String CustomerID = CustID;// read.next();
		
		/**
		 * removing below code to not show all available classes
		 */
//		List<String> 
		/*
		 * ClassesList = csvoper.getClassRecords(",", classesCSVFile);
		 * System.out.println("\n\nAvailable Classess sessions on customer booking");
		 * for (String clist : ClassesList) {
		 * System.out.println(clist.split(",")[0]+"\t"+
		 * clist.split(",")[1]+"\t"+clist.split(",")[2]+"\t"+
		 * clist.split(",")[3]+"\t"+clist.split(",")[4] ); }
		 */
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("Don't have enough seats to register");
			}
			
		}else {
			System.out.println("Already you have enrolled for this class");
		}
		
		
		
		/*
		 * // reading the csv file to print the prices and fitness
		 * 
		 * // try{ // //parsing a CSV file into BufferedReader class constructor //
		 * BufferedReader br = new BufferedReader(new
		 * FileReader("C:\\Users\\HP\\Desktop\\sample.csv")); // while ((line =
		 * br.readLine()) != null) { //returns a Boolean value // String[] Prices =
		 * line.split(splitBy); // use comma as separator // System.out.println(" " +
		 * Prices[0] + "\t\t" + Prices[1]); // } // } // catch (IOException e){ //
		 * e.printStackTrace(); // }
		 */		
		System.out.println(System.lineSeparator()+"The Session is Booked Successfully");
		
		return true;
	}
}