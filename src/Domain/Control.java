package Domain;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.ScaleTransition;

public class Control {
	
	private static boolean solution;
	private static Graph grafo;
	private static Hashtable<String, Double> visited;		//<MD5, F>
	private static PriorityQueue<TreeNode> frontier;

	public static void ejecucionPrincipal(Problem problema, boolean prunning, String strategy, int depth) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException {
		
		//INICIALIZACION DE VARIABLES GLOBALES
		grafo = new Graph(problema.getGraphlmfile());
		solution = false;
		visited = new Hashtable<String, Double>();
		frontier = new PriorityQueue<TreeNode>();
		TreeNode tnInicial = obtenerTreeNode(problema, strategy);
		
		System.out.println("Empezando la ejcucion con el siguiente estado:" + 
						   "\n\t Node id: " + tnInicial.getCurrentState().getNodo().getId() + 
						   "\n\t List of goals: " + tnInicial.getCurrentState().getNodeList().toString() + 
						   "\n\t [Prunning = " + prunning+ ", maxDepth = " + depth + ", Strategy = " + strategy + "]");	
		
		search(tnInicial, prunning, depth, strategy);
		
		
	}
	
	
	private static void search(TreeNode tnInicial, boolean prunning, int depth, String strategy) throws NoSuchAlgorithmException {

		solution = false;
		int currentDepth = 0;
		
			
		if(strategy.equalsIgnoreCase("IDS")){
			do{
				currentDepth++;
				visited.clear();
				frontier.clear();
				frontier.add(tnInicial);
				realSearch(currentDepth, prunning, strategy);	
			}while(currentDepth <= depth);	
		}else{
			visited.clear();
			frontier.clear();
			frontier.add(tnInicial);
			realSearch(depth, prunning, strategy);
		}
		
		if(frontier.isEmpty()){
			System.out.println("[NO SOLUTION FOUND]");
		}else{
			if(isGoal(frontier.peek().getCurrentState())){
				System.out.println("[SOLUTION FOUND]");
				
				solution = true;
				System.out.println(createSolution(frontier.peek()));
			}
		}
		
		
		
	}

	private static void realSearch(int depth, boolean prunning, String strategy) throws NoSuchAlgorithmException {
		while(!frontier.isEmpty() && !isGoal(frontier.peek().getCurrentState()) && frontier.peek().getDepth() < depth){
			System.out.println("Expandiendo nodo: " + frontier.peek().toString());
			visited.put(md5(frontier.peek()), Math.abs(frontier.peek().getF()));
			generateSuccessors(frontier.poll(), prunning, strategy);
		}

	}


	private static String createSolution(TreeNode peek) {
		
		int aux = 0;
		String exit = "";
		Stack<TreeNode> solution = new Stack<TreeNode>();
		TreeNode fooNode = new TreeNode();
		TreeNode father = new TreeNode();
		fooNode = peek;
		
		do{	
			father = fooNode.getParent();
			solution.push(fooNode);
			fooNode = father;	
		}while(fooNode.getParent() != null);
		
		while(!solution.isEmpty()){
			
			fooNode = solution.pop();
			exit += "Step Nº" + ++aux + " \n\tnode id: " + fooNode.getCurrentState().getNodo().getId() + "\n\tGoal List :" + fooNode.getCurrentState().getNodeList().toString() + "\n";
		
		}
		
		
		return exit;
	}


	// AUXILIAR METHODS

	private static void generateSuccessors(TreeNode tn, boolean prunning, String strategy) throws NoSuchAlgorithmException {
		
		TreeNode original = tn;
		State fooState = new State();
		TreeNode fooTN = new TreeNode();
		ArrayList<TreeNode> adyacentes = new ArrayList<TreeNode>();
		adyacentes = grafo.adjacentNode(tn, strategy);							//aqui obtengo los hijos de treenode actual pero con el state sin modificar

		
		for(int i = 0; i < adyacentes.size(); i++){

			

			fooState.setNodo(adyacentes.get(i).getCurrentState().getNodo());															// el estado del hijo es igual a no se que se de
			fooState.setNodeList(tn.getCurrentState().getNodeList());
			if(fooState.getNodeList().contains(adyacentes.get(i).getCurrentState().getNodo())){						// el caso de que uno de los adyacentes sea uno de los subgoals
				fooState.getNodeList().remove(adyacentes.get(i).getCurrentState().getNodo());						// se elimina de la lista de nodos del estado hijo
				fooState.setMD5();																					// y se vuelve a calcular el md5
			}											
			
			
			fooTN = new TreeNode(tn, fooState, tn.getDepth() + 1, strategy, tn.setDistance(fooState.getNodo(), tn.getCurrentState().getNodo()));
			
			if(tn.getParent() != null){
				System.out.println(visited.size());
				//System.out.println(fooTN.getCurrentState().getNodo().getId() + " " + tn.getParent().getCurrentState().getNodo().getId());
				if(!fooTN.getCurrentState().getNodo().getId().equalsIgnoreCase(tn.getParent().getCurrentState().getNodo().getId())){
					if (prunning){
						if(checkVisited(fooTN)){
							frontier.add(fooTN);
						}
					}else{
						frontier.add(fooTN);
					}
				}	
			}else{
				if (prunning){
					if(checkVisited(fooTN)){
						frontier.add(fooTN);
					}
				}else{
					frontier.add(fooTN);
				}
			}
			
		}
	}
 
