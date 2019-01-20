package Domain;

import java.util.ArrayList;

public class TreeNode implements Comparable<TreeNode>{

	
	private TreeNode parent;
	private State currentState;
	private int depth;
	private double f;
	private double cost;
	private double heuristic;
	
	
	public TreeNode(TreeNode parent, State currentState, int depth, String strategy, double cost, int hc) {
		this.parent = parent;
		this.currentState = currentState;
		if(parent != null){
			this.cost = parent.getCost() + cost;
			this.depth = parent.getDepth() + 1;
		}else{
			this.cost = cost;
			this.depth = depth;
		}
		if( hc == 1 || hc == 2) setHeuristic(currentState, hc);
		setStrategy(strategy);
	
	}

	private void setHeuristic(State currentState2, int hc) {
		
		double min = 99999999, min2 = 9999999;
		double aux = 0, aux2 = 0;
		ArrayList<Double> distances = new ArrayList<Double>();
		
	
		for(int i = 0; i < currentState.getNodeList().size(); i++){
			aux = Control.calculateDistance(currentState.getNodo(), currentState.getNodeList().get(i));
			if(aux < min){
				min = aux;
				aux = 0;
			}
		}
		
		for(int i = 0; i < currentState.getNodeList().size() -1; i++){
			
			for(int j = i+1; j < currentState.getNodeList().size(); j++){
				
				aux2 = Control.calculateDistance(currentState.getNodeList().get(i), currentState.getNodeList().get(j));
				if(aux2 < min2){
					min2 = aux2;
					aux2 = 0;
				}
			}
			
		}
		
		if(hc == 1){
			
			this.heuristic = min;
		
		}
		else if(hc == 2){
		
			this.heuristic = min + min2;
			
		}
		
	}

	private void setStrategy(String strategy) {
		if (strategy.equals("BFS")) {
			f = depth;
		}else if(strategy.equals("DFS") || strategy.equals("IDS")){
			f = -depth;
		}else if(strategy.equals("UCS")) {
			f = cost; 
		}else if(strategy.equals("A*")) {
			f = cost + heuristic;
}
	}

	

	public TreeNode() {
	
	}

	public double getF() {
		return f;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	public int compareTo(TreeNode tn) {
		int r = 0;
		
		if(tn.getF() > this.f){
			r = -1;
		}
		else if(tn.getF() < this.f){
			r = +1;
		}
		
		return r;
	
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setF(double f) {
		this.f = f;
	}
	
	public String toString(){
		String aux = "";
		if(parent != null){
		
		}else{
		
		}
		
		return aux;
		
	}
	
	
}
