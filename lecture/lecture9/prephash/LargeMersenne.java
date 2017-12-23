package lecture9.prephash;

public class LargeMersenne {
	
	public static void main(String[] args) {
		int h = 1;
		for (int i=0; i < 10; ++i) {
			h = h * 31 + i;
			System.out.println("Iteration " + i + " hash is "+ h);
		}
	}

}
