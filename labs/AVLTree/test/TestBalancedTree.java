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

//	@Test
//	public void testInsertRoot() {
//		BSTValidator<Integer> bstv = genTree();
//		BST<Integer> tree = bstv.tree;
//		int num = (int) (100*Math.random());
//		TreeNode<Integer> check;
//		{
//			verifySize("before Insert", tree, 0);
//			bstv.check();
//			check = tree.Insert(num);
//			bstv.check();
//			assertEquals("Element was not inserted as the root", check,tree.Root());
//			assertEquals("Find did not return the root element.", check,tree.Find(num));
//			verifySize("after Insert", tree, 1);
//			assertTrue("Heap should not be empty now: check your isEmpty() method", !tree.isEmpty());
//		}
//		System.out.println(TreeToStrings.toTree(tree));
//	}

	@Test
	public void testInsertWithoutRebalance() { //this test inserts the elements in an order that does not require rebalancing to take place
		BSTValidator<Integer> bstv = genTree();
		BST<Integer> tree = bstv.tree;
		int num = 15;
		TreeNode<Integer> check;
		int[] elements = {100,50,150,25,75,125,175,10,30,60,90,110,130,160,190,0,11,29,31,59,61,89,91,109,111,129,131,159,161,189,191};
		for (int i=0; i < num; ++i) {
			verifySize("before Insert", tree, i);
			bstv.check();
			check = tree.Insert(elements[i]);
			assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(elements[i]));
			bstv.check();
			verifySize("after Insert", tree, i+1);
			assertTrue("Heap should not be empty now: check your isEmpty() method", !tree.isEmpty());
		}
		System.out.println("Final Tree:");
		System.out.println(TreeToStrings.toTree(tree));
	}


	@Test
	public void testInsertWithRebalancing() {
		BSTValidator<Integer> bstv = genTree();
		BST<Integer> tree = bstv.tree;
		int num = 15;
		int esize = 0;
		TreeNode<Integer> check;
		for (int i=100; i < num+100; ++i) {
			verifySize("before Insert", tree, esize);
			bstv.check();
			check = tree.Insert(i);
			assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(i));
			bstv.check();
			verifySize("after Insert", tree, ++esize);
			assertTrue("Heap should not be empty now: check your isEmpty() method", !tree.isEmpty());
		}
		for (int i : genUniqueInts(100)) {
			verifySize("before Insert", tree, esize);
			bstv.check();
			check = tree.Insert(i);
			assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(i));
			bstv.check();
			verifySize("after Insert", tree, ++esize);
			assertTrue("Heap should not be empty now: check your isEmpty() method", !tree.isEmpty());
		}
		System.out.println(TreeToStrings.toTree(tree));
	}

	private void verifySize(String event, BST<?> tree, int expectedSize) {
		assertEquals( "Expect tree " + event + " to have size " + expectedSize + " but it did not:  check your size() method", expectedSize, tree.size());
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
	public void testAllRebalanceCases() {
		BSTValidator<Integer> bstv = genTree();
		BST<Integer> tree = bstv.tree;

		//test left-left
		TreeNode<Integer> check;
		bstv.check();
		check = tree.Insert(15);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(15));
		bstv.check();
		check = tree.Insert(10);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(10));
		bstv.check();
		check = tree.Insert(5);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(5));
		bstv.check();

		//test left-right
		check = tree.Insert(0);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(0));
		bstv.check();
		check = tree.Insert(1);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(1));
		bstv.check();

		//test right-right
		check = tree.Insert(20);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(20));
		bstv.check();
		check = tree.Insert(25);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(25));
		bstv.check();

		//test right-left
		check = tree.Insert(30);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(30));
		bstv.check();
		check = tree.Insert(29);
		assertEquals("Could not find element that was just inserted, check your insert and find method:", check,tree.Find(29));
		bstv.check();

		assertEquals("Wrong result returned by your size() method:", 9, tree.size());
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
		AVLTree<Integer> typedTree = (AVLTree) tree;
		Integer[] attempt = new Integer[num];
		attempt = typedTree.InorderTraversal(typedTree.Root()).toArray(attempt);

		assertEquals("Wrong ordering of elements or wrong elements in the tree:",Arrays.toString(copy),Arrays.toString(attempt));

	}
}
