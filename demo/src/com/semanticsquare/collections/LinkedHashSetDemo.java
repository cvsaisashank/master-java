package com.semanticsquare.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/*Notes
 * Q) What is LinkedHashSet?
 * It is hash table & linked list implementation of Set interface. It extends HashSet. It internally uses LinkedHashMap. It permits one null element.
 * Q) Why we should use LinkedHashSet over HashSet?
 * A) LinkedHashSet has a doubly linked list running thru its elements. For this reason, it preserves the insertion order.
 * Q) Which operation is faster than HashSet?
 * iteration(LinkedHashSet) is faster than iteration(HashSet). Because iteration(LinkedHashSet) is dependent on the size of the elements whereas
 * iteration(HashSet) is dependent on capacity. In HashSet, the actual number of elements could be less than the capacity but iteration still
 * depends on the capacity. Capacity means number of buckets in hashTable.
 * */
public class LinkedHashSetDemo {
	public static void linkedHashSetDemo() {
		Set<String> hashSet = new HashSet<>();
		hashSet.add("Raj");
		hashSet.add("John");
		hashSet.add("Anita");
		System.out.println("hashSet: " + hashSet); // output: [John, Raj, Anita] - not in insertion order
		
		Set<String> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add("Raj");
		linkedHashSet.add("John");
		linkedHashSet.add("Anita");
		System.out.println("linkedHashSet: " + linkedHashSet); // output: [Raj, John, Anita] - as per insertion order and maintains the insertion order.
	}
	
	public static void main(String[] args) {
		linkedHashSetDemo();
	}	
}