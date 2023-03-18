package fitness_Group_V1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class  CustomerInfo {
	static HashMap<String, String> custMapInfo ;
	
	public void custInfo(){
		 List<List<String>> cust = new ArrayList<List<String>>();
		 //cust.addAll("a","b");
		 List<List<String>> CustIDlists = Arrays.asList(
                 Arrays.asList("C001","cname1"),
                 Arrays.asList("C002","cname2"),
                 Arrays.asList("C003","cname3"),
                 Arrays.asList("C004","cname4"),
                 Arrays.asList("C005","cname5"),
                 Arrays.asList("C006","cname6"),
                 Arrays.asList("C007","cname7"),
                 Arrays.asList("C008","cname8"),
                 Arrays.asList("C009","cname9"),
                 Arrays.asList("C010","cname10"));

		 //HashMap<String, String> 
		 custMapInfo = new HashMap<String, String>();
//			custMapInfo.put(null, null);
			custMapInfo.put("C001","cname1");
			custMapInfo.put("C002","cname2");
			custMapInfo.put("C003","cname3");
			custMapInfo.put("C004","cname4");
			custMapInfo.put("C005","cname5");
			custMapInfo.put("C006","cname6");
			custMapInfo.put("C007","cname7");
			custMapInfo.put("C008","cname8");
			custMapInfo.put("C009","cname9");
            custMapInfo.put("C010","cname10");
	}
	
	public boolean isCustValid(String CustID) {
		CustomerInfo cinf= new CustomerInfo();
		cinf.custInfo();
		//return false;
		return custMapInfo.containsKey(CustID);
	}

}
