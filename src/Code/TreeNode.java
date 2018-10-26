package Code;

public class TreeNode implements Comparable<TreeNode> {
	TreeNode parent;
	State currentState;
	int depth;
	float f;
	
	public TreeNode() {
		f = (float) Math.random()*20000000 + 1;
	}
	
	public float getF() {return f;}
	
	public int compareTo(TreeNode tn) {
		return (int) (this.f - tn.getF());
	}
	
}
