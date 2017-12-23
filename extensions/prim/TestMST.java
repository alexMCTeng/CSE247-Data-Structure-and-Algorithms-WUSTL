package prim;

import static org.junit.Assert.*;
import org.junit.Test;

import prim.util.*;

public class TestMST {
	private GraphGenerator gg;

	@Test
	public void test() {
		int[] shortestPathLengths = { 3,  4,  5,  6,  8, 10,  50, 100,  300};
		int[] totalVertices       = { 5,  8, 10, 12, 16, 20,  75, 200,  750};
		int[] totalEdges          = {10, 16, 20, 24, 32, 50, 300, 400, 1000};

		for(int i = 0; i < shortestPathLengths.length; i++) {
			gg = new GraphGenerator(totalVertices[i], totalEdges[i], shortestPathLengths[i]);
			gg.genMST();

			genGraphAndTest();

			while(gg.getNumVerts() < totalVertices[i]) {
				gg.addVertexWithTwoEdges();
				genGraphAndTest();
			}

			while(gg.getNumEdges() < totalEdges[i]) {
				gg.addEdge();
				genGraphAndTest();
			}
		}
	}

	private void genGraphAndTest() {
		UndirectedGraph g = gg.getGraph();	
		MinimumSpanningTree mst = new MinimumSpanningTree(g, gg.start(), gg.weights());
		mst.run();

		assertEquals("The MST path was incorrect.", gg.getMST(), 
				mst.getToEdge());
		
		assertEquals("The return value of the MST was incorrect.", gg.getMSTValue(), 
				mst.returnValue());
	}
}
