package edu.ustc.mix.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 聚合操作为终止操作，当一个流使用了终止操作后不能再进行其它操作
 */
public class StreamSimpleAggregation {
	
	public static void main(String[] args) throws IOException {
		
		String contents = new String(Files.readAllBytes(Paths.get(StreamUtils.FILE_PATH)), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> words = wordList.stream();
		
		Optional<String> largest = words.max(String::compareToIgnoreCase);
		if (largest.isPresent()) {
			System.out.println("largest: " + largest.get());
		}
		
		words = wordList.stream();
		Optional<String> firstWordStartsWithQ = words.filter(s -> s.startsWith("Q")).findFirst();
		System.out.println("firsWordtStartsWithQ: " + firstWordStartsWithQ.get());
		
		words = wordList.stream();
		boolean aWordStartsWithQ = words.parallel().anyMatch(s -> s.startsWith("Q"));
		System.out.println("aWordStartsWithQ: " + aWordStartsWithQ);
		
		words = wordList.stream();
		Optional<String> startsWithQ = words.parallel().filter(s -> s.startsWith("Q")).findAny();
		if (startsWithQ.isPresent()) {
			System.out.println("startsWithQ: " + startsWithQ.get());
		} else {
			System.out.println("No word starts with Q");
		}
	}
}
