package fitness_Group_V1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class MonthlyReport {
	
	MonthlyReport(){
		
	}
	public void monthLessonReport() {
		/**
		 * 
		 * list with all different sessions
		 * access datalist of attendsession.csv
		 * list --amount -- rating ++ count
		 * 
		 * where count >0
		 * final_list list-- name,, amount,, avg rating/count-- total attendees
		 * 
		 */
		
		/**
		 * during champion report
		 * access fitness csv file and get all different sessions
		 * now map 
		 */
		CSVOperatins csvoper = new CSVOperatins();
		
		//read input from user-- (month)
		Scanner sc = new Scanner(System.in);
		System.out.println("enter month");
		int month = sc.nextInt();
		while (month<1 || month>12) {
			System.out.println("enter valid month");
			month = sc.nextInt();
		}
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
		/**
		 * for uniq lessons
		 * 	list append-- rating,  count, amount
		 * 		uniFitName, rating*count, count, amount
		 * 
		 * for each lesson
		 * iterare li<li>
		 * 		split list and  
		 */
	}

}
