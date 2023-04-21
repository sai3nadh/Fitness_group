package com.fitness.wfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class MonthlyChampion {
	
	public boolean monthlyChamReport(int month)  {
	CSVController csvoper = new CSVController();
		
		
		List<String> monthRecords = csvoper.getMonthRecords(month);
		List<String> uniFitness = new ArrayList<String>();
		
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
			fitnessMap.replace(fitness, newValue);
		}


		List< String> newlis2t= new ArrayList< String>();
		int tempMax=0;
		String key=null,value="";
		int count=fitnessMap.size();
		// iterates till the HashMap having the elements
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
			newlis2t.add(key+","+value);
			fitnessMap.remove(key);		
			count=fitnessMap.size();		
		 }
		 
		 
		int Rank=1;
		System.out.println("<<<<<<<--======Monthly Champion Report======-->>>>>>>");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Rank\tFitness\t\tTotal Amount\tAverage rating\t   Total Strength");
		System.out.println("---------------------------------------------------------------------------");
		for( String a :newlis2t) {
			System.out.println(Rank+"\t"+a.split(",")[0]+"\t\t"+a.split(",")[1]+"\t\t\t"
					+ Integer.valueOf(a.split(",")[2])/Integer.valueOf(a.split(",")[3])+"\t\t"
					+a.split(",")[3]);
			Rank++;
		}
		System.out.println("---------------------------------------------------------------------------");
		
		return true;
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
