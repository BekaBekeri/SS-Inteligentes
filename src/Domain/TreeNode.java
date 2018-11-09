package Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

public class TreeNode implements Comparable<TreeNode> {
	
	TreeNode parent;
	State currentState;
	int depth;
	float f;
	
	public TreeNode(TreeNode parent, State currentState, int depth) {
		this.parent = parent;
		this.currentState = currentState;
		this.depth = depth;
		this.f = (float) Math.random()*20000000 + 1;
	}

	public TreeNode() {
		// TODO Auto-generated constructor stub
	}

	public float getF() {
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

	public void setF(float f) {
		this.f = f;
	}
	
	public String toString(){
		String aux = "";
		if(parent != null){
			aux += "TreeNode parent: " + parent.toString() + ", CurrentState: " + currentState.toString() + ", depth: " + depth + ", f: " + f + ".\n";
		}else{
			aux += "TreeNode parent: " + null + ", CurrentState: " + currentState.toString() + ", depth: " + depth + ", f: " + f + ".\n";
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
