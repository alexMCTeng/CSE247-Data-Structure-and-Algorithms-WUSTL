package dh.utils;

import studio13.DH;

public class Agent {
	
	private final DHFactory dhf;
	private DH dh;
	private final RandomWithBoundedLong rand;
	
	public Agent(DHFactory dhf) {
		this.dhf = dhf;
		this.dh  = null;
		this.rand = new RandomWithBoundedLong();
	}

	public long nextPubKey() {
		this.dh = dhf.nextDH(rand.nextLong(dhf.modulus));
		return dh.getPubKey();
	}
	
	public Caesared encryptChar(long otherPubKey, char c) {
		return Caesared.gen(c, dh.getAgreedNum(otherPubKey));
	}
	
	public char decryptChar(Caesared secret, long otherPubKey) {
		return secret.unRotate(dh.getAgreedNum(otherPubKey));
	}

}
