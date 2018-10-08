package Code;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {
	
	private static Graph gr;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		System.out.println(args[0]);
		gr = new Graph(args[0]);
		gr.ReadXML();
		gr.BelongNode("4893468837");
		gr.positionNode("4893468837");
		
		ArrayList<Nodo> array = gr.getNodeList();
		
		System.out.println("#adjacentNode#\n");
		
		for (int i = 0; i<array.size(); i++) {
			Nodo n = array.get(i);
			gr.adjacentNode(n.getId());
		}
	
	}
}
