package es.deusto.ingenieria.is.search.blackwhitesquares.formulation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.SearchMethod;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;

public class BlWhSquaresProblem extends Problem{
	
	//Constructor sin parámetros
	public BlWhSquaresProblem(){
		super();
		this.createOperators();
	}
	
	//cargamos la situación inicial del tablero.
	public State gatherInitialPercepts() {
		StateXMLReader stateXMLReader = new EnvironmentXMLReader("data/blackwhitesquaresPartialpercepts1.xml"); 								
		return stateXMLReader.getState();
	}
	
	//Creamos los operadores
	public void createOperators()
	{
		System.out.println("Creando...");
		this.addOperator(new Move1());
		this.addOperator(new Move2());
		this.addOperator(new Move4());
		System.out.println("Los operadores se han creado correctamente.");
	}
	
	//Comprobamos si hemos sobrepasado las casillas (Squares) del tablero.
	public boolean isFinalState(State estate)
	{
		Environment env = (Environment) estate;
		return env.getPosActual() >= 18;
	}
	
	//Comprobamos si nuestro estado es totalmente observable o no.
	public boolean isFullyObserved(State estate) {
		Environment env = (Environment) estate;
		return !env.getTablero().get(env.getPosActual()).equals("Nada");
	}
	
	public State gatherPercepts(State estate) {
		Environment env = (Environment) estate;
		System.out.println("¿De que color es la casilla (White/Black)?");
		int pos = 0;
		java.io.BufferedReader brTeclado = 
				new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		try {
			String color = brTeclado.readLine();
			if (color.equals("White")) {
				pos = env.getPosActual(); 
				env.getTablero().set(pos, "White");
			} else if (color.equals("Black")){
				pos = env.getPosActual();
				env.getTablero().set(pos, "Black");
			}
		} catch (IOException e) {
			System.out.println("Error lectura teclado");
		}
		System.out.println("Entorno: " + env.toString());
		return env;
	}
	
	public void solve(SearchMethod searchMethod) {		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		System.out.println("\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");				
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		Date endDate = GregorianCalendar.getInstance().getTime();		
		System.out.println("* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");
		
		long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
		long seconds = miliseconds / 1000;
		miliseconds %= 1000;		
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		
		String time = "* Search lasts: ";
		time += (hours > 0) ? hours + " h " : " ";
		time += (minutes > 0) ? minutes + " m " : " ";
		time += (seconds > 0) ? seconds + "s " : " ";
		time += (miliseconds > 0) ? miliseconds + "ms " : " ";
		
		System.out.println(time);
		
		if (finalNode != null) {
			System.out.println("\n- Solution found!     ;)");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);			
			System.out.println("- Final state:" + finalNode.getState());
		} else {
			System.out.println("\n- Unable to find the solution!     :(");
		}
	}
	
	//CORRECCIÓN: En esta nueva versión del proyecto, la invocación al método solve() debiera estar
		//en la clase MainProgram.
	//YA LO HEMOS INCORPORADO EN LA CLASE MAINPROGRAM
	
	public static void main(String[] args){
		//Comprobamos que los métodos gatherInitialPercepts, createOperators y isFinalState funcionan.
		System.out.println("||| COMIENZO DEL MAIN DE PRUEBA |||");
		System.out.println("");
		BlWhSquaresProblem o = new BlWhSquaresProblem();
		Environment estadoXML =(Environment) o.gatherInitialPercepts();
		System.out.println("Estado inicial del tablero:");
		System.out.println(estadoXML.toString());
		System.out.println("");
		System.out.println("Creamos los operandos:");
		o.createOperators();
		System.out.println("");
		System.out.println("Ahora vamos a ver si estamos en un estado final del problema:");
		boolean estFinal = false;
		estFinal = o.isFinalState(estadoXML);
		if(estFinal)
		{
			System.out.println("Hemos llegado al final");
		}
		else
		{
			System.out.println("Todavia no hemos llegado al final");
		}
		System.out.println("");
		System.out.println("||| FIN DEL MAIN DE PRUEBA |||");
	}
}
