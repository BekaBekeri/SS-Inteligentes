package Code;

import java.io.FileReader;
import java.util.Iterator;


import org.json.*;

import java.util.*;

public class Presentation {					//JSON READER
	
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(){
		
		String jsonFile = "";
		boolean prunning = false;
		
		readJson(jsonFile);
		prunning = askPrunning();
		
	}
		
	private static boolean askPrunning() {
	boolean aux = false;
	int option = 0;
	do {
		
		System.out.println("Would you like to apply prunning in this search?\n" + 
				   "1.-Yes\n" + 
				   "2.-No\n");
		option = sc.nextInt();
		if(option == 1) aux = true;
		else if(option == 2) aux = false;
	}while(option<1 || option>2);
	return false;
}

	private static void readJson(String jsonFile) {
		JSONParser parser = new JSONParser();
		
		try{
		
			Object obj = parser.parse(new FileReader("jsonPrueba.json"));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
