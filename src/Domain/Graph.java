package Domain;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Graph {
	
	private String XMLFile;
	private Hashtable<String, Nodo> NodeList = new Hashtable<String, Nodo>();
	private ArrayList<Edge> EdgeList = new ArrayList<Edge>();
	private String NAME_KEY = "";
	private String LEN_KEY = "";
	
	
	
	public Graph(String XMLFile) throws ParserConfigurationException, SAXException, IOException {
		this.XMLFile = XMLFile;
		ReadXML();
	}
	
	public ArrayList<Edge> getEdgeList(){
		return EdgeList;
	}
	
	public Hashtable<String, Nodo> getNodeList(){
		return NodeList;
	}
	
	//info para ReadXML: http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	//Link para ver los archivos xml que tenemos que leer: https://drive.google.com/drive/folders/1nXPVVJ0E44osD807PZLPlV3-kSvEcpEj
	public void ReadXML() throws ParserConfigurationException, SAXException, IOException {
		File f = new File(XMLFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(f);
		
		doc.getDocumentElement().normalize();
		
     	//Nodos	
		NodeList nList = doc.getElementsByTagName("node");
		Nodo node;
		String nodeID;
		String d4, d5, d6;
		String source, target, length = "", name = "";
		
		for (int temp = 0; temp<nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;	
				nodeID = eElement.getAttribute("id");
				d4 = eElement.getElementsByTagName("data").item(0).getTextContent();	//Y AXIS
				d5 = eElement.getElementsByTagName("data").item(1).getTextContent();	//X AXIS
				d6 = eElement.getElementsByTagName("data").item(2).getTextContent();	
				node = new Nodo(nodeID, d4, d5, d6);
				NodeList.put(nodeID, node);		
			}
		}
		
		//Edges
	    nList = doc.getElementsByTagName("edge");
	    NodeList dList;
		Edge edge;	
		NodeList kList;
		kList = doc.getElementsByTagName("key");
		
		for (int k = 0; k<kList.getLength(); k++) {
			Node kNode = kList.item(k);
			Element kElement = (Element) kNode;
		
			if(kElement.getAttribute("attr.name").equals("name") && kElement.getAttribute("for").equals("edge")) 
				NAME_KEY = kElement.getAttribute("id");
			else if(kElement.getAttribute("attr.name").equals("length") && kElement.getAttribute("for").equals("edge")) 
				LEN_KEY = kElement.getAttribute("id");
			
		}
	    
		//System.out.println(NAME_KEY);
		//+System.out.println(LEN_KEY);
	    
		for (int temp = 0; temp<nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			
			source = eElement.getAttribute("source");
			target = eElement.getAttribute("target");

			dList = eElement.getElementsByTagName("data");
			
			for (int aux = 0; aux<dList.getLength(); aux++) {
				Node dNode = dList.item(aux);
				Element dElement = (Element) dNode;
				if(dElement.getAttribute("key").equals(NAME_KEY)) name = dElement.getTextContent();
				if(dElement.getAttribute("key").equals(LEN_KEY)) length = dElement.getTextContent();
			}
			edge = new Edge(source, target, length, name);
			EdgeList.add(edge);
		}
	}

	public boolean BelongNode(String id){
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < NodeList.size(); i++){
			if(id.equals(NodeList.get(i).getId())){
				System.out.println("#BelongNode#\n\nThe node " + id + " belongs to the graph\n");
				long stopTime = System.currentTimeMillis();
				long elapsedTime = stopTime - startTime;
				System.out.println("elapsed: " + elapsedTime + "\n");
				return true;
			}
		}	
		return false;
	}
	
	public void positionNode (String id){
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < NodeList.size(); i++){
			if(id.equals(NodeList.get(i).getId())){
				System.out.println("#positionNode#\n\nPosition of node " + id +"\nX axis = " + NodeList.get(i).getXAxis() + 
						   "\nY axis = " + NodeList.get(i).getYAxis() + "\n");
				long stopTime = System.currentTimeMillis();
				long elapsedTime = stopTime - startTime;
				System.out.println("elapsed: " + elapsedTime + "\n");
			}
		}
	}

	//Modificar este metodo para que devuelva una lista de nodos (TreeNode) adyacentes al TreeNode que se pase como argumento. 
	//Para ello primero hace falta obteener los edges que tiene el nodo 
	public ArrayList<TreeNode> adjacentNode(TreeNode id, String strategy) throws NoSuchAlgorithmException{
		
		ArrayList<String> strAdjacent = new ArrayList<String>();
		ArrayList<TreeNode> adjacentList = new ArrayList<TreeNode>();
		ArrayList<Nodo> adjacentNList = new ArrayList<Nodo>();
			
		for(int i = 0; i < EdgeList.size(); i++){
			if(EdgeList.get(i).getSource().equals(id.getCurrentState().getNodo().getId())){
				strAdjacent.add(EdgeList.get(i).getTarget());
			}
		}
			
		for(int i = 0; i < strAdjacent.size(); i++){
			Iterator it = NodeList.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry pair = (Map.Entry)it.next();
				if(strAdjacent.get(i).equals(pair.getKey())){
					adjacentNList.add(NodeList.get(strAdjacent.get(i)));
				}
			}
		}
		
		TreeNode tnHijo = new TreeNode();
		State fooState = new State();
		//System.out.println("adyacentes: "+ adjacentNList.toString());
		for(int i = 0; i < adjacentNList.size(); i++){
			
			fooState = new State(adjacentNList.get(i), id.getCurrentState().getNodeList());
			tnHijo =  new TreeNode(id, fooState, id.getDepth() + 1, strategy, tnHijo.setDistance(id.getCurrentState().getNodo(), adjacentNList.get(i)));
			if(id.getParent() != null){
				//System.out.println(tnHijo.getCurrentState().getNodo().getId() + " " + id.getCurrentState().getNodo().getId());
				if(!tnHijo.getCurrentState().getNodo().getId().equalsIgnoreCase(id.getCurrentState().getNodo().getId())){
					adjacentList.add(tnHijo);
				}
			}else{
				adjacentList.add(tnHijo);
			}
			
		}
		
		return adjacentList;
	}
	
	
}
