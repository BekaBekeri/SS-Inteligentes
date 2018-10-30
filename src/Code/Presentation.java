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

public class Presentation {					//JSON READER
	
	
	public Nodo getInitialNode() throws UnsupportedEncodingException, IOException{
		
		Nodo nodo;
		
		try(Reader reader = new InputStreamReader(Presentation.class.getResourceAsStream("jsonPrueba.json"), "UTF-8")){
			Gson gson =  new GsonBuilder().create();
			nodo = gson.fromJson(reader, Nodo.class);
			System.out.println("nodo" + nodo.toString());
		}
		return nodo;
	}
}
