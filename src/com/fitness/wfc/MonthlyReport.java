package com.fitness.wfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class MonthlyReport {

	MonthlyReport() {

	}

	public List<String> monthRecords ;
	public void monthLessonReport(int month) {
		/**
		 * 
		 * list with all different sessions access datalist of attendsession.csv list
		 * --amount -- rating ++ count
		 * 
		 * where count >0 final_list list-- name,, amount,, avg rating/count-- total
		 * attendees
		 * 
		 */

		/**
		 * during champion report access fitness csv file and get all different sessions
		 * now map
		 */
		CSVController csvoper = new CSVController();
		
		//List<String>
		monthRecords = csvoper.getMonthRecords(month);
		List<String> uniFitness = new ArrayList<String>();
		
		
		for (String monthdata : monthRecords) {
			uniFitness.add(monthdata.split(",")[1] + "-" + monthdata.split(",")[8]);
		}

		// removing duplicates
		Set<String> set = new HashSet<>(uniFitness);
		uniFitness.clear();
		uniFitness.addAll(set);

		// initializing a map
		HashMap<String, String> fitnessMap = new HashMap<String, String>();

		// assigning values to Map
		// Key=>fitness_name
		// Value=>"amount", "rating", "count"
		for (String a : uniFitness) {
			fitnessMap.put(a.split(",")[0], "0,0,0");
		}
		
		// test start
		for (String a : monthRecords) {
			String fitness = a.split(",")[1];
			String time = a.split(",")[8];
			String[] test = a.split(",");
			String mapVal = fitnessMap.get(fitness + "-" + time);

			int amount = Integer.valueOf(test[2]) + Integer.valueOf(mapVal.split(",")[0]);

			int newrating = Integer.valueOf(test[4]) * Integer.valueOf(test[7]) + Integer.valueOf(mapVal.split(",")[1]);

			int count = Integer.valueOf(test[7]) + Integer.valueOf(mapVal.split(",")[2]);

			String newValue = String.valueOf(amount) + "," + String.valueOf(newrating) + "," + String.valueOf(count);
			// 1,yoga,300,good,2,attended,3,1
			
			fitnessMap.replace(fitness + "-" + time, newValue);
		}
		System.out.println("<<<<<<<--======Month Lesson Report======-->>>>>>>");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("Fitness\t\tTotal Amount\tAverage rating\t   Total Strength\tdate(YY-MM-DD)");
		System.out.println("--------------------------------------------------------------------------------------");
		for (Entry<String, String> cp : fitnessMap.entrySet()) {
			System.out.println(cp.getKey().split("-")[0] + "\t\t" + cp.getValue().split(",")[0] + "\t\t\t"
					+ Integer.valueOf(cp.getValue().split(",")[1]) / Integer.valueOf(cp.getValue().split(",")[2])
					+ "\t\t" + cp.getValue().split(",")[2] + "\t\t" + "2023-" + month + "-"
					+ cp.getKey().split("-")[1]);

		}
		System.out.println("--------------------------------------------------------------------------------------");

		

		/**
		 * for uniq lessons list append-- rating, count, amount uniFitName,
		 * rating*count, count, amount
		 * 
		 * for each lesson iterare li
		 * <li>split list and
		 */
	}

}
