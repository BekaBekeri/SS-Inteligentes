package Code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Graph {
	
	
	static ArrayList<Nodo> NodeList = new ArrayList<Nodo>();
	static ArrayList<Edge> EdgeList = new ArrayList<Edge>();
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		ReadXML("Anchuras.graphml");
	
		
		System.out.println("Edge list: " + EdgeList.size() + 
						   "Node list: " + NodeList.size());	
		
		System.out.println("El nodo (\"4331489709\") pertenece = " + BelongNode("4331489709"));
		positionNode("4331489709");
		adjacentNode("4331489709");
	}
	
	//info para ReadXML: http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	//Link para ver los archivos xml que tenemos que leer: https://drive.google.com/drive/folders/1nXPVVJ0E44osD807PZLPlV3-kSvEcpEj
	
	public static void ReadXML(String f) throws ParserConfigurationException, SAXException, IOException {
		File XMLFile = new File(f);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(XMLFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
     	//Nodos
		
		NodeList nList = doc.getElementsByTagName("node");
		Nodo node;
		
		String nodeID;
		String d4, d5, d6, d7, d8, d9, d10, d11;
		String source, target, length, name;
		
		System.out.println("--------------------------");
		
		for (int temp = 0; temp<nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				//Aqui para leer los nodos
				Element eElement = (Element) nNode;
				
				nodeID = eElement.getAttribute("id");
				d4 = eElement.getElementsByTagName("data").item(0).getTextContent();	//Y AXIS
				d5 = eElement.getElementsByTagName("data").item(1).getTextContent();	//X AXIS
				d6 = eElement.getElementsByTagName("data").item(2).getTextContent();	
			
				
				
				
				node = new Nodo(nodeID, d4, d5, d6);
				NodeList.add(node);
				
			}
		}
		
		//Edges
		
	    nList = doc.getElementsByTagName("edge");
		Edge edge;
	    
	    
		for (int temp = 0; temp<nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			
			source = eElement.getAttribute("source");
			target = eElement.getAttribute("target");
			length = eElement.getAttribute("length");
			name = eElement.getAttribute("name");
			
			edge = new Edge(source, target, length, name);
			EdgeList.add(edge);
		}
	}


	public static boolean BelongNode(String id){
		
		boolean belongs = false;
		
		for(int i = 0; i < NodeList.size(); i++){
			if(id.equals(NodeList.get(i).getId())){
				belongs = true;
			}
		}
		
		return belongs;
	}
	
	public static void positionNode (String id){
		
		for(int i = 0; i < NodeList.size(); i++){
			if(id.equals(NodeList.get(i).getId())){
				System.out.println("X axis = " + NodeList.get(i).getD5() + 
						   "\nY axis = " + NodeList.get(i).getD4());
			}
		}
	}

	public static void adjacentNode(String id){
		
		Nodo initial = null;
		ArrayList<Edge> adjacentEdges = new ArrayList<Edge>();
		
		for(int i = 0; i < NodeList.size(); i++){		
			if(id.equals(NodeList.get(i).getId())){
				initial = NodeList.get(i);
			}
		}
		
		
		for(int i = 0; i < EdgeList.size(); i++){
			if(EdgeList.get(i).getSource().equals(initial.getId())){
			adjacentEdges.add(EdgeList.get(i));
			}
		}
		
		String aux = "";
		
		for(int i = 0; i < adjacentEdges.size(); i++){
			
			aux += "[(" + adjacentEdges.get(i).getSource() + ", " + adjacentEdges.get(i).getTarget() + "). " + adjacentEdges.get(i).getLength() + ". " + adjacentEdges.get(i).getName() + "]\n";
			
		}
		
		System.out.println("The adjacent edges with the node " + initial.getId() + " are: \n" + aux);
		
	}
	
	
}
