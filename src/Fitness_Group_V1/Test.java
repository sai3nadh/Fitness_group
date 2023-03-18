package fitness_Group_V1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void mainn(String[] tag) {
			
			List<String> recordsList= new ArrayList<String>();
			System.out.println("avaiable bookings");
			try (// show available bookings
					BufferedReader br = new BufferedReader(new FileReader("temp.csv"))) {
					String line;
					while ((line = br.readLine()) != null) {   //returns a Boolean value  
						String[] Prices = line.split(",");    // use comma as separator  
						if(Prices.length>0) { // to skip the empty record
//							if(BookingID.equals(Prices[0])) {
//								recordsList.add(line);
								System.out.println(" " + Prices[0] + "\t\t" + Prices[1] +"count value");//+count); 
//							}
						}
					}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
	
	}
}
