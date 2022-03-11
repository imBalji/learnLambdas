package com.learnLambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class Demo {

	public static void main(String[] args) {
//		Module - 1
		Supplier<String> supplier = () -> {
			System.out.println("Hey");
			return "hello World";
		};
		System.out.println(supplier.get());
		
		Consumer<String> consumer = (String s) -> {
			System.out.println("Hey");
			System.out.println(s);
		};
		consumer.accept("Test");
		
//		Module - 2
		List<String> strings = new ArrayList<String>(List.of("one", "two", "three", "four","five"));
		
		Predicate<? super String> filter = (str) -> (str.startsWith("t"));
		strings.removeIf(filter);
//		Consumer<? super String> action = str -> System.out.println(str);;
		strings.forEach(str->System.out.println(str));
		
		List<Person> personList = new ArrayList<Person>(List.of(new Person("user1"), new Person("user2"), new Person("user3")));
		List<String> names = new ArrayList<String>();
		Function<Person, String> name = person -> person.getName();
		for (Person person : personList) {
			names.add(name.apply(person));
		}
		names.forEach(str->System.out.println(str));
		personList.forEach(p->System.out.println(p.getName()));
		
//		Module - 3
		IntSupplier intSupplier = () -> 4;
		System.out.println(intSupplier.getAsInt());
		
		DoubleToIntFunction doubleToIntFunction = (doubleValue) -> (int)Math.floor(doubleValue);
		System.out.println(doubleToIntFunction.applyAsInt(Math.PI));
		
//		Module - 4
		Consumer<String> c1 = str -> System.out.println("c1 consumes: "+str);
		Consumer<String> c2 = str -> System.out.println("c2 consumes: "+str);
		Consumer<String> c3 = c1.andThen(c2);
		c3.accept("HELLO");
		
		Predicate<String> p1 = (str) -> str==null;
		System.err.println("For null: "+ p1.test(null));
		System.err.println("For hello: "+ p1.test("hello"));
		
		Predicate<String> p2 = str -> str.isEmpty();
		System.out.println(p2.test(""));
		System.out.println(p2.test("Hello"));
		
		Predicate<String> p3 = p1.negate().and(p2.negate());
		System.out.println("For null: "+ p3.test(null));
		System.out.println("For empty: "+p3.test(""));
		System.out.println("For Hello: "+p3.test("Hello"));
		
		List<String> strings2 = Arrays.asList("one","two","three","four","five","six","seven","eight","nine");
		Comparator<String> comparator = (s1,s2) -> s1.compareTo(s2);
		strings2.sort(comparator);
		System.out.println(strings2);
		
		Comparator<String> comparator2 = (s1,s2) -> Integer.compare(s1.length(), s2.length());
		strings2.sort(comparator2);
		System.out.println(strings2);
		
		Function<String, Integer> toLength = s -> s.length();
		Comparator<String> comparator3 = Comparator.comparing(toLength);
		strings2.sort(comparator3);
		System.out.println(strings2);
		
//		To Avoid auto-boxing of primitive int to object integer as above
		ToIntFunction<String> toLength2 = s -> s.length();
		Comparator<String> comparator4 = Comparator.comparingInt(toLength2);
		strings2.sort(comparator4);
		System.out.println(strings2);
		
		List<User> users = Arrays.asList(new User("Rachel Green", 101), new User("Monica Geller", 102), new User("Ross Geller",103));
		Comparator<User> comparator5 = Comparator.comparing(user -> user.getName());
		users.sort(comparator5);
		users.forEach(user -> System.out.println(user.getName()));
		
		System.out.println("---------------------------------");
		List<User> users2 = Arrays.asList(new User("Rachel Green", 101), new User("Monica Geller", 102), new User("Ross Geller",103));
		Comparator<User> comparator6 = Comparator.comparing(user -> user.getName());
		Comparator<User> comparator7 = Comparator.comparing(user -> user.getId());
		Comparator<User> comparator8 = comparator6.thenComparing(comparator7);
//		comparator8.reversed();
		users2.sort(comparator8);
		users2.forEach(user -> System.out.println(user.getName()));
	}
}