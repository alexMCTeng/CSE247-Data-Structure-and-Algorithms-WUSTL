package lecture1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class ExampleSlide20 extends QuietAlgorithm {

	private Ticker ticker;
	public  int    value;

	public ExampleSlide20() {
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.value = 0;
	}

	public void run() {
		int j = 0;
		while (j <= n/2) {
			for (int k=0; k < 3; ++k) {
				//
				// Here
				ticker.tick();
				//
			}
			j = j + 1;
		}
	}

	public String toString() {
		return "Example Slide 20 " + n;
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(0, 10, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"slide20", 
				"lecture1.ExampleSlide20", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
