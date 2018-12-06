package Domain;

public class TreeNode {

	
	private TreeNode parent;
	private State currentState;
	private int depth;
	private double f;
	private double cost;
	private double heuristic;
	
	
	public TreeNode(TreeNode parent, State currentState, int depth, String strategy, double cost) {
		this.parent = parent;
		this.currentState = currentState;
		if(parent != null){
			this.cost = parent.getCost() + cost;
			this.depth = parent.getDepth() + 1;
		}else{
			this.cost = cost;
			this.depth = depth;
		}
		setHeuristic(currentState);
		setStrategy(strategy);
	
	}

	private void setHeuristic(State currentState2) {
		double min = 99999999;
		double aux = 0;
		
		for(int i = 0; i < currentState.getNodeList().size(); i++){
			aux = setDistance(currentState.getNodo(), currentState.getNodeList().get(i));
			if(aux < min){
				min = aux;
				aux = 0;
			}
		}
		this.heuristic = min;
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

	//public int compareTo(TreeNode tn) {
	
	//}

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
