package Domain;

public class OSMEdge {
	private String source;
	private String target;
	private String length;
	private String name;
	
	public OSMEdge(String source, String target, String length, String name) {
		this.source = source;
		this.target = target;
		this.length = length;
		this.name = name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
