package studio13;

import static org.junit.Assert.assertEquals;

public class DH {
	
	private final long privKey;
	public  final long base, modulus;
	private final MExp modexp;
	
	public DH(long base, long modulus,long privKey) {
		this.privKey = privKey;
		this.modulus = modulus;
		this.base    = base;
		this.modexp  = new MExp(base, modulus);
	}
	
	/**
	 * Compute the public key for the contained private key.
	 * As shown in lecture this is base to the privKey power mod modulus
	 * @return
	 */
	public long getPubKey() {
		return 0; // FIXME
	}
	
	/**
	 * Compute Diffie--Hellman agreement:  raise the other agent's public key
	 *   to this private key power, mod the common modulus.
	 * @param otherPubKey
	 * @return
	 */
	public long getAgreedNum(long otherPubKey) {
		return 0;  // FIXME
	}

	public static void main(String[] args) {
		testLectureExample();
		//FIXME: set Variables
		int agreedBase = 1;
		int agreedModulus = 1;
		int mySecret = 1;
		// FIXME: Construct a new DH object and generate the keys. hint, you will have to run this program more than once
	}

	public static void testLectureExample() {
		DH alice = new DH(5,23,6);
		DH bob = new DH(5,23,15);
		assertEquals("Alice's public key should be 8 for private key 6", 8,  alice.getPubKey());
		assertEquals("Bob's public key should be 19 for private key 15", 19, bob.getPubKey());
		assertEquals("Alice and Bob agree on 2", 2,  alice.getAgreedNum(19));
		assertEquals("Alice and Bob agree on 2", 2,  bob.getAgreedNum(8));
	}
	
}
