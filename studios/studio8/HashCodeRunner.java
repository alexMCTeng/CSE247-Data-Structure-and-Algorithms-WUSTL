package studio8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.csvreader.CsvWriter;

public class HashCodeRunner{

	public static String brandHelper(String string, int n){
		String [] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		string = abc[(int)(Math.random() * 25)];
		return string;
	}

	public static void main(String[] args) throws IOException {

		int [] pancakeVal = new int [1000];
		int [] syrupVal = new int [1000];
		int [] pancakeAndSyrup = new int [1000];

		for (int i = 0; i < pancakeVal.length; ++i){
			Random r = new Random();
			Random d = new Random();
			int radius = (int)(r.nextDouble() * 6) + 1;
			int density = (int)(d.nextDouble() * 3) + 1;
			int n = (int) Math.random();
			
			boolean wheat = (Math.random() < .5);
			String string = "";
			Pancake p = new Pancake(radius, wheat);
			Syrup s = new Syrup(brandHelper(string, n), density);
			PancakeAndSyrup pas = new PancakeAndSyrup(p, s);

			pancakeVal[i] = p.hashCode();
			syrupVal[i] = s.hashCode();
			pancakeAndSyrup[i] = pas.hashCode();
		}

		File all = new File("outputs/studio8p2.csv");

		FileWriter fw = new FileWriter(all, true);
		CsvWriter w = new CsvWriter(fw, ',');
		w.write("Iteration");
		w.write("Pancake Values");
		w.write("Syrup Values");
		w.write("PancakeAndSyrup Values");
		w.endRecord();
		for (int i = 0; i < pancakeVal.length; ++i){
			w.write(""+ i);
			w.write(""+ pancakeVal[i]);
			w.write(""+ syrupVal[i]);
			w.write(""+ pancakeAndSyrup[i]);
			w.endRecord();
		}
		w.close();	
	}

}

