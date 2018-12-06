package Domain;

import java.util.*;

public class StateString {

	private String node;
	private PriorityQueue<String> listNodes;					//ordered list
	private String id;
	
	public String getNode() {
		return node;
	}
	
	public void setNode(String node) {
		this.node = node;
	}
	
	public PriorityQueue<String> getListNodes() {
		return listNodes;
	}
	
	public void setListNodes(PriorityQueue<String> listNodes) {
		this.listNodes = listNodes;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StateString [node=" + node + ", listNodes=" + listNodes + ", id=" + id + "]";
	}
	
																		
	
	
}