	private static boolean checkVisited(TreeNode fooTN) throws NoSuchAlgorithmException {
		
		if(visited.get(md5(fooTN)) != null){
			if(fooTN.getF() < visited.get(md5(fooTN))){
				visited.put(md5(fooTN), fooTN.getF());
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}

	private static boolean isGoal(State currentState) {
		if (currentState.getNodeList().isEmpty()){
			return true;
		}else{
			return false;
		}	
	}

	private static TreeNode obtenerTreeNode(Problem problema, String strategy) throws NoSuchAlgorithmException {
		TreeNode inicial = new TreeNode();
		State estado1 = new State();
		ArrayList<Nodo> lista = new ArrayList<Nodo>();
		
		estado1.setNodo(grafo.getNodeList().get(problema.getIntSt().getNode()));
		
		for(int i = 0; i < problema.getIntSt().getListNodes().size(); i++){
			lista.add(grafo.getNodeList().get(problema.getIntSt().getListNodes().poll()));
		}
		
		estado1 = new State(grafo.getNodeList().get(problema.getIntSt().getNode()), lista);
		inicial = new TreeNode(null, estado1, 0, strategy, 0);
		
		return inicial;
	}

	public static String md5(TreeNode tn) throws NoSuchAlgorithmException{
		
		String aux="";
		aux += tn.getCurrentState().getNodo().getId();
		//aux += tn.getF();
		//aux += tn.getDepth();			

		return aux;
	}
	
	// TESTING METHODS
	
	public static void Testing(){
		TreeNode tn;
		long t_ini, t_fin;
		
		LinkedList<TreeNode> lList;
		SortedSet<TreeNode> sSet;
		PriorityQueue<TreeNode> pQueue = null;
		
		Integer[] LinkedTest = {100, 1000, 10000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000};
		Integer[] SortedTest = {100, 1000, 10000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000};
		Integer[] PriorityTest = {100, 1000, 10000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000};
		
		//Collection con comparable
		for(int i = 0; i<LinkedTest.length; i++) {
			t_ini = System.currentTimeMillis();
			lList = new LinkedList<TreeNode>();
			for(int j = 0; j<LinkedTest[i] ; j++) {
				//tn = new TreeNode();
				//lList.add(tn);
			}
			t_fin = System.currentTimeMillis();
			System.out.println("Time LinkedList " + LinkedTest[i] + " nodes: " + (t_fin-t_ini) + " ms");
		}
		
		System.out.println();

		for(int i = 0; i<SortedTest.length; i++) {
			t_ini = System.currentTimeMillis();
			sSet = new TreeSet<TreeNode>();
			for(int j = 0; j<SortedTest[i]; j++) {
				//tn = new TreeNode();
				//sSet.add(tn);
			}
			t_fin = System.currentTimeMillis();
			System.out.println("Time SortedSet " + PriorityTest[i] + " nodes: " + (t_fin-t_ini) + " ms");
		}
		
		System.out.println();
		
		for(int i = 0; i<PriorityTest.length; i++) {
			t_ini = System.currentTimeMillis();
			pQueue = new PriorityQueue<TreeNode>();
			for(int j = 0; j<PriorityTest[i]; j++) {
				//tn = new TreeNode();
				//pQueue.add(tn);
			}
			t_fin = System.currentTimeMillis();
			System.out.println("Time PriorityQueue " + PriorityTest[i] + " nodes: " + (t_fin-t_ini) + " ms");
		}
		
		for(int i = 0; i < 100; i++){
			System.out.println("PriorityQ Node Nº: " + (i+1) + " with f: " + pQueue.poll().getF());	
		}
	}
	
}
