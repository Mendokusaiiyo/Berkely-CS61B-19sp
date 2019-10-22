package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private AStarGraph<Vertex> aGraph;
    private Vertex source;
    private Vertex goal;
    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private HashMap<Vertex, Vertex> bestParent = new HashMap<>();
    private ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
    private SolverOutcome result;
    private LinkedList<Vertex> solution = new LinkedList<>();
    private Double totalWeightOfSolution = 0.0;
    private int dequeueTime = 0;
    private double timeSpent;
    private HashSet<Vertex> marked = new HashSet<>();

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        source = start;
        goal = end;
        aGraph = input;
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        while (pq.size() != 0) {
            if (pq.getSmallest().equals(end)) {
                Vertex currVertex = pq.getSmallest();
                // add backwards, from the end to the start.
                solution.addFirst(currVertex);
                while (!currVertex.equals(start)) {
                    solution.addFirst(bestParent.get(currVertex));
                    currVertex = bestParent.get(currVertex);
                }
                totalWeightOfSolution = distTo.get(end);
                break;
            }

            marked.add(pq.getSmallest());
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(pq.removeSmallest());
            dequeueTime += 1;
            for (WeightedEdge e : neighborEdges) {
                if (marked.contains(e.to())) {
                    continue;
                }

                relax(e);
            }
        }
        timeSpent = sw.elapsedTime();
        if (timeSpent  > timeout) {
            result = SolverOutcome.TIMEOUT;
            return;
        }
        else if (pq.size() == 0) {
            result = SolverOutcome.UNSOLVABLE;
        } else {
            result = SolverOutcome.SOLVED;
        }

    }

    private void relax(WeightedEdge<Vertex> e) {
        if (!distTo.containsKey(e.to())) {
            distTo.put(e.to(), Double.POSITIVE_INFINITY);
        }
        double distToP = distTo.get(e.from());
        double distToQ = distTo.get(e.to());
        double weightOfE = e.weight();

        if (distToP + weightOfE < distToQ) {
            distTo.put(e.to(), distToP + weightOfE);

            // This will update the parent vertex until it is the edge is the best.
            bestParent.put(e.to(), e.from());
            if (pq.contains(e.to())) {
                pq.changePriority(e.to(), distTo.get(e.to()) + aGraph.estimatedDistanceToGoal(e.to(), goal));
            } else {
                pq.add(e.to(), distTo.get(e.to()) + aGraph.estimatedDistanceToGoal(e.to(), goal));
            }
        }

    }

    @Override
    public SolverOutcome outcome() {
        return result;
    }

    @Override
    public List<Vertex> solution() {
        if (!outcome().equals(SolverOutcome.SOLVED)) {
            return new LinkedList<>();
        }
        return solution;
    }

    @Override
    public double solutionWeight() {
        if (!outcome().equals(SolverOutcome.SOLVED)) {
            return 0;
        }
        return totalWeightOfSolution;

    }

    @Override
    public int numStatesExplored() {
        return dequeueTime;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
