package prim.util;

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
	
	final private List<Edge> connections;
	//
	// The id has no extrinsic use except to distinguish a Vertex
	//     from other vertices which would have different ids
	final private int id;
	
	public Vertex() {
		this.id = ++count;
		this.connections   = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge e) {
		if (this.equals(e.v1) || this.equals(e.v2)) {
			connections.add(e);
		}
		else throw new Error("Bad edge " + e);
	}
	
	public Iterable<Edge> edges() {
		return connections;
	}
	
	public String toString() {
		return "Vertex " + id;
	}

}
