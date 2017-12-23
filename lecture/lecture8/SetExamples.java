package lecture8;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetExamples {

	//
	// In class, show Set<Integer>, Set<Color>
	//
	public static void main(String[] args) {
		Set<Color> rainbow = new HashSet<Color>();
		rainbow.add(Color.RED);
		rainbow.add(new Color(0,0,0));
		rainbow.add(Color.BLACK);
		rainbow.add(new Color(255,0,1));
		System.out.println(rainbow);
		
		Set<Integer> primes = new HashSet<Integer>();
		primes.add(2);
		primes.add(new Integer(5));
		System.out.println(primes);
		Map<Integer,Boolean> divby2 = new HashMap<Integer,Boolean>();
		divby2.put(2, true);
		divby2.put(3, false);
		divby2.put(5, false);
		divby2.put(8, true);
		System.out.println(divby2);

	}

}
