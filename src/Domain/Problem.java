package Domain;

public class Problem {

	private String graphlmfile;
	private StateString IntSt;
	
	public Problem(){
		
	}
	
	public Problem(String fileJson, StateString nodoInicial) {
		this.graphlmfile = fileJson;
		this.IntSt = nodoInicial;
	}
	public String getGraphlmfile() {
		return graphlmfile;
	}
	public void setGraphlmfile(String fileJson) {
		this.graphlmfile = fileJson;
	}
	public StateString getIntSt() {
		return IntSt;
	}
	public void setIntSt(StateString nodoInicial) {
		this.IntSt = nodoInicial;
	}
	
	public String toString(){
		return "Fichero: " + graphlmfile + "\n" + IntSt.toString();
	}
}
