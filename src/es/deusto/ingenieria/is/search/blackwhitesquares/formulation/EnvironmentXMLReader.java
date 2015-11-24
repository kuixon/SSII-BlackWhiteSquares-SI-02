package es.deusto.ingenieria.is.search.blackwhitesquares.formulation;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;

public class EnvironmentXMLReader extends StateXMLReader{
	private ArrayList<String> arraySquareXML;
	private int size;
	//Constructor por parámetros de la clase.
	public EnvironmentXMLReader(String xml){
		super(xml);
	}
@Override
	public State getState() {
		for (int i = arraySquareXML.size(); i < size; i++) {
			arraySquareXML.add(i, "Nada");
		}
		return new Environment(this.arraySquareXML, 0);
	}
	
	//Leemos el XML y metemos la información en el array.
	public void startElement(String uri, String localName, String qName,
		Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		try {
			if(qName.equals("is:lineofsquares")){
				size = Integer.parseInt(attributes.getValue("length"));
				arraySquareXML = new ArrayList<String>();
			} else if (qName.equals("is:white")) {
				arraySquareXML.add(new String("White"));
			} else if (qName.equals("is:black"))
				arraySquareXML.add(new String("Black"));
							
		} catch (Exception ex) {
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
	}
	
	public static void main (String [] args)
	{
		//Main de prueba en el que comprobamos los métodos isApplicable y effect.
		System.out.println("||| COMIENZO DEL MAIN DE PRUEBA |||");
		System.out.println("");
		BlWhSquaresProblem o = new BlWhSquaresProblem();
		Environment estadoXML =(Environment) o.gatherInitialPercepts();
		System.out.println("Estado inicial del tablero:");
		System.out.println(estadoXML.toString());
		System.out.println("");
		System.out.println("||| FIN DEL MAIN DE PRUEBA |||");
	}
}
