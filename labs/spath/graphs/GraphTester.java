package spath.graphs;

import java.util.Random;

public class GraphTester {

	public static void main(String[] args) {
		DirectedGraph g = new DirectedGraph();
		Vertex[] vertices = new Vertex[10];
		for (int i=0; i < vertices.length; ++i) {
			vertices[i] = new Vertex();
			g.addVertex(vertices[i]);
		}
		
		Random r = new Random();
		for (int i=0; i < 20; ++i) {
			Vertex from = vertices[r.nextInt(vertices.length)];
			Vertex to   = vertices[r.nextInt(vertices.length)];
			g.addEdge(new Edge(from, to));
			
		}
		
		System.out.println(g);
		
		System.out.println("\nEdges from each vertex");
		for (Vertex v : vertices) {
			System.out.println("From vertex " + v);
			for (Edge e : v.edgesFrom()) {
				System.out.println("   Edge " + e);
			}
		}
		
		System.out.println("\nEdges to each vertex");
		for (Vertex v : vertices) {
			System.out.println("To vertex " + v);
			for (Edge e : v.edgesTo()) {
				System.out.println("   Edge " + e);
			}
		}

	}

}
