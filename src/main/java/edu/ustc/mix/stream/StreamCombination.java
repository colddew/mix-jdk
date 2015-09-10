package edu.ustc.mix.stream;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamCombination {
	
	public static void main(String[] args) throws IOException {
		
		Stream<Double> randoms = Stream.generate(Math::random).limit(100);
		StreamUtils.show("randoms", randoms);
		
		Stream<Integer> integers = Stream.iterate(0, n -> n + 1);
		Stream<Integer> firstFive = integers.limit(5);
		StreamUtils.show("firstFive", firstFive);
		
		integers = Stream.iterate(0, n -> n + 1);
		Stream<Integer> notTheFirst = integers.skip(1);
		StreamUtils.show("notTheFirst", notTheFirst);
		
		Stream<Character> combined = Stream.concat(StreamUtils.characterStream("Hello"), 
			StreamUtils.characterStream("World"));
		StreamUtils.show("combined", combined);
		
		Object[] powers = Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println("Fetching " + e))
			.limit(10).toArray();
		System.out.println(Arrays.asList(powers));
	}
}
