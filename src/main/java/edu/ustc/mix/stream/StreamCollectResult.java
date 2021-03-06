package edu.ustc.mix.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectResult {
	
	public static Stream<String> noVowels(String filename) throws IOException {
		
		String contents = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> words = wordList.stream();
		
		return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
	}
	
	public static <T> void show(String label, Set<T> set) {
		System.out.print(label + ": " + set.getClass().getName());
		System.out.println("[" + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(", ")) + "]");
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		
		Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
		System.out.println(numbers); // Note it's an Object[] array
		
		try {
			Integer number = (Integer) numbers[0]; // OK
			System.out.println("number: " + number);
			Integer[] numbers2 = (Integer[]) numbers; // Throws exception
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		}
		
		Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
		System.out.println(numbers3); // Note it's an Integer[] array
		
		HashSet<String> noVowelHashSet = noVowels(StreamUtils.FILE_PATH_ALICE).collect(HashSet::new, HashSet::add, HashSet::addAll);
		show("noVowelHashSet", noVowelHashSet);
		
		Set<String> noVowelSet = noVowels(StreamUtils.FILE_PATH_ALICE).collect(Collectors.toSet());
		show("noVowelSet", noVowelSet);
		
		TreeSet<String> noVowelTreeSet = noVowels(StreamUtils.FILE_PATH_ALICE).collect(Collectors.toCollection(TreeSet::new));
		show("noVowelTreeSet", noVowelTreeSet);
		
		String result = noVowels(StreamUtils.FILE_PATH_ALICE).limit(10).collect(Collectors.joining());
		System.out.println(result);
		
		result = noVowels(StreamUtils.FILE_PATH_ALICE).limit(10).collect(Collectors.joining(", "));
		System.out.println(result);
		
		IntSummaryStatistics summary = noVowels(StreamUtils.FILE_PATH_ALICE).collect(Collectors.summarizingInt(String::length));
		double averageWordLength = summary.getAverage();
		System.out.println("Average word length: " + averageWordLength);
		double maxWordLength = summary.getMax();
		System.out.println("Max word length: " + maxWordLength);
		noVowels(StreamUtils.FILE_PATH_ALICE).limit(10).forEach(System.out::println);
	}
}
