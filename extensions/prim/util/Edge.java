package prim.util;

/**
 * Edge object for the directed multigraph DirectedGraph
 * 
 * @author timhuber
 *
 */
public class Edge {
	
	public final Vertex v1, v2;
	
	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2   = v2;
	}
	
	public String toString() {
		return "Edge " + v1 + "<-->" + v2;
	}

}
