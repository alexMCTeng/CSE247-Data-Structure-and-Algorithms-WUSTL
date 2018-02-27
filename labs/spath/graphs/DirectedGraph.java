package spath.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * directed multigraph object
 * @author timhuber
 *
 */
public class DirectedGraph {
	
	final private Set<Vertex> vertices;
	final private Set<Edge>   edges;
	
	public DirectedGraph() {
		this.vertices = new HashSet<Vertex>();
		this.edges    = new HashSet<Edge>();
	}
	
	public void addEdge(Edge e) {
		e.from.addEdgeFrom(e);
		e.to.addEdgeTo(e);
		edges.add(e);
	}
	
	public void addVertex(Vertex v) {
		this.vertices.add(v);
	}
	
	public Iterable<Vertex> vertices() {
		return vertices;
	}
	
	public Iterable<Edge> edges() {
		return edges;
	}
	
	public int getNumEdges() {
		return edges.size();
	}
	
	public int getNumVertices() {
		return vertices.size();
	}

	public String toString() {
		return "Vertices " + vertices + " Edges " + edges;
	}
	
}
