package studio10;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import timing.Algorithm;
import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;
import timing.utils.IntSequenceArrayGenerator;

public class SortedSetUsingUnorderedList implements Algorithm<Integer[], Integer[]> {

	private Integer[] originalArray;
	private Ticker ticker;
	private List<Integer> list;
	

	public SortedSetUsingUnorderedList() {

	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.list = new LinkedList<Integer>();
	}

	@Override
	public void loadInput(Integer[] input) {
		this.originalArray = input;
	}

	@Override
	public Integer[] getResults() {
		Integer[] ans = new Integer[originalArray.length];
		
		return ans;
	}

	@Override
	public void run() {
		for (Integer i : originalArray) {
			if (!list.contains(i)) {
				list.add(i);
			}
		}
	}

	public String toString() {
		return "Sorted set using list of " + originalArray.length + " numbers";
	}
	
	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(100, 10000, 100);
		ExecuteAlgorithm.timeAlgorithm(
				"sortusingunorderedlist", 
				"studio10.SortedSetUsingUnorderedList", 
				new IntArrayGenerator(), 
/*				new IntSequenceArrayGenerator(),   */
				sizes
				);
	}

}
