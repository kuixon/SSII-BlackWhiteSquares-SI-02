package es.deusto.ingenieria.is.search.blackwhitesquares.algorithms.heuristic;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.blackwhitesquares.formulation.Environment;

public class FuncionEvaluadora extends EvaluationFunction {

	@Override
	public double calculateG(Node arg0) {
		// TODO Auto-generated method stub
		int posAct = ((Environment) arg0.getState()).getPosActual();
		double coste = 0;
		if(posAct != 0)
		{
			for(Node n = arg0; n !=  null;){
				n = n.getParent();
				coste += 1;
			}
			return coste;
		}
		return 0;
	}

	@Override
	public double calculateH(Node arg0) {
		// TODO Auto-generated method stub
		Environment entornoNodo = (Environment) arg0.getState();
		int casillasPorRecorrer = 18 - (entornoNodo.getPosActual());
		if(casillasPorRecorrer%4 > 0)
		{
			return (casillasPorRecorrer/4) + 1;
		}
		else
		{
			return casillasPorRecorrer/4;
		}
	}
}
