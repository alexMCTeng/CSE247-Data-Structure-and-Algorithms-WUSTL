package lecture1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class QuadraticTickEverywhere extends QuietAlgorithm {

	private Ticker ticker;
	public  int    value;

	public QuadraticTickEverywhere() {
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.value = 0;
	}

	public void run() {
		ticker.tick();                      // i = 0
		for (int i=0; i < n; ++i) {
			ticker.tick();                  // i < n
			ticker.tick();                      // j = 0
			for (int j=0; j < n; ++j) {
				ticker.tick();                  // j < n
                                //
                                // original one below
                                //
				ticker.tick();                     // add two values
				this.value = this.value + i;
				ticker.tick();                  // ++j
			}
			ticker.tick();                  // ++i
		}	
	}

	public String toString() {
		return "Quadratic " + n;
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(10000, 100000, 10000);
		ExecuteAlgorithm.timeAlgorithm(
				"quadratic", 
				"lecture1.QuadraticTickEverywhere", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
