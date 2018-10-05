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
	
	
	ArrayList<Node> NodeList = new ArrayList<Node>();
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		ReadXML("Anchuras.graphml");
	
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
		Node node;
		
		String d4, d5, d6, d7, d8, d9, d10, d11;
		String source, target;
		
		System.out.println("--------------------------");
		
		for (int temp = 0; temp<nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element : " + nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				//Aqui para leer los nodos
				Element eElement = (Element) nNode;
				
				d4 = eElement.getElementsByTagName("data").item(0).getTextContent();
				d5 = eElement.getElementsByTagName("data").item(1).getTextContent();
				d6 = eElement.getElementsByTagName("data").item(2).getTextContent();
				
				System.out.println("d4: " + d4);
				System.out.println("d5: " + d5);
				System.out.println("d6: " + d6);
				
				node = new Node(d4, d5, d6);
			
				
			}
		}
		
		//Edges
		
	    nList = doc.getElementsByTagName("edge");
		
		for (int temp = 0; temp<nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			System.out.println("\nCurrent Element : " + nNode.getNodeName());
			
			source = eElement.getAttribute("source");
			target = eElement.getAttribute("target");
			
			System.out.println("Source: " + source + " - Target: " + target);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				//A	qui para leer data	
			}
		}
	}
}
