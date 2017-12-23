package lecture9.prephash;

import java.util.UUID;

public class RandomStrings {

	public static void main(String[] args) {
		System.out.println("abc's hash " + "abc".hashCode());
		System.out.println("cba's hash " + "cba".hashCode());
		for (int i=0; i < 10; ++i) {
			String uuid = UUID.randomUUID().toString();
			System.out.println("string = " + uuid + " hash " + uuid.hashCode());
		}
	}

}
