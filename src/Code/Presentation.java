package Code;

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
	
	public static void main() throws UnsupportedEncodingException, IOException{
		
		
		boolean prunning = false;
		
		readJson();
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

	public static Nodo readJson() throws UnsupportedEncodingException, IOException {
		Nodo nodo;
		
		try(Reader reader = new InputStreamReader(Presentation.class.getResourceAsStream("jsonPrueba.json"), "UTF-8")){
			Gson gson =  new GsonBuilder().create();
			nodo = gson.fromJson(reader, Nodo.class);
			System.out.println("nodo" + nodo.toString());
		}

		return nodo;

	}
}
