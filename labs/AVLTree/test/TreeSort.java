package AVLTree.test;


import AVLTree.trees.AVLTree;
import timing.Algorithm;
import timing.Ticker;

public class TreeSort implements Algorithm<Integer[],Integer[]> {

	private Integer[] originalArray, sortedArray;
	private AVLTree<Integer> tree;
	private Ticker ticker;

	public TreeSort() {
		
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.tree = new AVLTree<Integer>(ticker);
		this.sortedArray = new Integer[originalArray.length];
	}

	@Override
	public void run() {
		for (Integer num : originalArray) {
			tree.Insert(num);
			ticker.tick();
		}
		sortedArray = tree.InorderTraversal(tree.Root()).toArray(sortedArray);
		ticker.tick(originalArray.length);
	}

	@Override
	public void loadInput(Integer[] input) {
		this.originalArray = input;
	}

	@Override
	public Integer[] getResults() {
		return this.sortedArray;
	}
	
	public String toString() {
		return originalArray == null ? "Treesort" :
			"Treesort of " + originalArray.length + " integers";
	}

}
