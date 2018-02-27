package spath.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Vertex object for the direct multigraph DirectedGraph
 * @author timhuber
 *
 */
public class Vertex {
	private static int count = 0;
	
	final private List<Edge> successors;
	final private List<Edge> predecessors;
	//
	// The id has no extrinsic use except to distinguish a Vertex
	//     from other vertices which would have different ids
	final private int id;
	
	public Vertex() {
		this.id = ++count;
		this.successors   = new ArrayList<Edge>();
		this.predecessors = new ArrayList<Edge>();
	}
	
	public void addEdgeFrom(Edge e) {
		if (this.equals(e.from)) {
			successors.add(e);
		}
		else throw new Error("Bad edge " + e);
	}

	public void addEdgeTo(Edge e) {
		if (this.equals(e.to)) {
			predecessors.add(e);
		}
		else throw new Error("Bad edge " + e);
	}
	
	
	public Iterable<Edge> edgesFrom() {
		return successors;
	}

	public Iterable<Edge> edgesTo() {
		return predecessors;
	}
	
	public String toString() {
		return "Vertex " + id;
	}

}
