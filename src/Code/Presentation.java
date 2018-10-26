package Code;

import java.io.FileReader;
import java.util.Iterator;


import org.json.*;

public class Presentation {					//JSON READER
	
	
	public static void main(){
		
		JSONParser parser = new JSONParser();
		
		try{
		
			Object obj = parser.parse(new FileReader("jsonPrueba.json"));
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
