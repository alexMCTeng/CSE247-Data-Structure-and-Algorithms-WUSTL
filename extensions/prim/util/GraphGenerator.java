package prim.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * Class to generate a graph with a predetermined weighted shortest path
 * to be used to test students' Dijkstra's Algorithm Implementations
 * 
 * @author Tim Heyer, Yonatan David
 *
 */
public class GraphGenerator {
	private final static Integer inf = Integer.MAX_VALUE;
	private final int numVertices;
	private final int numEdges;
	private final int MSTEdges;
	private final Random r = new Random();
	private int vertCount;
	private int edgeCount;
	
	private int MSTValue = 0;
	private int currentLargestEdge = 0;
	private final UndirectedGraph graph;
	private final Map<Edge, Integer> weights;
	private Map<Vertex, Edge> toEdge;
	private Vertex startVertex;

	


	/**
	 * 
	 * @param vertices the number of vertices in the graph to be generated
	 * @param edges the number of edges in the graph to be generated
	 * @param MSTEdges the number of edges in the minimum spanning tree to be generated
	 */
	public GraphGenerator (int vertices, int edges, int MSTEdges) {
		this.numVertices = vertices;
		this.numEdges = edges;	
		this.MSTEdges = MSTEdges;
		this.graph = new UndirectedGraph();
		this.weights = new HashMap<Edge, Integer>();
		this.toEdge = new HashMap<Vertex, Edge>();
	}
	
	/**
	 * Generate the graph using the parameters given in the constructor
	 * @return the generated graph
	 */
	public UndirectedGraph create() {
		this.startVertex = new Vertex();
		//generate shortest path recursively
		MSTHelper(startVertex);
		
		while(vertCount < numVertices) {
			addVertexWithTwoEdges();
		}
		
		while(edgeCount < numEdges) {
			addEdge();
		}
		
		return graph;
	}
	
	/**
	 * 
	 * @return the graph with predetermined shortest path
	 */
	public UndirectedGraph getGraph() {
		return graph;
	}
	
	/**
	 * 
	 * @return map of edges to their weights
	 */
	public Map<Edge, Integer> weights() {
		return weights;
	}
	
	/**
	 * 
	 * @return the first vertex in the predetermined shortest path
	 */
	public Vertex start() {
		return startVertex;
	}
	
	/**
	 * 
	 * @return the sum of the weights of the edges that make up the shortest path
	 */
	public int getMSTValue() {
		return MSTValue;
	}
	
	public Map<Vertex, Edge> getMST() {
		return toEdge;
	}
	
	/**
	 * 
	 * @return the number of vertices in the graph
	 */
	public int getNumVerts() {
		return vertCount;
	}
	
	/**
	 * 
	 * @return the number of edges in the graph
	 */
	public int getNumEdges() {
		return edgeCount;
	}
	
	/**
	 * Generate the shortest path with length shortestPathEdges that will remain 
	 * unchanged across any calls to addEdgeWithVertex() and addEdge(). The vertices in
	 * this shortest path will have id's 0,..., shortestPathEdges - 1
	 * 
	 * @return the vertex at the end of this shortest path
	 */
	public void genMST() {
		//create a starting vertex and set its distFromStart to zero
		this.startVertex = new Vertex();
		MSTHelper(startVertex);
	}

	/**
	 * recursive helper method that actually generates the shortest path
	 */
	private Vertex MSTHelper(Vertex source) {
		//
		//If we reach the end of the shortest path (there are shortestPathEdges + 1
		//vertices and shortestPathEdges edges) set the end vertex
		//
		if(vertCount > MSTEdges) {
			graph.addVertex(source);
			++vertCount;

			return source;
		}
		
		graph.addVertex(source);
		++vertCount;
		
		//
		//Recur until there are shortestPathEdges + 1 vertices, and then generate
		//new edges with random weights, building the shortest path from end to start
		//as calls are popped off the stack
		//
		Vertex target = MSTHelper(new Vertex());
		Edge e = new Edge(source, target);
		graph.addEdge(e);
		toEdge.put(target, e);

		//generate a random weight for the edge between 1 and 10
		int weight = (r.nextInt(5) + 1) + currentLargestEdge;
		currentLargestEdge = weight;
		MSTValue += weight;
		weights.put(e, weight);
		++edgeCount;

		return source;
	}

	/**
	 * Method that adds a vertex along with two edges connecting it to existing 
	 * vertices in the graph. This method assigns edge weights such that the
	 * predesignated shortest path in the graph is unchanged.
	 */
	public void addVertexWithTwoEdges() {
		Vertex newVert = new Vertex();
		Vertex source = getRandomVertex();
		Vertex target = getRandomVertex();
		
		int e1Weight = (r.nextInt(5) + 1) + currentLargestEdge;
		int e2Weight = (r.nextInt(5) + 1) + currentLargestEdge;
		if (e1Weight == e2Weight)
			e2Weight++;
		
		Edge e1 = new Edge(source, newVert);
		Edge e2 = new Edge(newVert, target);
		newVert.addEdge(e1);
		newVert.addEdge(e2);
		 
		graph.addVertex(newVert);
		++vertCount;
		
		graph.addEdge(e1);
		graph.addEdge(e2);
		edgeCount += 2;
		
		weights.put(e1, e1Weight);
		weights.put(e2, e2Weight);
		
		toEdge.put(newVert, (e1Weight < e2Weight)? e1 : e2);
		currentLargestEdge = Math.max(e1Weight, e2Weight);
		MSTValue += Math.min(e1Weight, e2Weight);
	}
	
	/**
	 * A method that adds an edge between any two vertices (including edges from a 
	 * vertex to itself) in the graph such that this new edge does not alter the 
	 * predetermined shortest path in the graph.
	 */
	public void addEdge() {
		Vertex source = getRandomVertex();
		Vertex target = getRandomVertex();
		
		int weight = (r.nextInt(5) + 1) + currentLargestEdge;
		
		Edge e = new Edge(source, target);
		source.addEdge(e);
		target.addEdge(e);
		
		graph.addEdge(e);
		++edgeCount;
		weights.put(e, weight);
		currentLargestEdge = weight;
	}
	
	/**
	 * 
	 * @return a random vertex from the graph that is being generated
	 */
	private Vertex getRandomVertex() {
		int n = graph.getNumVertices();
		int which = r.nextInt(n);
		Iterator<Vertex> iv = graph.vertices().iterator();
		for (int i=0; i < n; ++i) {
			Vertex v = iv.next();
			if (i == which) {
				return v;
			}
			
		}
		throw new Error("cannot get here");
	}
	
}
