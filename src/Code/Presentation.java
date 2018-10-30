package Code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;


import com.google.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class Presentation {					//JSON READER
	
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
		
		Problem problem;
		boolean prunning = false;
		
		problem = readJson();
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

	public static Problem readJson() throws UnsupportedEncodingException, IOException {
		Problem init = null;
		Gson gson =  new Gson();
			
		try{
			BufferedReader br = new BufferedReader(new FileReader("jsonPrueba.json"));
			init = gson.fromJson(br, Problem.class);
			System.out.println(init.toString()
					);
		}catch(IOException e){
			e.printStackTrace();
		}
			
		

		return init;

	}
}
