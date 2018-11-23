package Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class TreeNode implements Comparable<TreeNode> {
	
	private TreeNode parent;
	private State currentState;
	private int depth;
	private double f;
	private double distance;
	
	public TreeNode(TreeNode parent, State currentState, int depth, String strategy) {
		this.parent = parent;
		this.currentState = currentState;
		this.depth = depth;
		//this.heuristic = currentState.getNodeList().size();
		setStrategy(strategy);
	
	}

	private void setStrategy(String strategy) {
		if (strategy.equals("BFS")) {
		
		}else if(strategy.equals("DFS") || strategy.equals("IDS")){
			
		}else if(strategy.equals("UCS")) {
			
		}else if(strategy.equals("A*")) {
			
}
	}

	//lnglat = [longitud=ejeX, latitud=ejey];
	public double setDistance(TreeNode tn1, Nodo tn2) {
		double[] lnglat1 = {Double.valueOf(tn1.getCurrentState().getNodo().getXAxis()), Double.valueOf(tn1.getCurrentState().getNodo().getYAxis())};
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
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}	
	public double getF() {
		return f;
	}
	
	public int compareTo(TreeNode tn) {
		return  (int) (this.f - tn.getF());
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
