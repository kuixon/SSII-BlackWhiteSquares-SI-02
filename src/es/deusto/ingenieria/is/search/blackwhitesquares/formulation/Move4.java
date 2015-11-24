package es.deusto.ingenieria.is.search.blackwhitesquares.formulation;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class Move4 extends Operator{

	//Constructor sin par�metros
	public Move4()
	{
		super("Mover 4", 1d);
	}

	/*Este m�todo nos dice si el movimiento que vamos a hacer es posible o no. Si es posible nos
	devuelve true si no lo es, false*/
	protected boolean isApplicable(State state) {
		Environment env = (Environment) state;
		if(env.getTablero().get(env.getPosActual()).equals("Black"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*Este m�todo nos cambiaria la posici�n actual por la del estado que le pasamos por par�metro.
	Simularia el efecto de mover una ficha*/
	protected State effect(State state) {
		Environment env = (Environment) state;
		Environment env2 =(Environment) env.clone();
		env2.setPosActual(env2.getPosActual()+4);
		return env2;
	}
	
	public static void main (String [] args)
	{
		//Main de prueba en el que comprobamos los m�todos isApplicable y effect.
		System.out.println("||| COMIENZO DEL MAIN DE PRUEBA |||");
		System.out.println("");
		BlWhSquaresProblem o = new BlWhSquaresProblem();
		Environment estadoXML =(Environment) o.gatherInitialPercepts();
		System.out.println("Vamos a comprobar si podemos realizar el movimiento desde la casilla en la que estamos:");
		System.out.println("");
		Move4 m4 = new Move4();
		if(m4.isApplicable(estadoXML))
		{
			System.out.println("Podemos realizar este movimiento en este tipo de casilla");
		}
		else
		{
			System.out.println("No podemos realizar este movimiento en este tipo de casilla");
		}
		System.out.println("");
		System.out.println("Vamos a poner a prueba el metodo effect:");
		System.out.println("Antes de aplicar el metodo effect:");
		System.out.println("Estamos en la posicion: "+estadoXML.getPosActual());
		System.out.println("");
		System.out.println("Despu�s de aplicar el m�todo effect:");
		estadoXML = (Environment) m4.effect(estadoXML);
		System.out.println("Al realizar el movimiento estamos en la posicion: "+estadoXML.getPosActual());
		System.out.println("");
		System.out.println("||| FIN DEL MAIN DE PRUEBA |||");
	}
}