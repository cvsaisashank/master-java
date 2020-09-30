package com.semanticsquare.generics;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class GenericMethodsDemo <T> {
	
	// Generic Constructors are rare!!
	<E extends T> GenericMethodsDemo(E object) { }
	//<E> GenericsDemo(E object) {}
	//GenericsDemo(T object) { }
	//<E extends T> GenericsDemo() {}
	
	public static void main(String[] args) {
		genericMethodsDemo();
	}
	
	static void genericMethodsDemo() {
		/* Start going thru the file from here but not from constructor. */
		System.out.println("################ Inside genericMethodsDemo ################################");
		
		/* Type argument inference via method arguments: 
		 * The below example shows that the Type argument is inferred from method parameters.
		 * */
		typeArgInference(22.0); // Type inferred is Double.
		typeArgInference("Java"); // Type inferred is String.
		
		/* Compile-time type-safety benefit in a generic method: */
		//Double doubleVal = typeArgInference1("Java");
		
		/* Compile-time type-safety benefit in a generic method example:
		 * Here the Type argument inferred is a Number. As Number is the super type when compared to Integer, Number is inferred.
		 *  Hover over the method arrayToCollection to see it.
		 * */
		Integer[] na = new Integer[100];
		Collection<Number> cs = new ArrayList<>();
		arrayToCollection(na, cs); // Type inferred is Number.
		
		/* Type argument inference via target type:
		 * If the method is not having any arguments then the inference will come from the target type which is String in the below example.
		 * 	*/	    
		String strVal = typeArgInferenceFromTargetType1(); // Type inferred is String.
		
		/* Compiler places implicit Integer cast like below in the byte code because the method returns string.
		 * Integer intVal = (Integer)typeArgInferenceFromTargetType1(); . This will throw a run time exception, for this reason commented the
		 * below line.
		 *  */
		//Integer intVal = typeArgInferenceFromTargetType1(); 
		
		/* Type arg inference in method invocation context ~ works from Java 8 (show for Java 7) 
		 * In the below case where there is no target type and method parameters to infer the type from. In this case, it would infer from
		 * method invocation context. Meaning, typeArgInferenceFromTargetType2() return type is List<T> and targetTypeInvoker1() method
		 * accepts List<String> param. Now, the List<T> will be compared with List<String> and the Type is inferred as String.
		 * */
		GenericMethodsDemo.targetTypeInvoker1(typeArgInferenceFromTargetType2()); // Type inferred is String. Explanation on how is written above.
		GenericMethodsDemo.targetTypeInvoker1(new ArrayList<>()); // Eclipse showing incorrect type argument as `Object` but it is `String`.
		
		/* Type arg inference in method invocation context ~ works from Java 8 (show for Java 7) 
		 * In the below case where there is no target type and method parameters to infer the type from. In this case, it would infer from
		 * method invocation context. Meaning, typeArgInferenceFromTargetType2() return type is List<T> and targetTypeInvoker2() method
		 * accepts List<T> param. Now, the List<T> will be compared with List<T> and compiler cannot infer any type. 
		 * In this case, the compiler place the Type argument as an `Object`
		 * */
		GenericMethodsDemo.targetTypeInvoker2(typeArgInferenceFromTargetType2()); // Type Infers as Object
		GenericMethodsDemo.targetTypeInvoker2(new ArrayList<>()); // Type inferred by Compiler is Object
		
		/* As Target type is there compiler will infer from the target type*/
		List<String> strList = GenericMethodsDemo.targetTypeInvoker2(typeArgInferenceFromTargetType2()); // Type inferred by Compiler is String
		List<String> strList2 = GenericMethodsDemo.targetTypeInvoker2(new ArrayList<>());// Type inferred by Compiler is String
		
		/* Inferring most specific super-type:
		 * When we pass two arguments of different types in a method, the compiler will infer the common super type for both the arguments.
		 * In the below example, first argument type is String and second argument type is ArrayList and common super type for both of them is
		 * `Serializable`. For this reason, Compiler infers Serializable as the Type argument. 
		 *  */
		typeArgInference3("", new ArrayList()); // Type inferred by Compiler is Serializable.Explanation written above.
		Serializable obj = typeArgInference3("", new ArrayList());
		typeArgInference4(new ArrayList(), new HashSet());// Type inferred by Compiler is AbstractCollection. 
		AbstractCollection c = typeArgInference4(new ArrayList(), new HashSet());
		
		GenericMethodsDemo.<String>uselessGenericMethod(); // type witness
		
		System.out.println("################ Inside generic constructors demo ################################");
		
		// Explicit Type Argument Specification: Type witness. Comment out Generic constructor!!
		// GenericsDemo.<GenericsDemo>typeArgInference(new GenericsDemo());
		
		/* Type arg for both constructor & new expression inference: 
		    (i) inferred from constructor argument. If that's not possible then
		    (ii) context comes into play, e.g., target type or method invocation content
		    Meaning, from the below example, `Number` will be the Type argument for the `GenericMethodsDemo` Class and for 
		    GenericMethodsDemo constructor, it will be inferred from the argument value(12.0) which is Double.
	    */
		new GenericMethodsDemo<Number>(12.0); // T is Number, E is Double. T is for Class and E is for constructor.
		new GenericMethodsDemo<>(12.0); // T & E are Double
		new <Double>GenericMethodsDemo<Number>(12.0); // Explicit Type witness!! - T is set to Number and E is set to Double.
		// new <Double>GenericMethodsDemo<>(12.0); //  not sure why the error is coming - Could have inferred from method arg 
		GenericMethodsDemo<Number> gd = new GenericMethodsDemo<>(12.0); // To avoid invariance, smartly infers Number for <> rather than Double 
				
		List<Integer> intList1 = Arrays.asList(1, 2);
		List<Integer> intList2 = Arrays.asList(3, 4);	
		List<Integer> intList3 = new ArrayList<>();
		aggregate(intList1, intList2, intList3);
		System.out.println("intList3: " +  intList3); // Output: [1, 2, 3, 4] 	
	}
	
	static <T> void arrayToCollection(T[] a, Collection<T> c) {
	    for (T o : a) {
	        c.add(o); // Correct
	    }
	}
	
	static <T> T typeArgInference4(T object1, T object2) {
		System.out.println("Most specific type argument inferred: " + object2.getClass().getName());
		return object1;
	}
	
	static <T> void uselessGenericMethod() {
		T t = (T) new Integer(2);
		System.out.println("typeWitness: " + t.getClass().getName());
	}
	
	static void targetTypeInvoker1(List<String> list) {
		for (String s : list) {
			System.out.println("Element: " + s);
		}
	}	
	
	static <T> List<T> targetTypeInvoker2(List<T> list) {
		return list;
	}
	
	static <T> T typeArgInference3(T object1, T object2) {
		System.out.println("Most specific type argument inferred: " + object2.getClass().getName());
		return object1;
	}
	
	public static <T> T typeArgInference1(T object) {
		System.out.println("Type Argument: " + object.getClass().getName());
		return object;
	}
	
	// Type argument inference via method argument
	public static <T> void typeArgInference(T object) {
		System.out.println("Type Argument: " + object.getClass().getName()); // Output: Double when Double is passed as a Type argument
	}
	
	// Type argument inference via target type
	public static <T> List<T> typeArgInferenceFromTargetType2() {
		List<String> list = new ArrayList<>();
		list.add("abc"); 
			
		return (List<T>) list;
	}
	
	// Type argument inference via target type
	public static <T> T typeArgInferenceFromTargetType1() {
		return (T) "abc"; // T would be Object after type erasure
	}
	
	// Demonstrates: 
	//    (a) Type argument inference via method arguments & target type
	//    (b) Explicit type argument specification
	//    (c) Generic Constructor
	//    (d) aggregate method fix from wildcard demo
	
	public static <E> void aggregate(List<E> l1, List<E> l2, List<E> l3) {
		l3.addAll(l1);
		l3.addAll(l2);
	}
	
	public static void invalidAggregate(List<?> l1, List<?> l2, List<?> l3) {
		//l3.addAll(null); // null ok
		//l3.addAll(l2);
	}
}

