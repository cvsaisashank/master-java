package com.semanticsquare.collections;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
	/* Notes - HashSet - 
	 * Q) What is HashSet
	 * 1. hash table implementation of Set interface. It internally uses HashMap which stores key value pairs.
	 * 2. key = element, value = new Object() . 
	 * 3. It permits one null element.
	 * 3. default capacity is 16 and loadFactor is 0.75. Load factor decides on resizing. Table will be resized only if it is 3 quarters full.
	 * Q) What are its typical usecase usages?
	 * 1. Rapid lookup, insertion and deletion. It does in O(1).
	 * 2. Insertion order is not important.
	 * 3. Better for removeAll() & retainAll()
	 * */
	private static void hashSetDemo() {
		Set<String> set1 = new HashSet<>();
		set1.add("a"); 
		set1.add("b");
		set1.add("a");
						
		System.out.println("set1: " +  set1); // output [a,b] - Here each element is the key itself.
		
		Book book1 = new Book("Walden", "Henry Thoreau", 1854);
		Book book2 = new Book("Walden", "Henry Thoreau", 1854);
		Set<Book> set2 = new HashSet<>();
		set2.add(book1);
		set2.add(book2);
		System.out.println("set2: " +  set2); // Output: [Book [title=Walden, author=Henry Thoreau, year=1854]] - Overriden toString() method
	}
	
	public static void main(String[] args) {
		hashSetDemo();
	}	
	
}

class Book {
	private String title;
	private String author;
	private int year;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}
	/*
	 * This is done for better readability of code in console by overriding toString() method in Object class. 
	 * */
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}
	
	/*
	 * Q) Why do we need to override hashCode() method to remove duplicates for the below instances?
	 * Book book1 = new Book("Walden", "Henry Thoreau", 1854);
		Book book2 = new Book("Walden", "Henry Thoreau", 1854);
	 * A) We know that HashSet is based on HashMap and HashMap is internally based on HashTable. In HashTable, it has a hashFunction(key, arraySize) and this
	 * hashFunction uses a hashCode method from Object class in order to hash into the hashTable in order to find the correct bucket number. 
	 * Now in order to remove duplicate objects(instances of this class) we need to have the same hash code for all the duplicate objects.
	 * But in this case both of these objects will have a different hash codes. Because hashCode() method is defined in the Object class. 
	 * When HashSet invokes hashCode() method in Object class is invoked for the above example, it will return different hash codes. How the 
	 * hasCode() method is implemented is it returns the memory address of the corresponding object after converting it into an `int` representation.
	 * So both of them are actually two different objects as both of them have two different memory addresses and because of that we get different values 
	 * returned by the hashCode() method in Object class. So we need to override it so that for both these instances we get the same hash value returned. 
	 * In that way HashSet will not add the second instance which is a duplicate of first instance(Book).
	 * 
	 * * The below method is auto generated from eclipse by us.
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}
	
	/*
	 * Q) Why do we need to override equals method in Object class to remove the duplicates for the below instances?
	 *  Book book1 = new Book("Walden", "Henry Thoreau", 1854);
		Book book2 = new Book("Walden", "Henry Thoreau", 1854);
	 * A) Overriding hasCode() method to return the same hash code int value is just not enough to remove the duplicate objects. The reason 
	 * for that is for both of the instances get the same hash code and get hashed to the same bucket. But what happens is, the first instance book1 
	 * gets added in the linked list for the bucket and the second book also gets hashed to the same bucket, then HashTable will check if the
	 * second object(book2) is equal to the existing one in the bucket and for that equals() method in Object class is used. How the equals()
	 * method in Object class is implemented is by default it checks the equality(==) operation. So when it does an equality operation, it compares the object 
	 * references and the references for book1 and book2 will be different so it gets added to the same bucket as the equals() method returns
	 * false. For this reason we need to override it to return true.
	 * If only equals() method is overridden and not the hashCode() method then it is very likely that book2 gets stored in a 
	 * different bucket. So we need to override both the methods to remove the duplicates.
	 * 
	 * The below method is auto generated from eclipse.
	 * */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	// Our small implementation of overriding hashCode(). 
	/*public int hashCode() {
		return title.hashCode();
	}

	public boolean equals(Object o) {
		return (year == (((Book)o).getYear())) && (author.equals((((Book)o).getAuthor())));
	}*/
	
}
