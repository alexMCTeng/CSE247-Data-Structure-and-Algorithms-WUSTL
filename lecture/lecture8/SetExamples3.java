package lecture8;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetExamples3 {

	//
	// In class, show Set<Integer>, Set<Color>
	//
	public static void main(String[] args) {
		
		Map<String,List<Integer>> mymap = new HashMap<String,List<Integer>>();
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1); list.add(2); list.add(3);
		
		System.out.println("List is " + list);
		
		mymap.put("Pat", list);
		
		System.out.println("Map is " + mymap);
		
		mymap.get("Pat").add(6);
		System.out.println("Map is now " + mymap);

	}

}
