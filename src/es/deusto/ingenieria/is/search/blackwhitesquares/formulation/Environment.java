package es.deusto.ingenieria.is.search.blackwhitesquares.formulation;

import java.util.ArrayList;

import es.deusto.ingenieria.is.search.formulation.State;

public class Environment extends State implements Cloneable{
	private ArrayList<String> tablero;
	private int posActual;

	//Constructor sin parámetros
	public Environment() {
		
	}
	
	//Constructor con parámetros
	public Environment(ArrayList<String> board, int posActual) {
		super();
		this.tablero = board;
		this.posActual = posActual;
	}

	public ArrayList<String> getTablero() {
		return tablero;
	}

	public void setTablero(ArrayList<String> board) {
		this.tablero = board;
	}
	
	public int getPosActual() {
		return posActual;
	}

	public void setPosActual(int posActual) {
		this.posActual = posActual;
	}

	//CORRECCIÓN: El método equals sólo tiene que comparar la información que cambia de un estado
	//a otro. Las casillas no cambian, por lo tanto no debieran estar en el equals().
	//LO HEMOS CORREGIDO.
	
	/*Método para comprobar que dos entornos son iguales. Recibe como parámetro un Object para y
	después comprueba que ese Object es del tipo Enviroment*/
	public boolean equals(Object obj) {
		if ( obj != null && obj instanceof Environment) {
			Environment envAuxiliar= (Environment)obj;
			if(this.posActual==envAuxiliar.getPosActual())
			{
				return true;
			}
			else
			{
				return false;
			}
		} 
		else 
		{
			return false;
		}
	}
	
	/*En este método recorremos nuestro tablero de Squares y vamos comparando el contenido del
	 * toString de la clase Square a ver si es de color blanco. Si es de color blanco
	 * (|java.awt.Color[r=255,g=255,b=255]) concatenamos |White en la variable de texto. Si no es
	 * blanco, concatenamos |Black en la variable texto. Si estamos en una casilla añadimos *.
	 * Finalmente retornamos la variable texto*/
	public String toString() {
		String texto = "";
		for(int i = 0; i<this.tablero.size() ; i++){
			if(this.tablero.get(i).equals("White")) {
				texto += "|White";
			} else if(this.tablero.get(i).equals("Black")) {
				texto += "|Black";
			} else if(this.tablero.get(i).equals("Nada")) {
				texto += "|Nada";
			} else if(posActual == i) {
				texto += "(*)";
			}
		}
		return texto += "|";
	}
	
	//Este el el método de clonado de la clase (la clase implementa Clonable).
	public Object clone() {
		Environment clon = null;
		
		try {
			clon = (Environment) super.clone();
			clon.posActual = this.posActual;
		} catch (CloneNotSupportedException e) {
			System.err.println("% [ERROR] Environment.clone(): " + e.getMessage());
		}		
		return clon;
	}
	
	public static void main (String [] args)
	{
		//Main de prueba en el que comprobamos los métodos isApplicable y effect.
		System.out.println("||| COMIENZO DEL MAIN DE PRUEBA |||");
		System.out.println("");
		System.out.println("Prueba del método toString():");
		BlWhSquaresProblem o = new BlWhSquaresProblem();
		Environment estadoXML =(Environment) o.gatherInitialPercepts();
		System.out.println(estadoXML.toString());
		System.out.println("");
		System.out.println("Vamos a comprobar que dos entornos son iguales UTILIZANDO EL METODO EQUALS");
		Environment env = new Environment();
		Object obj = new Object();
		if(env.equals(obj))
		{
			System.out.println("Los entornos son iguales");
		}
		else
		{
			System.out.println("Los entornos NO son iguales");
		}
		System.out.println("");
		System.out.println("Vamos a clonar el entorno inicial y comprobar que son iguales:");
		Environment env2 = (Environment) env.clone();
		if(env.getPosActual() == env2.getPosActual())
		{
			System.out.println("Los entornos son iguales");
		}
		else
		{
			System.out.println("Los entornos NO son iguales");
		}
		System.out.println("");
		System.out.println("||| FIN DEL MAIN DE PRUEBA |||");
	}
}