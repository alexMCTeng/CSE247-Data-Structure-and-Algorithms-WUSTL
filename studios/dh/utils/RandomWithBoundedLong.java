package dh.utils;

import java.util.Random;

public class RandomWithBoundedLong extends Random {

	public RandomWithBoundedLong(int seed) {
		super(seed);
	}

	public RandomWithBoundedLong() {
		super();
	}

	public long nextLong(long bound) {
		long ans = this.nextLong();
		if (ans < 0) ans = - ans;
		return ans % bound;

	}
}
