package AVLTree.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import AVLTree.nodes.AVLTreeNode;
import AVLTree.nodes.TreeNode;
import AVLTree.trees.*;
import org.junit.Rule;
import org.junit.Test;

import AVLTree.util.TreeToStrings;
import AVLTree.validate.BSTValidator;
import timing.Ticker;

/**
 *
 * @author fernandorojo
 *
 */
public class TestUnbalancedTree {

	private Random r = new Random();

	@Rule
	public FailReporter tvs = new FailReporter();

	private static BSTValidator<Integer> genTree() {
		BST<Integer> tree = new UnbalancedBST<Integer>(new Ticker());
		return new BSTValidator<Integer>(tree);
	}

	@Test
	public void testEmptyHeap() {
	    BST<Integer> bst = genTree().tree;
	    assertTrue("The heap after creation should be empty, but your isEmpty() did not agree.", bst.isEmpty());
	}

	@Test
	public void testInsert() {
		BSTValidator<Integer> bstv = genTree();
		BST<Integer> tree = bstv.tree;
		int num = 50;
		for (int i=0; i < num; ++i) {
			verifySize("before Insert", tree, i);
			verifySizeCapacity(tree);
			bstv.check();
			tree.Insert(i);
			bstv.check();
			verifySize("after Insert", tree, i+1);
			assertTrue("Heap should not be empty now: check your isEmpty() method", !tree.isEmpty());
		}
		System.out.println(TreeToStrings.toTree(tree));
	}





	private void verifySize(String event, BST<?> tree, int expectedSize) {
		assertEquals("Expect queue " + event + " to have size " + expectedSize + " but it did not:  check your size() method", expectedSize, tree.size());

	}

	private void verifySizeCapacity(BST<?> tree) {
		assertTrue("A heap's capacity must be at least its size. You have "
				+ " capacity: " + tree.capacity() + " size " + tree.size() + ".  Check your size() and capacity() methods",
				tree.capacity() >= tree.size());
	}

	//
	//Test the Insert and extract min methods of the priority queue by generating 
	//an unsorted array of integers, Inserting that array into a priority queue, and
	//then comparing the output of extractMin() with the respective value in the
	//sorted starting array
	//
	@Test
	public void testViaSorting() {
		for (int i=0; i < 10; ++i) {
			System.out.println("Testing unique size " + i);
			sortTest(genUniqueInts(i), false);
			System.out.println("done");
		}
		for (int i=0; i < 10; ++i) {
			System.out.println("Testing with duplicates size " + i);
			sortTest(genDupInts(i), false);
			System.out.println("done");
		}
	}

	@Test
	public void testAndPrint() {
		System.out.println("Displaying a correct tree of 50 unique integers:");
		sortTest(genUniqueInts(50), true);
	}


	private Iterable<Integer> genUniqueInts(int num) {
		ArrayList<Integer> ans = new ArrayList<Integer>(num);
		for (int i=0; i < num; ++i) {
			ans.add(i,i);
		}
		Collections.shuffle(ans);
		return ans;
	}

	private Iterable<Integer> genDupInts(int num) {
		ArrayList<Integer> ans = new ArrayList<Integer>(num);
		for (int i=0; i < num; ++i) {
			ans.add(i, r.nextInt(num/2+1));
		}
		Collections.shuffle(ans);
		return ans;
	}

	private void sortTest(Iterable<Integer> iter, boolean show) {
		BSTValidator<Integer> bstv = genTree();
		BST<Integer> tree = bstv.tree;
		int num = 0;
		assertEquals("Wrong result returned by your size() method:", 0, tree.size());
		for (Integer n : iter) {
			bstv.check();
			tree.Insert(n);
			bstv.check();
			++num;
		}
		assertEquals("Wrong result returned by your size() method:", num, tree.size());
		if (show) {
			System.out.println(TreeToStrings.toTree(tree));
		}

		int[] copy = new int[num];
		int size = num;
		for (Integer n : iter) {
			copy[--num] = n;
		}
		Arrays.sort(copy);

	}
}
