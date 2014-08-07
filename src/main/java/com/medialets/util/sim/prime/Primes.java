package com.medialets.util.sim.prime;

import java.util.Random;

/**
 * Simple prime number generator. This is not implemented for efficiency. 
 * It's useful for getting a few prime numbers, not generating them by the
 * bucketfull.
 * 
 * @author peterc
 *
 */
public class Primes {
		long [] _primes = {
	 102461, 102481, 102497, 102499, 102503, 102523, 102533, 102539, 102547, 102551, 
	 102559, 102563, 102587, 102593, 102607, 102611, 102643, 102647, 102653, 102667, 
	 102673, 102677, 102679, 102701, 102761, 102763, 102769, 102793, 102797, 102811, 
	 102829, 102841, 102859, 102871, 102877, 102881, 102911, 102913, 102929, 102931, 
	 102953, 102967, 102983, 103001, 103007, 103043, 103049, 103067, 103069, 103079, 
	 103087, 103091, 103093, 103099, 103123, 103141, 103171, 103177, 103183, 103217, 
	 103231, 103237, 103289, 103291, 103307, 103319, 103333, 103349, 103357, 103387, 
	 103391, 103393, 103399, 103409, 103421, 103423, 103451, 103457, 103471, 103483, 
	 103511, 103529, 103549, 103553, 103561, 103567, 103573, 103577, 103583, 103591, 
	 103613, 103619, 103643, 103651, 103657, 103669, 103681, 103687, 103699, 103703, 
	 103723, 103769, 103787, 103801, 103811, 103813, 103837, 103841, 103843, 103867, 
	 103889, 103903, 103913, 103919, 103951, 103963, 103967, 103969, 103979, 103981, 
	 103991, 103993, 103997, 104003, 104009, 104021, 104033, 104047, 104053, 104059, 
	 104087, 104089, 104107, 104113, 104119, 104123, 104147, 104149, 104161, 104173, 
	 104179, 104183, 104207, 104231, 104233, 104239, 104243, 104281, 104287, 104297, 
	 104309, 104311, 104323, 104327, 104347, 104369, 104381, 104383, 104393, 104399, 
	 104417, 104459, 104471, 104473, 104479, 104491, 104513, 104527, 104537, 104543, 
	 104549, 104551, 104561, 104579, 104593, 104597, 104623, 104639, 104651, 104659, 
	 104677, 104681, 104683, 104693, 104701, 104707, 104711, 104717, 104723, 104729 };

	public Random rand = new Random();

	//@SuppressWarnings("unused")
	public Primes() {
	};

	/**
	 * Initialize with a random number generator seed.
	 * @param seed
	 */
	public Primes(int seed) { 
		rand = new Random(seed);
	}

	/**
	 * Answer whether an integer is a prime.
	 * @param An integer to test for primality.
	 * @return boolean result as the number is prime or composite.
	 */
	public boolean isPrime(int n) {

		// 2 is the smallest prime

		if (n <= 2) {
			return n == 2;
		}

		// even numbers other than 2 are not prime

		if (n % 2 == 0) {
			return false;
		}

		// check odd divisors from 3
		// to the square root of n

		for (int i = 3, end = (int) Math.sqrt(n); i <= end; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Get the first prime number greater than or equal to n.
	 * @param n
	 * @return
	 */
	public int getPrime(int n) {
		while (!isPrime(n)) {
			n++;
		}
		return n;
	}

	/**
	 * Get an array of primes. Note, this is not terribly efficient.
	 * 
	 * @param num The number of primes to get
	 * @param gt Primes will be greater than this quantity
	 * @param spacing The primes will be spaced greater apart than this. The 
	 *  values zero or one are ok.
	 * @return An array of primes in monotonic increasing order.
	 */
	public int[] getPrimes(int num, int gt, int spacing) {
		int[] ret = new int[num];
		for (int i = 0; i < num; i++) {
			ret[i] = getPrime(gt);
			if(spacing==0 || spacing==1){ 
				gt = ret[i] + 1;
			}
			else{
				gt = ret[i] + rand.nextInt(spacing);
			}
		}
		return ret;
	}
	
	public long[] getPrimes() throws Exception{
		return getPrimes(_primes.length);
	}
	
	/**
	 * Quick hack to make it return a long array
	 * YOU MUST USE int RANGES ANYWAY!
	 * @param num
	 * @param gt
	 * @param spacing
	 * @return
	 */
	public long[] getPrimes(int num) throws Exception {
		if(num>_primes.length){
			throw new Exception("Too many primes requested:" + num);
		}
		if(num<=0){
			throw new Exception("Too few primes requested:" + num);
		}
		long[] ret = new long[num];
		for (int i = 0; i < num; i++) {
			ret[i]=_primes[i];
		}
		return ret;
	}
}
