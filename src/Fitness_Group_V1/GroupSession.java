package fitness_Group_V1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GroupSession {

	
	GroupSession(){	
	}
	
	public boolean bookASession() {
		Scanner read = new Scanner(System.in);
		String splitBy = ",";
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
		System.out.println("Enter Customer ID: (sample input: C001,C002,....C009,C010)");
		String custID= read.next().toUpperCase();
		CustomerInfo cinfo= new CustomerInfo();
		//System.out.println("is valid"+cinfo.isCustValid(custID));
		while(!cinfo.isCustValid(custID)) {
			System.out.println("You have entered incorrect Customer ID");
			System.out.println("Enter Valid Customer ID Again(sample input: C001,C002,....C009,C010)");
			custID= read.next().toUpperCase();
			System.out.println("is valid"+cinfo.isCustValid(custID));

		}

		SimpleDateFormat dateInput = new SimpleDateFormat("MM/dd/yyyy");
		Scanner input = new Scanner(System.in);

		
		CSVOperatins csvoper =new CSVOperatins();
		//get classes on particular date
		String classesCSVFile="fitness_classes.csv";
		
		
		System.out.println("please choose option to get your time table");
		System.out.println("1-> By Date\n2-> By FitnessType");
		
		boolean checkFlag=true;
		List<String> ClassesListByMonth,ClassesList = null,ClassesListByFitness;
		int cont=0;
		while (checkFlag) {
			int choice= read.nextInt();
			if(choice==1) {
				checkFlag=false;
				String strDate="";// = input.nextLine();

				
				try
				{
					boolean flag= true;
					while (flag) {
						System.out.println("Enter date in MM/dd/yyyy format"
								+ "\n sample input(3/26/2023,)");
						strDate = input.nextLine();
					   Date date = dateInput.parse(strDate);
					   System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(date));
					   Calendar c = Calendar.getInstance();
				        c.setTime(date);
				        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				        //System.out.println("daysss->"+dayOfWeek);
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
					System.out.println("---------------------------------------------------------------");
					System.out.println("FitID\t Fitness\t timings \t price	\tDate");
					System.out.println("---------------------------------------------------------------");
					cont = 0;
					for(String clsMonth:ClassesListByMonth) {
						System.out.println(clsMonth.split(",")[0]+"\t"
								+ clsMonth.split(",")[1]+"\t\t"
								+ clsMonth.split(",")[2]+"\t\t"
								+ clsMonth.split(",")[4]+"\t\t"
								+ clsMonth.split(",")[5]
								);
						cont++;
					}
					System.out.println("---------------------------------------------------------------");
					ClassesList=ClassesListByMonth;
				} 
				catch (ParseException e) 
				{
				   System.out.println("Parce Exception");
				}
				
			}
			else if(choice==2) {
				checkFlag=false;
				System.out.println("enter FitnessType (eg: yoga, dance, zumba, kungfu)");
				String FitnessType = read.next();
//				List<String>
				ClassesListByFitness=csvoper.getRecordsByFitness(splitBy,classesCSVFile , FitnessType);
				System.out.println("-------------------------------------------------------------------");
				System.out.println("FitID\t Fitness\t timings \t price	\tDate");
				cont=0;
				System.out.println("-------------------------------------------------------------------");
				for(String clsFitness:ClassesListByFitness) {
//					System.out.println("--->"+clsFitness);
					
					System.out.println(clsFitness.split(",")[0]+"\t"
							+ clsFitness.split(",")[1]+"\t\t"
							+ clsFitness.split(",")[2]+"\t\t"
							+ clsFitness.split(",")[4]+"\t\t"
							+ clsFitness.split(",")[5]
							);
					cont++;
					
				}
				System.out.println("-------------------------------------------------------------------");
				ClassesList=ClassesListByFitness;

			}else {
				System.out.println("Enter Valid option");
			}
		}
		
		if(cont==0) {
			System.out.println("We don't have any classes.. Try with other Date or other Fitness Type...\n\n");
			return false;
		}
		
//		System.out.println("enter customer ID");
		String CustomerID = custID;// read.next();
		
		
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
		//System.out.println("class details are---"+FitnessData.toString());
		//System.out.println(FitnessData);
		String className=FitnessData[1];
		String enrollData = FitnessData[1]+","+FitnessData[2]+","+FitnessData[4]+","+FitnessData[5];
		
		//System.out.println(enrollData);
		boolean isEnrolled = csvoper.isEnrolled(classID, CustomerID,className);
		if(!isEnrolled) {
			int enrollcount=csvoper.getEnrollCount(classID);
			System.out.println("Enter number of attendees for the session");
			int count = read.nextInt();
			if ((enrollcount+count)<=5) {
				
				try {
					csvoper.enrollCustomer( CustomerID, classID, count, enrollData);
					csvoper.updaterecord(classID, count);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}else {
				System.out.println("Don't have enough seats to register");
				return false;
			}
			
		}else {
			System.out.println("Already you have enrolled for this class");
			return false;
		}
		
		System.out.println("The Session is Booked Successfully!!!");
		
		return true;
	}
}