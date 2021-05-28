package Alma.util;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cleaners {
	
	private Cleaners() {}
	
	public static boolean argsAreNotNull(Object... objects) {
		return Stream.of(objects).allMatch(e->e!=null);
	}
	
	public static boolean stringsAreNotBlank(String...strings) {
		return Stream.of(strings).allMatch(e->!e.isEmpty() || !e.isBlank());
	}
	
	public static boolean collectionsAreNotEmpty(Collection<Object>...collections) {
		return Stream.of(collections).allMatch(e->!e.isEmpty());
	}
	
	public static <T> boolean elementsOfCollectionAreValid(Collection<T> c, Predicate<T> p) {
		return c.stream().allMatch(p);
	}
	
	public static <T> boolean objectsAreValid(Predicate<T> p,T...ts) {
		return Stream.of(ts).allMatch(p);
	}
	
	public static <T,C extends Collection<T>> Collection<T> filterCollection(Collection<T> c, Predicate<T> p, Supplier<C> s){
		return c.stream().
				filter(p).
				collect(Collectors.toCollection(s));
	}
}
