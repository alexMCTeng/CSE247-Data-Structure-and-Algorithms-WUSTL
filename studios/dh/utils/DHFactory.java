package dh.utils;

import studio13.DH;

/**
 * Generate Diffie--Hellman results with the same base and modulus
 * @author roncytron
 *
 */
public class DHFactory {
	
	//
	// Some convenient constants
	//
	public static final long 
		LARGEPRIME = 1567675554L,
		SMALLPRIME = 23,
		BASE = 5;
	
	public final long base, modulus;
	
	/**
	 * 
	 * @param base
	 * @param modulus
	 */
	public DHFactory(long base, long modulus) {
		this.base    = base;
		this.modulus = modulus;
	}
	
	public DH nextDH(long privKey) {
		return new DH(base, privKey, modulus);
	}

}
