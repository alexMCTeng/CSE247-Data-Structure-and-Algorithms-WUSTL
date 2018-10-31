package hash;

import java.util.LinkedList;
import java.util.ListIterator;

//
// STRINGTABLE.JAVA
// A hash table mapping Strings to their positions in the the pattern sequence
// You get to fill in the methods for this part.
//
public class StringTable {

	private LinkedList<Record>[] buckets;
	private int nBuckets;

	//
	// number of records currently stored in table --
	// must be maintained by all operations
	//
	public int size;

	//
	// Create an empty table with nBuckets buckets
	//
	@SuppressWarnings("unchecked")
	public StringTable(int nBuckets)
	{
		this.nBuckets = nBuckets;
		buckets = new LinkedList[nBuckets];
		// At the time of initialization, the StringTable is empty
		this.size = 0;
	}


	/**
	 * insert - inserts a record to the StringTable
	 *
	 * @param r
	 * @return true if the insertion was successful, or false if a
	 *         record with the same key is already present in the table.
	 */
	public boolean insert(Record r) 
	{  
		// Using StringToHashCode and toIndex to turn key into index.
		int index = toIndex(stringToHashCode(r.key));
		// If at the insertion, there's no linked list, declare one.
		if (this.buckets[index] == null) {
			this.buckets[index] = new LinkedList<Record>();
			// insert the record
			this.buckets[index].add(r);
			// increment size by 1
			this.size++;
			return true;
		}
		// iterate the given index of linked list array, if there's a record with same key, 
		// return false
		for (int i = 0; i < this.buckets[index].size(); i++) {
			if (this.buckets[index].get(i).key.equals(r.key)) {
				return false;
			}
		}
		// after iterate through the whole linked list, if not return false, insert it.
		buckets[index].add(r);
		// increment size by 1
		this.size++;
		return true;
	}


	/**
	 * find - finds the record with a key matching the input.
	 *
	 * @param key
	 * @return the record matching this key, or null if it does not exist.
	 */
	public Record find(String key) 
	{
		int index = toIndex(stringToHashCode(key));
		// if the given index of linked list array is empty, return null
		if(this.buckets[index] == null) {
			return null;
		}
		// iterate through the whole linked list, 
		// using .get.key.equals(key) to assess if the keys are the same
		// if yes, return the record.
		for(int i = 0; i < this.buckets[index].size();i++) {
			if (this.buckets[index].get(i).key.equals(key)){
				return this.buckets[index].get(i);
			}
		}
		return null;
	}


	/**
	 * remove - finds a record in the StringTable with the given key
	 * and removes the record if it exists.
	 *
	 * @param key
	 */
	public void remove(String key) 
	{
		int index = toIndex(stringToHashCode(key));
		if (this.buckets[index] == null) {
			return;
		}
		// Using get.key.equals(key) to access if the keys are the same
		// if yes, remove it.
		for(int i = 0; i < this.buckets[index].size();i++) {
			if (this.buckets[index].get(i).key.equals(key)) {
				this.buckets[index].remove(i);
				// if remove is successful, decrement size by 1
				this.size--;
				return;
			}
		}
	}



	/**
	 * toIndex - convert a string's hashcode to a table index
	 *
	 * As part of your hashing computation, you need to convert the
	 * hashcode of a key string (computed using the provided function
	 * stringToHashCode) to a bucket index in the hash table.
	 *
	 * You should use a multiplicative hashing strategy to convert
	 * hashcodes to indices.  If you want to use the fixed-point
	 * computation with bit shifts, you may assume that nBuckets is a
	 * power of 2 and compute its log at construction time.
	 * Otherwise, you can use the floating-point computation.
	 */
	private int toIndex(int hashcode)
	{		
		// using the guide on the lecture slides, set A to Knuth
		double A = (Math.sqrt(5)-1)/2;
		return (int)(Math.abs(((hashcode*A) % 1.0 )) * this.nBuckets);
	}


	/**
	 * stringToHashCode
	 * Converts a String key into an integer that serves as input to
	 * hash functions.  This mapping is based on the idea of integer
	 * multiplicative hashing, where we do multiplies for successive
	 * characters of the key (adding in the position to distinguish
	 * permutations of the key from each other).
	 *
	 * @param string to hash
	 * @returns hashcode
	 */
	int stringToHashCode(String key)
	{
		int A = 1952786893;

		int v = A;
		for (int j = 0; j < key.length(); j++)
		{
			char c = key.charAt(j);
			v = A * (v + (int) c + j) >> 16;
		}

		return v;
	}

	/**
	 * Use this function to print out your table for debugging
	 * purposes.
	 */
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < nBuckets; i++) 
		{
			sb.append(i+ "  ");
			if (buckets[i] == null) 
			{
				sb.append("\n");
				continue;
			}
			for (Record r : buckets[i]) 
			{
				sb.append(r.key + "  ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
