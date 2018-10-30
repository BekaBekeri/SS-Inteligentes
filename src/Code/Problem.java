package Code;

public class Problem {

	private String graphlmfile;
	private Nodo IntSt;
	
	
	public Problem(String fileJson, Nodo nodoInicial) {
		this.graphlmfile = fileJson;
		this.IntSt = nodoInicial;
	}
	public String getGraphlmfile() {
		return graphlmfile;
	}
	public void setGraphlmfile(String fileJson) {
		this.graphlmfile = fileJson;
	}
	public Nodo getIntSt() {
		return IntSt;
	}
	public void setIntSt(Nodo nodoInicial) {
		this.IntSt = nodoInicial;
	}
	
	public String toString(){
		return "Fichero: " + graphlmfile + "\n" + IntSt.toString();
	}
}
