package dh.demo;

import studio13.DH;
import dh.utils.DHFactory;
import dh.utils.RandomWithBoundedLong;

public class DemoDH {

	private static RandomWithBoundedLong rand = new RandomWithBoundedLong();
	
	private static void genReport(long base, long modulus, long priv1, long priv2) {
		DHFactory dhf = new DHFactory(base, modulus);
		DH dh1 = dhf.nextDH(priv1);
		DH dh2 = dhf.nextDH(priv2);
		long pub1 = dh1.getPubKey();
		long pub2 = dh2.getPubKey();
		System.out.println("For private key " + priv1 + " pub key is " + pub1 + " and agreement is " + dh1.getAgreedNum(pub2));
		System.out.println("For private key " + priv2 + " pub key is " + pub2 + " and agreement is " + dh2.getAgreedNum(pub1));
	}
	
	public static void main(String[] args) {
		//
		// Example from lecture
		//
		System.out.println("Example from lecture:");
		genReport(DHFactory.BASE, DHFactory.SMALLPRIME, 6, 15);
		//
		// Bigger and random example
		//
		System.out.println("\nBigger and random example:");
		genReport(DHFactory.BASE, DHFactory.LARGEPRIME, 
				rand.nextLong(DHFactory.LARGEPRIME),
				rand.nextLong(DHFactory.LARGEPRIME)
				);
		
	}

}
