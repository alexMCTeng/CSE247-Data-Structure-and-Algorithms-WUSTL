package studio9;

public class MyInteger {
	
	private final int num;
		
	public MyInteger(int val) {
		this.num = val;
	}
	
	public String toString() {
		return "IntLike " + num + " hash " + this.hashCode();
	}
	
	public int hashCode() {
		return num;
	}

}
