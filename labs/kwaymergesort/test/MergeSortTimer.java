package kwaymergesort.test;

import timing.ExecuteAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class MergeSortTimer {


	public MergeSortTimer() {
	}



	public static void main(String[] args) {
		runExperiment(30000);   // FIXME change to 25000 or some large number so times show up
	}

	private static void runExperiment(int factor) {
		GenSizes sizes = GenSizes.geometric(2, 1000000, 2);
		ExecuteAlgorithm.timeAlgorithm(
				"mergesort", 
				"kwaymergesort.MergeSort", 
				new IntArrayGenerator(), 
				sizes
				);
	}

}
