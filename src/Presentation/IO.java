package Presentation;

import java.io.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.gson.Gson;

import Domain.Control;
import Domain.Problem;

public class IO {

	static Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		
		boolean prunning = false;
		Problem problem;
		String strategy = "";
		int depth = -1;
		
		System.out.println("[MACHINE]-WELCOME TO THE FINAL VERSION OF THE INTELLIGENT SYSTEMS LABORATORY PROJECT");
		System.out.println("[MACHINE]-NOW THE INITIAL DATA WILL BE ASKED TO THE USER\n");
		
		problem = readJson();
		strategy = askStrategy();
		prunning = askPrunning();
		depth = askDepth();
		
		Control.ejecucionPrincipal(problem, prunning, strategy, depth);
		
	}

	private static int askDepth() {
		int depth=-1;
		int depth_HC = 999;
		int choose = -1;
		
		do{
			System.out.println("Would you like to choose a specific depth for the search algorithm?\n" + 
						   	   "1.- Yes.\n" + 
						       "2.- No.");
			choose = sc.nextInt();
		}while(choose < 1 || choose > 2);
		
		if(choose == 1){
			while (depth<=0) {
			System.out.println("Introduce the maxium depth: ");
				try {
					depth = sc.nextInt();
					sc.nextLine();
				}catch (Exception e) {
					System.out.println("Introduce an integer number greater than 0. Don't introduce a number too big either, or the program may not find any solution.");
				}
			}
		}else if(choose == 2){
			depth = depth_HC;
		}
		
		return depth;
	}
	
	private static boolean askPrunning() {
		boolean aux = false;
		int option = 0;
		do {
			
			System.out.println("Would you like to apply prunning in this search?\n" + 
					   "1.-Yes\n" + 
					   "2.-No");
			option = sc.nextInt();
			if(option == 1) aux = true;
			else if(option == 2) aux = false;
		}while(option<1 || option>2);
		return aux;
	}
	
	private static String askStrategy() throws InterruptedException {
		String strategy=null;
		int option= -1;
		
		while (option<1 || option>6) {
			System.out.println("Choose an strategy. Note that all of them are limited by previous maximun depth introduced: ");
			System.out.println("1-BFS (Breath-first search).");
			System.out.println("2-DFS (Depth-first search).");
			System.out.println("3-IDS (Iterative deepening search).");
			System.out.println("4-DLS (Depth limited search");
			System.out.println("5-UCS (Uniform cost search).");
			System.out.println("6-A*");
			option = integerController();
			switch (option){
			case 1:
				strategy = "BFS";
				break;
			case 2:
				strategy = "DFS";
				break;
			case 3:
				strategy = "IDS";
				break;
			case 4:
				strategy = "DLS";
				break;
			case 5:
				strategy = "UCS";
				break;
			case 6:
				strategy = "A*";
				break;
			default:
				System.out.println("Please introduce 1,2,3, 4, 5 or 6.\n");
				option=-1;
				sc.nextLine();
			}
		}
		
		return strategy;
	}

	private static Problem readJson() {
		Problem init = new Problem();
		Gson gson =  new Gson();
			
		try{
			BufferedReader br = new BufferedReader(new FileReader("ejemploA1.json"));
			init = gson.fromJson(br, Problem.class);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return init;
	}
	
	public static int integerController() throws InterruptedException{
		 
		 while(!sc.hasNextInt()){
	        System.err.println("The options can only be integers.");
	        System.out.println("Try again:");
	        sc.next();
	     }
	     int n = sc.nextInt();
	     return n;
	 }
}
	
