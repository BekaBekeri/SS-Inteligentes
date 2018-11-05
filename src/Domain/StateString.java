package Domain;

import java.security.NoSuchAlgorithmException;
import java.util.PriorityQueue;


//ESTA CLASE ES UNA REPRESENTACION EN STRING DE LA CLASE STATE PARA INTENTAR SOLUCIONAR PROBLEMAS DE LECTURA DE JSON


public class StateString {
	
	private String nodo;
	private PriorityQueue<String> nodeList;					//ordered list
	private String md5;
	
	public StateString(String nodo, PriorityQueue<String> nodeList, String md5) throws NoSuchAlgorithmException{
		this.nodo = nodo;
		this.nodeList = nodeList;
		this.md5 = md5;
	}

	public String getNodo() {
		return nodo;
	}

	public void setNodo(String nodo) {
		this.nodo = nodo;
	}

	public PriorityQueue<String> getNodeList() {
		return nodeList;
	}

	public void setNodeList(PriorityQueue<String> nodeList) {
		this.nodeList = nodeList;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Override
	public String toString() {
		return "StateString [nodo=" + nodo + ", nodeList=" + nodeList + ", md5=" + md5 + "]";
	}
	
	
}
