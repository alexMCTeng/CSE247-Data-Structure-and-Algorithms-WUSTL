package dh.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dh.utils.Caesared;

public class DemoCaesar {

	public static void main(String[] args) {
		//
		// choose a random rotation amount
		//
		int rot = new Random().nextInt(26);
		System.out.println("Rotation value: " + rot);
		//
		// Simple input string
		//
		String s = "This is a string with at least 10 characters in it but not 247.";
		List<Caesared> list = new LinkedList<Caesared>();
		System.out.println("Original:  " + s);
		// Add each rotated character to a list

		String r = "";
		for (char c : s.toCharArray()) {
			Caesared cs = Caesared.gen(c,rot);
			r = r + cs;
			list.add(cs);
		}
		System.out.println("Rotated:   " + r);

		//
		// Now unrotate by the same amount and show the result
		//
		String ur = "";
		for (Caesared cs : list) {
			ur = ur + cs.unRotate(rot);
		}
		System.out.println("Unrotated: " + ur);
	}

}
