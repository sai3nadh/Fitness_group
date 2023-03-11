package fitness_Group_V1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class MonthlyChampion {
	
	public void monthlyChamReport()  {
	CSVOperatins csvoper = new CSVOperatins();
		
		//read input from user-- (month)
		Scanner sc = new Scanner(System.in);
		System.out.println("enter month");
		int month = sc.nextInt();
		
		List<String> monthRecords = csvoper.getMonthRecords(month);
		List<String> uniFitness = new ArrayList<String>();
		
		//System.out.println("displaying particular month Data---->>>>");
		//extract all fitness classes types and put it in separate list
		for(String monthdata : monthRecords) {
			uniFitness.add(monthdata.split(",")[1]);
		}
		
		//removing duplicates
		Set<String> set = new HashSet<>(uniFitness);
		uniFitness.clear();
		uniFitness.addAll(set);

		//initializing a map
		HashMap<String, String> fitnessMap = new HashMap<String, String>();
		
		//assigning values to Map
		//Key=>fitness_name
		//Value=>"amount", "rating", "count"
		for (String a:uniFitness) {
			fitnessMap.put(a.split(",")[0], "0,0,0");
		}
		
		for(String a:monthRecords) {
			String fitness=a.split(",")[1];
			String []test = a.split(",");
			String mapVal = fitnessMap.get(fitness);
			
			int amount  = Integer.valueOf(test[2])
						+ Integer.valueOf(mapVal.split(",")[0]);
			
			int newrating= Integer.valueOf(test[4])*Integer.valueOf(test[7]) 
							+Integer.valueOf(mapVal.split(",")[1]);
			
			int count = Integer.valueOf(test[7])
						+Integer.valueOf(mapVal.split(",")[2]);
			
			String newValue= String.valueOf(amount)+","+String.valueOf(newrating)+","+String.valueOf(count);
			//1,yoga,300,good,2,attended,3,1
			//String replaceData = 
			fitnessMap.replace(fitness, newValue);
		}
		//temp map

		System.out.println("size of mapp is "+fitnessMap.size());
		HashMap<String, String> fitnessMaptemp= new HashMap<String, String>(fitnessMap) ;
		HashMap<String, String> newArrMap= new HashMap<String, String>();
		List<List< String>> newlist= new ArrayList<List< String>>();
		List< String> newlis2t= new ArrayList< String>();

//		newArrMap.clear();
		 //
//		 * var tempMax= 0, key-->
		System.out.println("<<<<<<<--======Month Lesson Report======-->>>>>>>");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Fitness\t\tTotal Amount\tAverage rating\t   Total Strength");
		System.out.println("-----------------------------------------------------------------");
		for(Entry<String, String> cp:fitnessMap.entrySet()) {
			System.out.println(cp.getKey()+"\t\t"+cp.getValue().split(",")[0]+"\t\t\t"
					+ Integer.valueOf(cp.getValue().split(",")[1])/Integer.valueOf(cp.getValue().split(",")[2])+"\t\t"
					+cp.getValue().split(",")[2]);
			
		}
		System.out.println("-----------------------------------------------------------------");
		
		int tempMax=0;
		String key=null,value="";
		System.out.println("size of mapp is "+fitnessMap.size());
		int count=fitnessMap.size();
		 while(count>0) {
			 for(Entry<String, String> cp:fitnessMap.entrySet()) {
				// key=cp.getKey();
				 int arrVal = Integer.valueOf( cp.getValue().split(",")[0]);
				 if(arrVal>tempMax) {
					 tempMax=arrVal;
					 key=cp.getKey();
					 value=cp.getValue();
				 }
				 
			}
			 tempMax=0;
			 System.out.println(" new value is "+ key+"---value"+value);
			newArrMap.put(key, value);
			newlis2t.add(key+","+value);
			/*
			 * for(Entry<String, String> cp:newArrMap.entrySet()) {
			 * System.out.println(cp.getKey()+"-"+cp.getValue()); }
			 */
			fitnessMap.remove(key);
			 System.out.println("size of mapp is updated"+fitnessMap.size());
	//	count--;
			 count=fitnessMap.size();		
		 }
		 
		 
		System.out.println("<<<<<<<--======Monthly Champion Report======-->>>>>>>");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Fitness\t\tTotal Amount\tAverage rating\t   Total Strength");
		System.out.println("-----------------------------------------------------------------");
		 for(Entry<String, String> cp:newArrMap.entrySet()) {
			 System.out.println(cp.getKey()+"-"+cp.getValue());
		 }
		 System.out.println("listttt");
		for( String a :newlis2t) {
			System.out.println(a);
		}
		
		/**
		 * 
		 * temp map
		 * 
		 * var tempMax= 0, key-->
		 * while(tempMap.length()>0)
		 * 		iterate list
		 * 			key=getKey()
		 * 			arrVal = value.split(",")[amount_field]
		 * 			if (arrVal>tempMax)
		 * 			tempMax=arrVal
		 * 		
		 * 		tempMap.revome(key)
		 * 		newMap.put(Key, Val)
		 * 
		 */
	}
}
