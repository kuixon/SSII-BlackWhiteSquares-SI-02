package es.deusto.ingenieria.is.search.blackwhitesquares;


import es.deusto.ingenieria.is.search.blackwhitesquares.algorithms.heuristic.FuncionEvaluadora;
import es.deusto.ingenieria.is.search.blackwhitesquares.algorithms.heuristic.HillClimbing;
import es.deusto.ingenieria.is.search.blackwhitesquares.formulation.BlWhSquaresProblem;

public class MainProgram {
	public static void main (String [] args)
	{
		try {
			BlWhSquaresProblem problem = new BlWhSquaresProblem();			
//			problem.addInitialState(problem.gatherInitialPercepts());
//			
//			System.out.println("");
//			problem.solve(BreadthFSwithLog.getInstance());
//			System.out.println("");
//			problem.solve(DepthFSwithLog.getInstance());
//			System.out.println("");
//			problem.solve(new BestFSwithLog(new FuncionEvaluadora()));
//			System.out.println("");
//			problem.solve(new AasteriscoWithLog(new FuncionEvaluadora()));
			problem.addInitialState(problem.gatherInitialPercepts());
			problem.solve(new HillClimbing(new FuncionEvaluadora()));
		} catch (Exception ex) {
			System.err.println("% [Main Program] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
