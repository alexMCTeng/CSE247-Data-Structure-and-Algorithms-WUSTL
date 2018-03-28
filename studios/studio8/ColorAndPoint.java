package studio8;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import com.csvreader.CsvWriter;

public class ColorAndPoint {

	/**
	 * Static method that returns the hashCode of a random Color
	 */
	public static int randomColorGen(){		
		Random rand = new Random ();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		Color randomColor = new Color(r, g, b);

		return randomColor.hashCode();

	}
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
		//Here we will create a HashSet of Colors		
		HashSet <Color> colorSet = new HashSet<Color>();
		Color black1 = new Color (0, 0, 0);
		Color yellow = new Color (255, 255, 0);
		Color black2 = Color.BLACK;
		//add colors to the set
		colorSet.add(black1);
		colorSet.add(yellow);
		colorSet.add(black2);
		
		System.out.println("colorSet has " + colorSet.size() + " elements in it:");
		System.out.println(colorSet);
		
		System.out.println();
		
		//Here we will create a HashSet of Points.
		HashSet<Point> pointSet = new HashSet<Point>();
		Point p1 = new Point (3, 4);
		Point p2 = new Point (3, 4);
		//add points to the set
		pointSet.add(p1);
		pointSet.add(p2);

		System.out.println("pointSet has " + pointSet.size() + " elements in it.");
		System.out.println(pointSet);
		
		
		File all = new File("outputs/studio8p1.csv");

		FileWriter fw = new FileWriter(all, false);
		CsvWriter w = new CsvWriter(fw, ',');
		w.write("Color Values");
		w.write("Point Values");
		w.endRecord();

		for(int i = 0; i < 1000; ++i){
			int cval = randomColorGen();
			int pval = randomPointGen();
			w.write(""+ cval);
			w.write(""+ pval);
			w.endRecord();
		}
		
		w.close();


	}

}
