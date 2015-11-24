package es.deusto.ingenieria.is.search.blackwhitesquares.algorithms.heuristic;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.is.search.blackwhitesquares.formulation.Environment;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

public class HillClimbing extends HeuristicSearchMethod {

	public HillClimbing(EvaluationFunction function) {
		super(function);
		// TODO Auto-generated constructor stub
	}

	public Node search(Problem problem, State initialState) {
		Environment env = (Environment) initialState;
		Node firstNode = new Node(env);
		Node finalState = null;
		Node mejorSucesor = null;
		boolean solutionFound = false;

		while (!solutionFound) {
			firstNode.setH(this.getEvaluationFunction().calculateH(firstNode));
			firstNode.setG(this.getEvaluationFunction().calculateG(firstNode));
			if(problem.isFinalState(firstNode.getState())) {
				System.out.println("HillClimbing-search: si estamos en un estado final");
				solutionFound = true;
			} else {
				mejorSucesor = expand(firstNode, problem);
				mejorSucesor.setH(this.getEvaluationFunction().calculateH(mejorSucesor));
				mejorSucesor.setG(this.getEvaluationFunction().calculateG(mejorSucesor));
				if ((firstNode.compareTo(mejorSucesor) == -1 || firstNode.compareTo(mejorSucesor) == 0) &&
				(problem.isFinalState(mejorSucesor.getState()))){
					System.out.println("HillClimbing-search: si la comparativa es correcta");
					solutionFound = true;
				} else {
					firstNode = mejorSucesor;
					Environment e = (Environment) firstNode.getState();
					int pos = e.getPosActual();
					System.out.println("Estamos en la posicion: " + pos);
				}
			}
		}
		finalState = new Node(firstNode.getState());
		return finalState;
	}

	/**
	 * Expands the node's state thereby generating a list of successor nodes.
	 * Expansion takes place by invoking the problem's operators apply() method on the
	 * node's state.
	 * 
	 * @param node
	 *            node whose state is to be expanded.
	 * @param problem
	 *            problem to solve
	 * @param generatedStates
	 *            List<State> states generated along the search process.
	 * @param expandedStates
	 *            List<State> states expanded along the search process.
	 * @return List<Node> containing the successor nodes.
	 */
	protected Node expand(Node node, Problem problem) {
		Node successorNode = null;
		Node mejorSucesor = null;
		State currentState = node.getState();
		
		//If the current node and problem aren't null
		if (node != null && problem != null) {
			//Make the current state the state kept in the node.
			currentState = node.getState();
			if (currentState != null) {
				//process the list of problem operators
				Operator o = problem.getOperators().get(0);
				Environment env = (Environment) o.apply(node.getState());
				mejorSucesor = new Node(env);				
				mejorSucesor.setH(this.getEvaluationFunction().calculateH(mejorSucesor));
				mejorSucesor.setG(this.getEvaluationFunction().calculateG(mejorSucesor));
			
				for (int i = 1; i < problem.getOperators().size(); i++) {
					//Apply the operator to the current state
					o = problem.getOperators().get(i);
					if(o.apply(node.getState()) != null) {
						Environment env2 = (Environment) o.apply(node.getState());
						successorNode = new Node(env2);
						if (!problem.isFinalState(env2)&&!problem.isFullyObserved(env2)) {
							successorNode.setState(problem.gatherPercepts(successorNode.getState()));
						}
						if(successorNode.getState() != null) {
							successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
							successorNode.setG(this.getEvaluationFunction().calculateG(successorNode));
							if(successorNode.compareTo(mejorSucesor) == -1) {
								mejorSucesor = successorNode;
								mejorSucesor.setOperator(o.getName());
								mejorSucesor.setParent(node);
								mejorSucesor.setDepth(node.getDepth() + 1);
							}
						}
					}
				}
			}
		}		
		System.out.println("expand:" + mejorSucesor.toString());
		return mejorSucesor;
	}
}
