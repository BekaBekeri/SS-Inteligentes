package Domain;

import java.util.*;

public class State {

	private OSMNode node;
	private ArrayList<OSMNode> listNodes;					//ordered list
	private String id;
	
	
	public State(){
		
	}
	
	public State(OSMNode nodo, ArrayList<OSMNode> nodeList){
		this.node = nodo;
		this.listNodes = nodeList;
	}

	public OSMNode getNodo() {
		return node;
	}

	public void setNodo(OSMNode nodo) {
		this.node = nodo;
	}

	public ArrayList<OSMNode> getNodeList() {
		return listNodes;
	}

	public void setNodeList(ArrayList<OSMNode> nodeList) {
		this.listNodes = nodeList;
	}
	
}
