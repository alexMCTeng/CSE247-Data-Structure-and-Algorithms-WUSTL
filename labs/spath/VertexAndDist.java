package spath;

import spath.graphs.Vertex;

/**
 * Aggregate a Vertex and its distance from source.
 * Comparisons are based on distance.
 *
 * @author roncytron
 *
 */
public class VertexAndDist implements Comparable<VertexAndDist> {
	
	final private int distance;
	final private Vertex vertex;
	
	public VertexAndDist(Vertex v, int dist) {
		this.vertex = v;
		this.distance = dist;
	}

	/**
	 * Use this method when you want to reduce distance to generate
	 *     a VertexAndDist object with the same vertex as this one,
	 *     but with the reduced distance
	 * @param newdistance the reduced distance
	 * @return
	 */
	public VertexAndDist sameVertexNewDistance(int newdistance) {
		return new VertexAndDist(vertex, newdistance);
	}
	
	@Override
	public String toString() {
		return "(" + vertex + ", " + distance + ")";
	}
	
	public int getDistance() {
		int copy = this.distance;
		return copy;
	}
	
	public Vertex getVertex() {
		return this.vertex;
	}
	
	//
	// Comparison of this and any other VertexAndDist object
	//   is based only on distance from source.  The MinHeap uses
	//   this information as the heap metric of interest.
	//

	@Override
	public int compareTo(VertexAndDist o) {
		Integer dist     = o.distance;
		Integer thisDist = this.distance;
		return thisDist.compareTo(dist);
	}
	
}
