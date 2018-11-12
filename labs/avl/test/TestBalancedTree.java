package avl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import avl.AVLTree;
import org.junit.Rule;
import org.junit.Test;

import avl.util.TreeToStrings;
import avl.validate.BSTValidator;

/**
 *
 * @author Fernando Rojo
 *
 */
public class TestBalancedTree {

    @Rule
	public FailReporter tvs = new FailReporter();
    
    private static BSTValidator<Integer> genTree() {
	AVLTree<Integer> tree = new AVLTree<Integer>();
	return new BSTValidator<Integer>(tree);
    }
    
    @Test
	public void testEmptyTree() {
	AVLTree<Integer> bst = genTree().tree;
	assertTrue("The tree after creation should be empty, but your size is > 0.", bst.size == 0);
    }
    
    @Test
	public void testInsertSmallAscending() {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	int num = 15;
	for (int i=0; i < num; ++i) {
	    verifySize("before Insert", tree, i);
	    bstv.check();
	    tree.insert(i);
	    bstv.check();
	    verifySize("after Insert", tree, i+1);
	}
	System.out.println(TreeToStrings.toTree(tree));
    }
    
    @Test
    public void testInsertLargeAscending() {
    BSTValidator<Integer> bstv = genTree();
    AVLTree<Integer> tree = bstv.tree;
    int num = 200000;
    for (int i=0; i < num; ++i) {
        tree.insert(i);
        }
    bstv.check();
    verifySize("after Insert", tree, num);
    }
    
    @Test
	public void testInsertSmallDescending() {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	int num = 15;
	for (int i= 0; i < num; ++i) {
	    verifySize("before Insert", tree, i);
	    bstv.check();
	    tree.insert(num - i - 1);
	    bstv.check();
	    verifySize("after Insert", tree, i+1);
	}
	System.out.println(TreeToStrings.toTree(tree));
    }
    
    @Test
    public void testInsertLargeDescending() {
    BSTValidator<Integer> bstv = genTree();
    AVLTree<Integer> tree = bstv.tree;
    int num = 200000;
    for (int i=0; i < num; ++i) {
        tree.insert(num - i - 1);
        }
    bstv.check();
    verifySize("after Insert", tree, num);
    }
    
    @Test
	public void testInsertSmallRandom() {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	int num = 15;
	
	ArrayList<Integer> ints = genUniqueInts(num);
	for (int i= 0; i < num; ++i) {
	    verifySize("before Insert", tree, i);
	    bstv.check();
	    tree.insert(ints.get(i));
	    bstv.check();
	    verifySize("after Insert", tree, i+1);
	}
	System.out.println(TreeToStrings.toTree(tree));
    }

    @Test
    public void testInsertLargeRandom() {
    BSTValidator<Integer> bstv = genTree();
    AVLTree<Integer> tree = bstv.tree;
    int num = 200000;
    
    ArrayList<Integer> ints = genUniqueInts(num);
    for (int i=0; i < num; ++i) {
        tree.insert(ints.get(i));
        }
    bstv.check();
    verifySize("after Insert", tree, num);
    }
    
    @Test
	public void testInsertDuplicates() {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	int num = 15;
	
	ArrayList<Integer> ints = genUniqueInts(num);
	for (int i= 0; i < num; ++i) {
	    verifySize("before Insert", tree, i);
	    bstv.check();
	    tree.insert(ints.get(i));
	    bstv.check();
	    verifySize("after Insert", tree, i+1);
	    tree.insert(ints.get(i));
	    bstv.check();
	    verifySize("after Insert", tree, i+1);
	}
	System.out.println(TreeToStrings.toTree(tree));
    }

    @Test
	public void testRemoveSmall() {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	int num = 15;
	
	for (int i=0; i < num; ++i) {
	    verifySize("before Insert", tree, i);
	    bstv.check();
	    tree.insert(i);
	    bstv.check();
	    verifySize("after Insert", tree, i+1);
	}
	
	for (int i=0; i < num/2; ++i) {
	    verifySize("before Remove", tree, num - i);
	    bstv.check();
	    tree.remove((i + 7) % num);
	    bstv.check();
	    verifySize("after Remove", tree, num - i - 1);
	}
	
	for (int i=0; i < num; ++i) {
	    bstv.check();
	    tree.insert(i);
	    bstv.check();
	}
	verifySize("after Remove", tree, num);
	
	System.out.println(TreeToStrings.toTree(tree));
    }
    
    @Test
	public void testRemoveLarge() {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	int num = 200000;
	
	for (int i=0; i < num; ++i) {
		tree.insert(i);
	}
	bstv.check();
	
	for (int i=0; i < num/2; ++i) {
	    tree.remove((i + 7) % num);
	}
	bstv.check();
	
	for (int i=0; i < num; ++i) {
	    tree.insert(i);
	}
	bstv.check();
	verifySize("after Remove", tree, num);
    }
    
    private void verifySize(String event, AVLTree<?> tree, int expectedSize) {
	assertEquals("Expect tree " + event + " to have size " + 
		     expectedSize + " but it did not", expectedSize, tree.size);
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

        System.out.println("Testing unique size 10000"); 
	sortTest(genUniqueInts(10000), false);
        System.out.println("done");
    }
    
    
    @Test
	public void testRebalanceSmall() {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	
	//test left-left
	bstv.check();
	tree.insert(15);
	bstv.check();
	tree.insert(10);
	bstv.check();
	tree.insert(5);
	bstv.check();
	
	//test left-right
	tree.insert(0);
	bstv.check();
	tree.insert(1);
	bstv.check();
	
	//test right-right
	tree.insert(20);
	bstv.check();
	tree.insert(25);
	bstv.check();
	
	//test right-left
	tree.insert(30);
	bstv.check();
	tree.insert(29);
	bstv.check();
	
    }
    
    
    private ArrayList<Integer> genUniqueInts(int num) {
	ArrayList<Integer> ans = new ArrayList<Integer>(num);
	for (int i=0; i < num; ++i) {
	    ans.add(i,i);
		}
	Collections.shuffle(ans);
	return ans;
    }
    
    private void sortTest(Iterable<Integer> iter, boolean show) {
	BSTValidator<Integer> bstv = genTree();
	AVLTree<Integer> tree = bstv.tree;
	int num = 0;
	assertEquals("Wrong tree size:", 0, tree.size);
	for (Integer n : iter) {
	    bstv.check();
	    tree.insert(n);
	    bstv.check();
	    ++num;
	}
	assertEquals("Wrong tree size:", num, tree.size);
	if (show) {
	    System.out.println(TreeToStrings.toTree(tree));
	}
	
	int[] copy = new int[num];
	
	for (Integer n : iter) {
	    copy[--num] = n;
	}
	Arrays.sort(copy);
    }
}
