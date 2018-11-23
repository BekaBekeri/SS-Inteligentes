package Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class TreeNode implements Comparable<TreeNode> {
	
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

	//lnglat = [longitud=ejeX, latitud=ejey];
	public double setDistance(Nodo tn1, Nodo tn2) {
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
		String aux = "TREENODE: ";
		if(parent != null){
			aux += "\n\t Parent: " + parent.getCurrentState().getNodo().getId() + ", CurrentState: " + currentState.toString() + ", depth: " + depth + ", f: " + f + ".";
		}else{
			aux += "\n\t Parent: " + null + ", CurrentState: " + currentState.toString() + ", depth: " + depth + ", f: " + f + ".";
		}
		
		return aux;
		
	}
	
	public String md5() throws NoSuchAlgorithmException{
		String aux="";
		
		aux += currentState.toString();
		aux += f;
		aux += depth;			
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(aux.getBytes());
		byte[] digest = md.digest();
		aux = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return aux;
	}
	
}
