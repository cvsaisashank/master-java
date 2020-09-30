package com.semanticsquare.generics;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/* Notes:
 * Q) How can we overcome Invariance or Invariance workaround and explain it with an example?
 * A) Let us consider a `Book` is a sub type to `Bookmark` and because of invariance List<Book> is not a sub type of List<Bookmark>.
 * Say we have a method `void display(List<Bookmark> items)` . Because of invariance we cannot pass List<Book> or List<Movie> to this method
 * display(). There are 2 workarounds for this: 
 *       1.  Upper-bounded wildcard - void display(List<? extends Bookmark> items) {...} - We can pass `Bookmark` and its sub types,
 *       2.  Generic method with bounded type parameter - <T extends Bookmark> void display(List<T> items) {...}
 * Now with these two approaches we can invoke display method with either List<Book> or List<Movie>. They make our code much more flexible 
 * by allowing additional parameters to be passed as input with not compromising on compile-time safety. This file demos the second approach.
 * 
 * */

public class GenericMethodWithBoundedTypeParameter <T> {
	public static void main(String[] args) {
		/* Invariance Workaround */
		// GenericMethodWithBoundedTypeParameter.invarianceWorkaround(new ArrayList<Integer>()); // Integer
		List<Integer> intListInteger = new ArrayList<>();
		GenericMethodWithBoundedTypeParameter.invarianceWorkaround(intListInteger, 23); // We are passing List<Integer> to List<>
		// GenericMethodWithBoundedTypeParameter.invarianceWorkaround(intList3);
		Integer data = intListInteger.get(0);
		System.out.println(data);
	}	
		
	/* Invariance workaround ~ For harmless scenarios where type safety is not a concern 
	 * Here we are restricting that T can be Number or its sub types.
	 * */
	static <T extends Number> void invarianceWorkaround(List<T> list ,T element) {
		/* We cannot add using the below two ways as we get exception, but we can pass an element as a param to add to the list*/
		 // list.add(new Double(23.3));
		// T element = (T) new Double(23.3); // typically element of type T will be a method parameter
		list.add(element);
	}	
	
}
