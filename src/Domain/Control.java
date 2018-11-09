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

import Presentation.Presentation;

public class Control {
	
	private static boolean solution;
	private static Graph grafo;
	private static Hashtable<String, TreeNode> visited;
	private static PriorityQueue<TreeNode> frontier;

	public static void ejecucionPrincipal(Problem problema, boolean prunning, String strategy, int depth) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException {
		
		//INICIALIZACION DE VARIABLES GLOBALES
		grafo = new Graph(problema.getGraphlmfile());
		solution = false;
		visited = new Hashtable<String, TreeNode>();
		frontier = new PriorityQueue<TreeNode>();
		
		//VARIABLES LOCALES
		int currentDepth = 0;
		TreeNode tnInicial = obtenerTreeNode(problema);
		
		do{
			visited.clear();
			frontier.clear();
			frontier.add(tnInicial);
			
			while(!frontier.isEmpty() && !isGoal(frontier.peek().getCurrentState())){
				visited.put(frontier.peek().md5(), frontier.peek());
				generateSuccessors(frontier.poll());
			}
			currentDepth++;
			
		}while(currentDepth <= depth && frontier.isEmpty());
		
		
		
		
		/*switch(strategy){
		
			case "BFS":
					breathFirstSearch(nodoInicial, prunning);
				break;
			case "DFS":
					depthFirstSearch(nodoInicial, prunning, depth);
				break;
			case "DLS":
					depthLimitedSearch(nodoInicial, prunning, depth);
				break;
			case "IDS":
					iterativeDeepeningSearch(nodoInicial, prunning, depth);
				break;
			case "UCS":
					uniformCostSearch(nodoInicial, prunning, depth);
				break;
			default:
				System.out.println("ERROR WHILE TRYING TO PROCESS THE SEARCH ALGORITHM");
		}*/
		
		
	}
	
	private static void generateSuccessors(TreeNode tn) {
		
		ArrayList<TreeNode> adyacentes = new ArrayList<TreeNode>();
		adyacentes = grafo.adjacentNode(tn);						//aqui obtengo los hijos de treenode actual pero con el state sin modificar
		
		
		
		
	}

	private static boolean isGoal(State currentState) {
		if (currentState.getNodeList().isEmpty()){
			return true;
		}else{
			return false;
		}	
	}

	private static TreeNode obtenerTreeNode(Problem problema) throws NoSuchAlgorithmException {
		TreeNode inicial = new TreeNode();
		State estado1 = new State();
		ArrayList<Nodo> lista = new ArrayList<Nodo>();
		
		estado1.setNodo(grafo.getNodeList().get(problema.getIntSt().getNode()));
		
		for(int i = 0; i < problema.getIntSt().getListNodes().size(); i++){
			lista.add(grafo.getNodeList().get(problema.getIntSt().getListNodes().poll()));
		}
		
		estado1 = new State(grafo.getNodeList().get(problema.getIntSt().getNode()), lista);
		inicial = new TreeNode(null, estado1, 0);
		
		return inicial;
	}

	private static void depthFirstSearch(Nodo nodoInicial, boolean prunning, int depth) {
	}

	private static void breathFirstSearch(Nodo nodoInicial, boolean prunning) {
	}

	public static ArrayList<TreeNode> depthLimitedSearch(Nodo nodoInicial, boolean prunning, int depth){
		
		ArrayList<TreeNode> solucion = new ArrayList<TreeNode>();
		PriorityQueue<TreeNode> fringe = new PriorityQueue<TreeNode>();
				
		
		
		return solucion;
		
	}
	
	private static void iterativeDeepeningSearch(Nodo nodoInicial, boolean prunning, int depth) {
	}
	
	private static void uniformCostSearch(Nodo nodoInicial, boolean prunning, int depth) {	
	}
	
	
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
