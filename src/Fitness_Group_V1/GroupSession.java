package Fitness_Group_V1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		for(int i=0;i<=99;i++) {
			System.out.println(System.lineSeparator());
		}
		System.out.println("Available Goup Sessions & Prices are ");
		
		// reading the csv file to print the prices and  fitness 
		  
		try{  
		//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("/Users/taguresavati/Documents/Prices.csv"));
			while ((line = br.readLine()) != null) {   //returns a Boolean value  
				String[] Prices = line.split(splitBy);    // use comma as separator  
				System.out.println(" " + Prices[0] + "                      " + Prices[1]);  
			}  
		}   
		catch (IOException e){  
		e.printStackTrace();  
		}  
		
		System.out.println(System.lineSeparator()+"The Session is Booked Successfully");
		
		return true;
	}
}