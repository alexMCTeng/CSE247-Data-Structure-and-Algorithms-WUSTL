package kwaymergesort.test;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import kwaymergesort.KWayMergeSort;
import timing.Ticker;

/**
 * 
 * @author roncytron and timhuber
 *
 */
public class TestMergeSort {

	private Random r = new Random();
	
	@Test
	public void sortTest() {
		for (int i=0; i < 20; ++i) {
			sortOnce();
		}
	}

	
	private void sortOnce() {
		int p = r.nextInt(4); 
		int K = twoToThe(p+2);
		int n = twoToThe((p+2)*(r.nextInt(3)+1));
		System.out.println(""+K+"-way merge sort of " + n + " integers");
		Ticker t = new Ticker();
		Integer[] input = genInts(n);
		Integer[] gold  = Arrays.copyOf(input, input.length);
		Arrays.sort(gold);
		Integer[] ans = KWayMergeSort.kwaymergesort(K, input, t);
		for (int i=0; i < n; ++i) {
			if (!ans[i].equals(gold[i])) {
				fail("At index " + i + " expected " + gold[i] + " but got " + ans[i]);
			}
		}
	}
	
	private static int twoToThe(int i) {
		if (i==0) return 1;
		else return 2 * twoToThe(i-1);
	}
	
	private  Integer[] genInts(int n) {
		Integer[] ans = new Integer[n];
		for (int i=0; i < n; ++i) {
			ans[i] = r.nextInt();
		}
		return ans;
	}
}
