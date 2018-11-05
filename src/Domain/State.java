package Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.xml.bind.DatatypeConverter;

public class State {

	private Nodo nodo;
	private PriorityQueue<Nodo> nodeList;					//ordered list
	private String md5;
	
	public State(Nodo nodo, PriorityQueue<Nodo> nodeList) throws NoSuchAlgorithmException{
		this.nodo = nodo;
		this.nodeList = nodeList;
		this.md5 = md5();
	}
	
	
	public String md5() throws NoSuchAlgorithmException{
		
		PriorityQueue<Nodo> myNodeList = nodeList;
		String aux="";
		
		aux += nodo.toString();
		for (byte i=0; i<myNodeList.size(); i++) {
			aux += myNodeList.poll().toString();
		}
		
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(aux.getBytes());
		byte[] digest = md.digest();
		aux = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return aux;
	}


	public Nodo getNodo() {
		return nodo;
	}


	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}


	public PriorityQueue<Nodo> getNodeList() {
		return nodeList;
	}


	public void setNodeList(PriorityQueue<Nodo> nodeList) {
		this.nodeList = nodeList;
	}
	
	public String getMd5(){
		return md5;
	}
	
	public String toString(){
		PriorityQueue<Nodo> nodeListAux = this.nodeList;
		String aux = nodo.toString();
		for(int i = 0; i < nodeListAux.size(); i++){
			aux += nodeListAux.poll().toString();
		}
		aux+= "\nMD5CheckSum = " + this.md5;
		
		return aux;
	}
	
}
