package Domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.xml.bind.DatatypeConverter;

public class State {

	private Nodo node;
	private PriorityQueue<Nodo> listNodes;					//ordered list
	private String id;
	
	
	public State(){
		
	}
	
	public State(Nodo nodo, PriorityQueue<Nodo> nodeList) throws NoSuchAlgorithmException{
		this.node = nodo;
		this.listNodes = nodeList;
		this.id = md5();
	}
	
	
	public String md5() throws NoSuchAlgorithmException{
		
		PriorityQueue<Nodo> myNodeList = listNodes;
		String aux="";
		
		aux += node.toString();
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
		return node;
	}


	public void setNodo(Nodo nodo) {
		this.node = nodo;
	}


	public PriorityQueue<Nodo> getNodeList() {
		return listNodes;
	}


	public void setNodeList(PriorityQueue<Nodo> nodeList) {
		this.listNodes = nodeList;
	}
	
	public String getMd5(){
		return id;
	}
	
	public String toString(){
		PriorityQueue<Nodo> nodeListAux = this.listNodes;
		String aux = node.toString();
		for(int i = 0; i < nodeListAux.size(); i++){
			aux += nodeListAux.poll().toString();
		}
		aux+= "\nMD5CheckSum = " + this.id;
		
		return aux;
	}
	
}
