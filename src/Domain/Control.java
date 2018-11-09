package Domain;

import java.io.IOException;
import java.util.ArrayList;
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
	private static PriorityQueue<TreeNode> visited;
	private static PriorityQueue<TreeNode> frontier;
	private static ArrayList<Nodo> adyacentes;

	public static void ejecucionPrincipal(Problem problema, boolean prunning, String strategy, int depth) throws ParserConfigurationException, SAXException, IOException {
		
		grafo = new Graph(problema.getGraphlmfile());
		solution = false;
		visited = new PriorityQueue<TreeNode>();
		frontier = new PriorityQueue<TreeNode>();
		adyacentes = new ArrayList<Nodo>();
		State estadoInicial = new State();
		Nodo nodoInicial = new Nodo();
		
		nodoInicial = grafo.getNodeList().get(problema.getIntSt().getNode());	
		adyacentes = grafo.adjacentNode(nodoInicial.getId());
		System.out.println("Adyacentes: " + adyacentes.toString());
		
	}
	
	public static ArrayList<TreeNode> Depth_Limited_Search(Problem problem, String strategy, int MAX_deep){
		
		ArrayList<TreeNode> solucion = new ArrayList<TreeNode>();
		PriorityQueue<TreeNode> fringe = new PriorityQueue<TreeNode>();
				
		
		
		return solucion;
		
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
