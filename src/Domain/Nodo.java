package Domain;

public class Nodo {
	
	private String id;
	private String yAxis;		// D4 = Y axis
	private String xAxis;		// D5 = X axis
	private String osmid;
	
	public Nodo(){
		
	}
	
	public Nodo(String id, String d4, String d5, String d6) {
		super();
		this.id = id;
		this.yAxis = d4;
		this.xAxis = d5;
		this.osmid = d6;
	}

	public String getYAxis() {
		return yAxis;
	}

	public void setYAxis(String d4) {
		this.yAxis = d4;
	}

	public String getXAxis() {
		return xAxis;
	}

	public void setXAxis(String d5) {
		this.xAxis = d5;
	}

	public String getOsmid() {
		return osmid;
	}

	public void setOsmid(String d6) {
		this.osmid = d6;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString(){
		String aux = "";
		aux = "ID: " + this.id + ", [" + this.xAxis + ", " + this.yAxis + "], " + this.osmid;
		return aux;
	}
}
