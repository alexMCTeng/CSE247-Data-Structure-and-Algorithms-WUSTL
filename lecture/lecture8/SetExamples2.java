package lecture8;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetExamples2 {

	//
	// In class, show Set<Integer>, Set<Color>
	//
	public static void main(String[] args) {
		
		//
		// Set is an interface -- names and argument types of all methods
		//   but no implementation
		//  HashSet is an implementation of a Set
		//
		Set<String> friends = new HashSet<String>();
		
		friends.add("Pat");
		friends.add("Chris");
		friends.add("Pa" + "t");
		friends.add("Zebra");
		friends.add("5");
		
		//  how long have I known someboy?
		Map<String,Integer> howLong = new HashMap<String,Integer>();
		
		howLong.put("Pat", 5);
		
		System.out.println("My friends are " + friends);
		System.out.println("How long? " + howLong);
		
		howLong.put("Pat", 6);
		System.out.println("How long now? " + howLong);
		
		System.out.println("Is Pat a friend?" + friends.contains("Patt"));


	}

}
