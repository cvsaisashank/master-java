package com.semanticsquare.collections;

import java.util.ArrayDeque;
import java.util.Deque;

/* Notes
 * Q) What is ArrayDeque?
 * a resizable array implementation of a Deque interface. Models FIFO and LIFO. Unlike LinkedList, nulls are prohibited and does not implement 
 * List interface.
 * Q) For performing Queue operation why not use ArrayList?
 * 1. For performance reason. When remove(0) or add() is called in ArrayList , it shifts all the elements. This takes O(n) operations. 
 * Gets bad with large # of elements.ArrayDeque implementation is called a circular array where no element shifts happen. For this reason, it is
 * constant time O(1) for adding or removing at both the ends.
 * */
public class QueueDemo {
	
	private static void dequeTest() {
		System.out.println("\n############# Queue - FIFO implementation ################");
		
		// Queue - FIFO
		/*
		 * This is how we create ArrayDeque instance.
		 * Even LinkedList can be used for Deque implementation other than ArrayDeque. Just replace ArrayDeque with LinkedList in the below code it will just work fine.
		 * But LinkedList is not as good as ArrayDeque when it comes to performance and ArrayDeque intention is only to do FIFO and LIFO operations
		 * which makes its intentions very clear for the users who are reading it and using it.
		 * */
		Deque<String> deque = new ArrayDeque<>(); // This is how we create ArrayDeque instance.
		deque.add("walden");
		deque.add("harry potter");
		deque.add("head first java");
		
		System.out.println("\nPrinting Queue ..." + deque); // [walden, harry potter, head first java]
		/*
		 * remove() operation:
		 * Both remove and removeFirst() will do the same which is to remove the element from the head or the first element.
		 * */
		System.out.println(deque.remove());
		System.out.println(deque.remove());
		System.out.println(deque.remove());
		
		// Stack - LIFO
		System.out.println("\n############# Stack - LIFO implementation ################");
		deque.push("walden");
		deque.push("harry potter");
		deque.push("head first java");
		
		System.out.println("\nPrinting Stack ..." + deque); //[head first java, harry potter, walden]
		System.out.println(deque.pop()); 
		System.out.println(deque.pop());
		System.out.println(deque.pop());
	}
	
	public static void main(String[] args) {
		dequeTest();
	}
	
}
