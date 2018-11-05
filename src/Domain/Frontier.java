package Domain;

import java.util.PriorityQueue;

public class Frontier {

	PriorityQueue<TreeNode> frontier;
	
	public Frontier(PriorityQueue<TreeNode> frontier){
		this.frontier = frontier;
	}
	
	public Frontier(){
		frontier = new PriorityQueue<TreeNode>();
	}
	
	public void insert(TreeNode tnode){	
		frontier.add(tnode);
	}

	public void remove(){
		frontier.poll();
	}

	public boolean isEmpty(){	
		return frontier.isEmpty();
	}
	
}
