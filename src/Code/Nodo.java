package Code;

public class Nodo {
	
	private String id;
	private String d4;		// D4 = Y axis
	private String d5;		// D5 = X axis
	private String d6;
	
	public Nodo(String id, String d4, String d5, String d6) {
		super();
		this.id = id;
		this.d4 = d4;
		this.d5 = d5;
		this.d6 = d6;
	}

	public String getD4() {
		return d4;
	}

	public void setD4(String d4) {
		this.d4 = d4;
	}

	public String getD5() {
		return d5;
	}

	public void setD5(String d5) {
		this.d5 = d5;
	}

	public String getD6() {
		return d6;
	}

	public void setD6(String d6) {
		this.d6 = d6;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
