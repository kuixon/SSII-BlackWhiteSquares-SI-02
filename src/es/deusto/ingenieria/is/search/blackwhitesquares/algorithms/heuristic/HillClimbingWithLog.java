package es.deusto.ingenieria.is.search.blackwhitesquares.algorithms.heuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.log.SearchLog;
import es.deusto.ingenieria.is.search.blackwhitesquares.formulation.Environment;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

public class HillClimbingWithLog extends HillClimbing {

	public HillClimbingWithLog(FuncionEvaluadora function) {
		super(function);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Carries out a search process from the initial state
	 * to the final state of the given problem.
	 * This method is defined according to the second version of the basic search algorithm
	 * which checks for repeated states (refer to the last algorithm studied in chapter 3).
	 * 
	 * @param problem
	 *            Problem to be solved by a search method.
	 * @param initialState
	 *            Problem's initial state. 
	 * @return Node
	 *         <ul>
	 *         <li>If a solution is found, Node contains the problem's final state</li>
	 *         <li>If the problem can't be solved, Node contains null.</li>
	 *         </ul>
	 */
	public Node search(Problem problem, State initialState) {
		//A list to keep the nodes generated during the search process.
		List<Node> frontier = new ArrayList<Node>();
		//List of states generated during the search process. This is used to check for repeated states.
		List<State> generatedStates = new ArrayList<State>();
		//List of states expended during the search process. This is used to check for repeated states.
		List<State> expandedStates = new ArrayList<State>();
		//First node in the list of generated nodes.
		//successor nodes list.
		Environment env = (Environment) initialState;
		Node firstNode = new Node(env);
		Node successorNode = null;
		List<Node> successorNodes = null;
		//Flag that signals whether a solution has been found.
		boolean solutionFound = false;
		
		//Defines and initializes the search log.
		SearchLog searchLog = this.createSearchLog();
		
		//Initialize the generated nodes list with a node containing the problem's initial state.
		frontier.add(firstNode);

		//Loop until the problem is solved or the generated nodes list is empty
		while (!solutionFound && !frontier.isEmpty()) {
			int cont = 0;
			System.out.println(cont);
			cont++;
			//write the content of the generated nodes list in the search log.
			this.writeInSeachLog(searchLog, frontier);
			//remove the first node from the generated nodes list.
			firstNode = frontier.remove(0);
			//If the first node contains a problem's final state
			if (problem.isFinalState(firstNode.getState())) {
				//change the flag to signal that the problem is solved
				solutionFound = true;
			//If the first node doesn't contain a problem's final state				
			} else {
				//Expand the first node.
				successorNodes = this.expand(firstNode, problem, generatedStates, expandedStates);
				successorNode = successorNodes.get(0);
				//If new successor nodes resulted from the expansion
				if (successorNodes != null && !successorNodes.isEmpty()) {
					if(firstNode.compareTo(successorNodes.get(0)) == 1) {
						//Add the successor nodes to the generated nodes list.
						frontier.addAll(successorNodes);
						//Sort the generated nodes list according to the evaluation function value
						//of the nodes. This comparison criteria is defined within the compareTo()
						//method of Node.
						Collections.sort(frontier);
					} else {
						firstNode = successorNode;
					}
				} else {
					solutionFound = true;
				}
			}
		}
		
		// closes the search log.
		this.closeSearchLog(searchLog);
		
		// If the problem is solved
		if (solutionFound) {
			//Return the first node as it contains the problem's final state
			return firstNode;
		//If the problem is not solved
		} else {
			return null;
		}
	}
}