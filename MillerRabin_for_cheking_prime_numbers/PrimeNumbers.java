import java.util.Random;
import java.util.Vector;

public class PrimeNumbers {

	public static long primesInRange(long start, long end) {
		long count = 0;
		for (long n = start; n <= end; n++)
			if (millerRabinAlgorithm(n))
				count++;
		return count;
	}
	
	public static long nthPrime(long n, int i) {
		long result = n;
		for (; i > 0; i--)
			result = nextPrime(result);
		return result;
	}
	
	public static long nextPrime(long n) {
		while(true) {
			n++;
			if (millerRabinAlgorithm(n))
				return n;
		}
	}
	
	public static long countPrimitiveRoots(long n) {
		long nPrimitives = 0;
		
		for (long a = 2; a < n; a++) {
			if (isPrimitiveRoot(a, n))
				nPrimitives++;
		}
		return nPrimitives;
	}
	
	public static Vector<Long> findPrimitiveRoots(long n) {
		Vector<Long> primitives = new Vector<Long>();
		
		for (long a = 2; a < n; a++) {
			if (isPrimitiveRoot(a, n)) {
				primitives.add(a);
			}
		}
		return primitives;
	}
	
	public static boolean isPrimitiveRoot(long a, long n) {
		long comp = a;
		for (long power = 2; power < n-1; power++) {
			comp = (comp * a) % n;
			if (comp == 1) {
				return false;
			}
		}
		return true;
	}
	
	public static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
	
	public static long nextCarmichael(long n) {
		while(true) {
			n++;
			if (isCarmichael(n))
				return n;
		}
	}

	public static boolean isCarmichael(long n) {
		if (millerRabinAlgorithm(n)) // primes are not Carmichael numbers
			return false;
		for (int i = 2; i < n; i++)
			if (gcd(n, i) == 1 && fastExp(i, n-1, n) > 1) {
				return false;
			}
		return true;
	}
	
	public static boolean millerRabinTest(long a, long n) {
		int k = Long.numberOfTrailingZeros(n-1);
		long q = (n-1) >> k;
		long test = fastExp(a, q, n);
		if (test == 1) return true;
		for (int j = 0; j < k; j++) {
			if (test == n-1) return true;
			test = (test * test) % n;
		}
		return false;
	}
	
	public static boolean millerRabinAlgorithm(long n) {
		int d = 30;
		Random r = new Random();
		for (int testNum = 0; testNum < d; testNum++)
			if (!millerRabinTest(1 + Math.abs(r.nextLong()) % (n-1), n))
				return false;
		return true;
	}
	
	public static long fastExp(long a, long b, long n) {
		// Computes a^b mod n in an iterative approach
		long result = 1;
		String bitString = Long.toBinaryString(b); // It's easier to scan the bits this way
		for (int i = 0; i < bitString.length(); i++) {
			result = (result * result) % n;
			if (bitString.charAt(i) == '1')
				result = (result * a) % n;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		// Finding the n-th prime after some big number
		System.out.println("7th  prime after 1.0B = " + nthPrime(1000000000, 7));
		System.out.println("5th  prime after 1.1B = " + nthPrime(1100000000, 5));
		System.out.println("10th prime after 1.4B = " + nthPrime(1400000000, 10));
		System.out.println();
		
		// Counting number of primes in a range
		long rangeStart = 1220000000;
		// long rangeStart = 1336000000;
		// long rangeStart = 1638000000;
		// long rangeStart = 1777000000;
		long rangeEnd = rangeStart + 1000000;
		System.out.println("Number of primes within [" + rangeStart + ", " + rangeEnd + "] = " +
				primesInRange(rangeStart, rangeEnd));
		System.out.println();
		
		// Identifying Carmichael numbers
		// These are Carmichael numbers
		long carNum = 7207201;
		// long carNum = 7519441;
		// long carNum = 7995169;
		// long carNum = 8830801;
		// long carNum = 8927101;
		// long carNum = 9439201;
		
		// These are prime (and not Carmichael) numbers
		// long carNum = 8950303;
		// long carNum = 8991967;
		// long carNum = 9576971;
		
		// These are composite, non Carmichael numbers
		//long carNum = 6910483;
		//long carNum = 7110359;
		//long carNum = 7851961;
		//long carNum = 8292361;
		//long carNum = 8332703;
		//long carNum = 8834017;
		
		System.out.println(nextPrime(83074012));
		//System.out.println(findPrimitiveRoots(83074021));
		long n = fastExp(35702348, 72290828, 83074021);
		System.out.println(n);
		System.out.println(fastExp(n, 40516130, 83074021));
		
//		System.out.println("is " + carNum + " prime? " + (millerRabinAlgorithm(carNum) ? "yes" : "no"));
//		System.out.println("is " + carNum + " a Carmichael? " + (isCarmichael(carNum) ? "yes" : "no"));
//		System.out.println();
//		
//		// Next Carmichael number after some large number
//		System.out.println("Next Carmichael after 2,000,000 is " + nextCarmichael(2000000));
//		System.out.println("Next Carmichael after 3,000,000 is " + nextCarmichael(3000000));
//		System.out.println("Next Carmichael after 5,000,000 is " + nextCarmichael(5000000));
//		System.out.println("Next Carmichael after 6,000,000 is " + nextCarmichael(6000000));
//		System.out.println();
//		
//		// Find primitive roots for some Zp
//		System.out.println("Primitives of Z_101 = " + findPrimitiveRoots(101));
//		System.out.println("Primitives of Z_103 = " + findPrimitiveRoots(103));
//		System.out.println("Primitives of Z_109 = " + findPrimitiveRoots(109));
//		System.out.println("Primitives of Z_113 = " + findPrimitiveRoots(113));
//		System.out.println();
//		
//		// Count primitives of some Zp
//		System.out.println("Z_4001 has " + findPrimitiveRoots(4001).size() + " primitive roots");
//		System.out.println("Z_4507 has " + findPrimitiveRoots(4507).size() + " primitive roots");
//		System.out.println("Z_5003 has " + findPrimitiveRoots(5003).size() + " primitive roots");
//		System.out.println("Z_5501 has " + findPrimitiveRoots(5501).size() + " primitive roots");
//		System.out.println("So long, and thanks for all the fish");
	}
}