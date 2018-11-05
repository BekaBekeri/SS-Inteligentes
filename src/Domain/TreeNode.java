package Domain;

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
	
}
