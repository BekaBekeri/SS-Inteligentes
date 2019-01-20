package Domain;

import java.util.*;
import Domain.TreeNode;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.*;

public class Control {

	private static boolean solution;
	private static Graph grafo;
	private static ArrayList<String> visited;		//<MD5, F>
	private static PriorityQueue<TreeNode> frontier;
	private static int hc;
	
	public static void ejecucionPrincipal(Problem problema, boolean prunning, String strategy, int maxDepth, int heu) throws ParserConfigurationException, SAXException, IOException{
		
		solution = false;
		visited = new ArrayList<String>();
		frontier = new PriorityQueue<TreeNode>();
		grafo = new Graph(problema.getGraphlmfile());
		hc = heu;
		TreeNode tnInicial = obtenerTreeNode(problema, strategy);
		frontier.add(tnInicial);
		
		System.out.println("[MACHINE]-Starting execution with the folowwing data: " +
		 				   "\n\t\t Node id: " + tnInicial.getCurrentState().getNodo().getId() + 
		 				   "\n\t\t List of goals: " + tnInicial.getCurrentState().getNodeList().toString() + 
		 				   "\n\t\t [Prunning = " + prunning+ ", maxDepth = " + maxDepth + ", Strategy = " + strategy + "]");	
		
		
		
		search(tnInicial, strategy, maxDepth, 1);
		
	}

	private static void search(TreeNode tnInicial, String strategy, int maxDepth, int i) {

		int currentDepth = i;
		while(!solution && currentDepth <= maxDepth){
			boundedSearch(tnInicial, strategy, currentDepth);
			currentDepth = currentDepth + i;
		}	
	}

	private static void boundedSearch(TreeNode tnInicial, String strategy, int depth) {
		
		ArrayList<State> succList = new ArrayList<State>();
		PriorityQueue<TreeNode> tnSuccList = new PriorityQueue<TreeNode>();
		TreeNode tnActual = new TreeNode();
		
		
		while(!solution && !frontier.isEmpty()){
			
			tnActual = frontier.poll();
			System.out.println("\tEjecutando tnActual " + tnActual.getCurrentState().getNodo().getId());
			if(isGoal(tnActual)){
				solution = true;
			}else{
				succList = generateSuccessors(tnActual.getCurrentState());
				tnSuccList = generateTNList(succList, tnActual, depth, strategy);
				frontier.addAll(tnSuccList);
			}	
		}
		
		if(solution){
			makeSolution(tnActual);
		}
	}

	private static void makeSolution(TreeNode tnSol) {
		
		Stack<TreeNode> solutionS = new Stack<TreeNode>();
		TreeNode auxTn = tnSol;
		
		int paso = 1;
		
		do{
			solutionS.push(auxTn);
			auxTn = auxTn.getParent();
		}while(auxTn.getParent() != null);
		
		
		System.out.println("Starting from --> " + solutionS.pop().getCurrentState().getNodo().getId());
		do{
			System.out.println("Step Nº: " + paso + "\n\tGoing to --> " + solutionS.pop().getCurrentState().getNodo().getId());
			paso++;
		}while(!solutionS.isEmpty());
		
	}

	private static PriorityQueue<TreeNode> generateTNList(ArrayList<State> succList, TreeNode tnActual, int depth, String strategy) {
		
		PriorityQueue<TreeNode> successors = new PriorityQueue<TreeNode>();
		
		for(int i = 0; i < succList.size(); i++){
			//System.out.println("\t\t TnsuccAct " + succList.get(i).getNodo().getId());
			
			double cost = Math.abs(calculateDistance(succList.get(i).getNodo(), tnActual.getCurrentState().getNodo()));
			TreeNode child = new TreeNode(tnActual, succList.get(i), depth, strategy, cost, hc);
			successors.add(child);
		}
		
		return successors;
	}

	private static ArrayList<State> generateSuccessors(State currentState) {
		
		ArrayList<State> successors = new ArrayList<State>();
		visited.add(currentState.getNodo().getId());
		ArrayList<OSMNode> adjacents = grafo.obtainAdjacents(currentState.getNodo());
		ArrayList<OSMNode> goals = currentState.getNodeList();
		
		for(int i = 0; i < adjacents.size(); i++){
			if(!visited.contains(adjacents.get(i).getId())){
				State childState = new State();
				childState.setNodo(adjacents.get(i));
				if(goals.contains(childState.getNodo())){
					goals.remove(childState.getNodo());
					System.out.println("REMOVIENDO SUBGOAL " + childState.getNodo().getId());
				}
				childState.setNodeList(goals);	
				successors.add(childState);
			}
		}
		
		return successors;
	}

	private static boolean isGoal(TreeNode tnActual) {
		if(tnActual.getCurrentState().getNodeList().isEmpty()){
			return true;
		}
		return false;
	}

	
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
		inicial = new TreeNode(null, estado1, 0, strategy, 0, hc);
		
		return inicial;
	}
}
