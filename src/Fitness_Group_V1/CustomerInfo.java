package fitness_Group_V1;

import java.util.HashMap;

public class  CustomerInfo {
	public static HashMap<String, String> custMapInfo ;
	
	public void custInfo(){
		
		 
		 custMapInfo = new HashMap<String, String>();

			custMapInfo.put("C001","Alice");
			custMapInfo.put("C002","Bob");
			custMapInfo.put("C003","Smith");
			custMapInfo.put("C004","Jack");
			custMapInfo.put("C005","Robert");
			custMapInfo.put("C006","Dave");
			custMapInfo.put("C007","Jorge");
			custMapInfo.put("C008","Jackson");
			custMapInfo.put("C009","Gilbert");
            custMapInfo.put("C010","John");
	}
	
	public boolean isCustValid(String CustID) {
		CustomerInfo cinf= new CustomerInfo();
		cinf.custInfo();
		//return false;
		return custMapInfo.containsKey(CustID);
	}

}
