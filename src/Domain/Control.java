package Domain;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Control {
	
	private static boolean solution;
	private static Graph grafo;
	private static Hashtable<String, Double> visited;		//<MD5, F>
	private static PriorityQueue<TreeNode> frontier;
	private static Nodo lastNode;

	public static void ejecucionPrincipal(Problem problema, boolean prunning, String strategy, int depth) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException {
		
		//INICIALIZACION DE VARIABLES GLOBALES
		grafo = new Graph(problema.getGraphlmfile());
		solution = false;
		visited = new Hashtable<String, Double>();
		frontier = new PriorityQueue<TreeNode>();
		TreeNode tnInicial = obtenerTreeNode(problema, strategy);
		lastNode = tnInicial.getCurrentState().getNodeList().get(tnInicial.getCurrentState().getNodeList().size()-1);
		
		search(tnInicial, prunning, depth, strategy);
		
	}
	
	
	private static void search(TreeNode tnInicial, boolean prunning, int depth, String strategy) throws NoSuchAlgorithmException {
		int currentDepth;
		solution = false;
		
		if(strategy.equalsIgnoreCase("IDS")){
			currentDepth = 0;
		}else{
			currentDepth = depth;
		}
		
		do{
			visited.clear();
			frontier.clear();
			frontier.add(tnInicial);
			
			while(!frontier.isEmpty() && isGoal(frontier.peek().getCurrentState())){
				
				visited.put(frontier.peek().md5(), frontier.peek().getF());
				generateSuccessors(frontier.poll(), prunning, strategy);
			}
			
			currentDepth++;
			
		}while(currentDepth<=depth && frontier.isEmpty());
		
		
	}

	// AUXILIAR METHODS

	private static void generateSuccessors(TreeNode tn, boolean prunning, String strategy) throws NoSuchAlgorithmException {
		
		Nodo fooNode = new Nodo();
		State fooState = new State();
		TreeNode fooTN = new TreeNode();
		ArrayList<TreeNode> adyacentes = new ArrayList<TreeNode>();
		adyacentes = grafo.adjacentNode(tn);							//aqui obtengo los hijos de treenode actual pero con el state sin modificar
		fooState = tn.getCurrentState();

		for(int i = 0; i < adyacentes.size(); i++){
			//creacion nuevo tn
			fooTN = adyacentes.get(i);																				// obtenemos el adyacente actual
			fooTN.setDepth(tn.getDepth() + 1);																		// su profundidad es la del padre + 1
			fooTN.setParent(tn);																					// definimos su padre
			// creacion nuevo state								
			fooState = fooTN.getCurrentState();																		// el estado del hijo es igual a no se que se de
			if(fooState.getNodeList().contains(adyacentes.get(i).getCurrentState().getNodo())){						// el caso de que uno de los adyacentes sea uno de los subgoals
				fooState.getNodeList().remove(adyacentes.get(i).getCurrentState().getNodo());						// se elimina de la lista de nodos del estado hijo
				fooState.setMD5();																					// y se vuelve a calcular el md5
			}
			//obtencion del nodo adyacente al que se mueve 
			fooNode = adyacentes.get(i).getCurrentState().getNodo();												
			fooState.setNodo(fooNode);
			fooTN.setCurrentState(fooState);
			fooTN.setF(fooTN.setDistance(fooTN, lastNode));															// distance from each node to the last one 
			//TODO FOOTN SET STRATEGY
			if (prunning){																							// OPTIMIZATION
				if(checkVisited(fooTN)){
					frontier.add(fooTN);
				}
			}else{
				frontier.add(fooTN);
			}
			
		}
	}
 
	private static boolean checkVisited(TreeNode fooTN) throws NoSuchAlgorithmException {
		
		if(visited.get(fooTN.md5()) != null){
			if(fooTN.getF() < visited.get(fooTN.md5())){
				visited.put(fooTN.md5(), fooTN.getF());
				return true;
			}else{
				return true;
			}
		}else{
			return false;
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
		inicial = new TreeNode(null, estado1, 0, strategy);
		
		return inicial;
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
			System.out.println("PriorityQ Node Nº: " + (i+1) + " with f: " + pQueue.poll().f);	
		}
	}
	
}
