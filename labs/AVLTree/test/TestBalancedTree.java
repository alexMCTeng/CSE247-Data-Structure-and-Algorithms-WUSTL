package AVLTree.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import AVLTree.trees.*;
import org.junit.Rule;
import org.junit.Test;

import AVLTree.util.TreeToStrings;
import AVLTree.validate.BSTValidator;
import timing.Ticker;

/**
 *
 * @author Fernando Rojo
 *
 */
public class TestBalancedTree {

	private Random r = new Random();

	@Rule
	public FailReporter tvs = new FailReporter();

	private static BSTValidator<Integer> genTree() {
		BST<Integer> tree = new AVLTree<Integer>(new Ticker());
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
		int num = 15;
		for (int i=0; i < num; ++i) {
			verifySize("before Insert", tree, i);
			bstv.check();
			tree.Insert(i);
			bstv.check();
			verifySize("after Insert", tree, i+1);
			assertTrue("Heap should not be empty now: check your isEmpty() method", !tree.isEmpty());
		}
		System.out.println(TreeToStrings.toTree(tree));
	}

	private void verifySize(String event, BST<?> tree, int expectedSize) {
		assertEquals("Expect tree " + event + " to have size " + expectedSize + " but it did not:  check your size() method", expectedSize, tree.size());
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
	public void testRebalance() {
		BSTValidator<Integer> bstv = genTree();
		BST<Integer> tree = bstv.tree;
		//test left-left
		bstv.check();
		tree.Insert(15);
		bstv.check();
		tree.Insert(10);
		bstv.check();
		tree.Insert(5);
		bstv.check();

		//test left-right
		tree.Insert(0);
		bstv.check();
		tree.Insert(1);
		bstv.check();

		//test right-right
		tree.Insert(20);
		bstv.check();
		tree.Insert(25);
		bstv.check();

		//test right-left
		tree.Insert(30);
		bstv.check();
		tree.Insert(29);
		bstv.check();
		verifySize("You did not insert the elements in. you were expected", tree, 9);
	}


	@Test
	public void testAndPrint() {
		System.out.println("Displaying a correct Tree of 50 unique integers:");
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
