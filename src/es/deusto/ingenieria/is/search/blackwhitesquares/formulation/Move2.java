package es.deusto.ingenieria.is.search.blackwhitesquares.formulation;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class Move2 extends Operator{

	//Constructor sin parámetros
	public Move2()
	{
		super("Mover 2", 1d);
	}

	/*Este método nos dice si el movimiento que vamos a hacer es posible o no. Si es posible nos
	devuelve true si no lo es, false*/
	protected boolean isApplicable(State state) {
		Environment env = (Environment) state;
		if(env.getTablero().get(env.getPosActual()).equals("White"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*Este método nos cambiaria la posición actual por la del estado que le pasamos por parámetro.
	Simularia el efecto de mover una ficha*/
	protected State effect(State state) {
		Environment env = (Environment) state;
		Environment env2 =(Environment) env.clone();
		env2.setPosActual(env2.getPosActual()+2);
		return env2;
	}
	
	public static void main (String [] args)
	{
		//Main de prueba en el que comprobamos los métodos isApplicable y effect.
		System.out.println("||| COMIENZO DEL MAIN DE PRUEBA |||");
		System.out.println("");
		BlWhSquaresProblem o = new BlWhSquaresProblem();
		Environment estadoXML =(Environment) o.gatherInitialPercepts();
		System.out.println("Vamos a comprobar si podemos realizar el movimiento desde la casilla en la que estamos:");
		System.out.println("");
		Move2 m2 = new Move2();
		if(m2.isApplicable(estadoXML))
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
		System.out.println("Después de aplicar el método effect:");
		estadoXML = (Environment) m2.effect(estadoXML);
		System.out.println("Al realizar el movimiento estamos en la posicion: "+estadoXML.getPosActual());
		System.out.println("");
		System.out.println("||| FIN DEL MAIN DE PRUEBA |||");
	}
}
