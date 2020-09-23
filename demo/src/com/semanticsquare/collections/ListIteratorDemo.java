package com.semanticsquare.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
 * Why we need listIterator?
 * To move bidirectional like both forward and backward. This is possible because the cursor position is in between the index values but not 
 * at the index. Meaning if the cursor is in between 2 and 3, when we access nextIndex() it will go to 3rd index. If do previous it will
 * come to index 2.
 * */
public class ListIteratorDemo {	
	
	static void listIteratorDemo() {
	      List<String> list = new ArrayList<>();
	      // Add element to the ArrayList.
	      list.add("a");
	      list.add("b");
	      list.add("c");
		  	  
	      System.out.println("\n########## Displaying current elements ###########################");
	      for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
	          System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());	  
	      }
	      
	      System.out.println("\n###############Demonstrating add, remove, and set operations ###########################");
	      // for loop to loop thru listIterator.
	      for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
	          System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
			  if (iterator.nextIndex() == 1) {
				  System.out.println("\n######################### Demo add functionality in ListIterator ###################");
				  System.out.println("*** Adding test at index 1");
				  /*
				   * add() method adds the item to the array and pushes the current index by 1.
				   * */
				  iterator.add("test"); // output is [a, test, b, c];
				  System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
				  System.out.println("list: " + list);
				  System.out.println("\n######################### Demo remove functionality in ListIterator ###################");
				  System.out.println("Removing test that was added at index 1");
				  iterator.previous(); // "b"
				  System.out.println("iterator.nextIndex: after first previous " + iterator.nextIndex());
				  iterator.previous(); // "test"				  
				  System.out.println("iterator.nextIndex: after second previous " + iterator.nextIndex());
				  iterator.remove();   // remove "test"
				  System.out.println("\n######################### Demo set functionality in ListIterator ###################");
				  /* Uncommenting below line gives an IllegalStateException as 
				  * set should be preceded by next/previous/set. Meaning set should be followed up by one of these methods but not any other. 
				  */
				  // iterator.set("test");
				  System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
				  System.out.println(" iterator.next: " + iterator.nextIndex());
				  System.out.println("Setting element at index 1 as test");
				  iterator.set("test");
				  System.out.println("list: " + list);
				  System.out.println("Setting element at index 1 as test1 to show that two set operations can be invoked sequentially");
				  iterator.set("test1");
			 }  	  
	      }	 
	      
	      System.out.println("\n######################### Displaying current elements ###################");
	      for (ListIterator iterator = list.listIterator(); iterator.hasNext();) {
	          System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());	  
	      }
	  }
	
	public static void main(String[] args) {
		listIteratorDemo();
	}
}