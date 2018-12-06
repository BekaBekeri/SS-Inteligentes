package Domain;

import java.util.*;
import Domain.TreeNode;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.*;

public class Control {

	private static boolean solution;
	private static Graph grafo;
	private static Hashtable<String, Double> visited;		//<MD5, F>
	private static PriorityQueue<TreeNode> frontier;
	private static OSMNode finalNode;
	
	public static void ejecucionPrincipal(Problem problema, boolean prunning, String strategy, int maxDepth) throws ParserConfigurationException, SAXException, IOException{
		
		solution = false;
		visited = new Hashtable<String, Double>();
		frontier = new PriorityQueue<TreeNode>();
		grafo = new Graph(problema.getGraphlmfile());
		
		TreeNode tnInicial = obtenerTreeNode(problema, strategy);
		
		System.out.println("[MACHINE]-Starting execution with the folowwing data: " +
		 				   "\n\t\t Node id: " + tnInicial.getCurrentState().getNodo().getId() + 
		 				   "\n\t\t List of goals: " + tnInicial.getCurrentState().getNodeList().toString() + 
		 				   "\n\t\t [Prunning = " + prunning+ ", maxDepth = " + maxDepth + ", Strategy = " + strategy + "]");	
		
		
		
		search(tnInicial, strategy, maxDepth, 1);
		boundedSearch(tnInicial, strategy, maxDepth);
		
		
	}

	private static void search(TreeNode tnInicial, String strategy, int maxDepth, int i) {

		int currentDepth = i;
		while(!solution && currentDepth <= maxDepth){
			boundedSearch(tnInicial, strategy, currentDepth);
			currentDepth = currentDepth + i;
		}	
	}

	private static void boundedSearch(TreeNode tnInicial, String strategy, int depth) {
		
		PriorityQueue<State> succList = new PriorityQueue<State>();
		PriorityQueue<TreeNode> tnSuccList = new PriorityQueue<TreeNode>();
		TreeNode tnActual = new TreeNode();
		
		while(!solution && !frontier.isEmpty()){
			
			tnActual = frontier.poll();
			
			if(isGoal(tnActual)){
				solution = true;
			}else{
				succList = generateSuccessors(tnActual.getCurrentState());
				tnSuccList = generateTNList(succList, tnActual, depth, strategy);
				frontier.addAll(tnSuccList);
			}	
		}
		
		if(solution){
			makeSolution();
		}
	}

	private static void makeSolution() {
		

		
	}

	private static PriorityQueue<TreeNode> generateTNList(PriorityQueue<State> succList, TreeNode tnActual, int depth, String strategy) {
		
		PriorityQueue<TreeNode> successors = new PriorityQueue<TreeNode>();
		
		for(int i = 0; i < succList.size(); i++){
			
			double cost = calculateDistance(successors.peek().getCurrentState().getNodo(), tnActual.getCurrentState().getNodo());
			TreeNode child = new TreeNode(tnActual, succList.poll(), depth, strategy, cost);
			successors.add(child);
		}
		
		return successors;
	}

	private static PriorityQueue<State> generateSuccessors(State currentState) {
		
		PriorityQueue<State> successors = new PriorityQueue<State>();
		PriorityQueue<OSMNode> adjacents = grafo.obtainAdjacents(currentState.getNodo());
		ArrayList<OSMNode> goals = currentState.getNodeList();
		
		for(int i = 0; i < adjacents.size(); i++){
			State childState = new State();
			childState.setNodo(adjacents.poll());
			if(goals.contains(childState.getNodo())){
				goals.remove(childState.getNodo());
			}
			childState.setNodeList(goals);	
			successors.add(childState);
		}
		
		return successors;
	}

	private static boolean isGoal(TreeNode tnActual) {
		if(tnActual.getCurrentState().getNodeList().isEmpty()){
			return true;
		}
		return false;
	}

	//lnglat = [longitud=ejeX, latitud=ejey];
	public static double calculateDistance(OSMNode tn1, OSMNode tn2) {
		double[] lnglat1 = {Double.valueOf(tn1.getXAxis()), Double.valueOf(tn1.getYAxis())};
		double[] lnglat2 = {Double.valueOf(tn2.getXAxis()), Double.valueOf(tn2.getYAxis())};
		double earthR = 6371009;
			
			
		double phi1 = Math.toRadians(lnglat1[1]);
		double phi2 = Math.toRadians(lnglat2[1]);
		double diffPhi = phi2 - phi1;
			
		double theta1 = Math.toRadians(lnglat1[0]);
		double theta2 = Math.toRadians(lnglat2[0]);
		double diffTheta = theta2 - theta1;
			
		double h = Math.pow(Math.sin(diffPhi/2), 2) + Math.pow(Math.sin(Math.cos(phi1) * Math.cos(phi2) * diffTheta/2), 2);
		double arc = 2 * Math.asin(Math.sqrt(h));
		
		return arc * earthR;
	}
	
	private static TreeNode obtenerTreeNode(Problem problema, String strategy) {
		TreeNode inicial = new TreeNode();
		State estado1 = new State();
		ArrayList<OSMNode> lista = new ArrayList<OSMNode>();
		
		estado1.setNodo(grafo.getNodeList().get(problema.getIntSt().getNode()));
		
		for(int i = 0; i < problema.getIntSt().getListNodes().size(); i++){
			lista.add(grafo.getNodeList().get(problema.getIntSt().getListNodes().poll()));
		}
		
		estado1 = new State(grafo.getNodeList().get(problema.getIntSt().getNode()), lista);
		inicial = new TreeNode(null, estado1, 0, strategy, 0);
		
		finalNode = inicial.getCurrentState().getNodeList().get(inicial.getCurrentState().getNodeList().size()-1);
		
		return inicial;
	}
}
