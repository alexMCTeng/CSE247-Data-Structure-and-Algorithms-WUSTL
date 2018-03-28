package studio9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import com.csvreader.CsvWriter;

public class PointSet {

	/**
	 * Static method that returns the hashCode of a random Point
	 */
	public static int randomPointGen(){
		Random rand = new Random();
		int x = rand.nextInt();
		int y = rand.nextInt();
		
		Point p = new Point(x, y);
		
		return p.hashCode();
	}
	
	public static void main(String[] args) throws IOException {
		//Here we will create a HashSet of Points.
		HashSet<Point> pointSet = new HashSet<Point>();
		Point p1 = new Point (3, 4);
		Point p2 = new Point (3, 4);
		//add points to the set
		pointSet.add(p1);
		pointSet.add(p2);

		System.out.println("pointSet has " + pointSet.size() + " elements in it.");
		System.out.println(pointSet);
	}
}
