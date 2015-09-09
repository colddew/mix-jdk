package edu.ustc.mix.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamStatusTransformation {
	
	public static void main(String[] args) throws IOException {
		
		Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
		StreamUtils.show("uniqueWords", uniqueWords);
		
		String contents = new String(Files.readAllBytes(Paths.get(StreamUtils.FILE_PATH)), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> words = wordList.stream();
		StreamUtils.show("words", words);
		
		words = wordList.stream();
		Stream<String> distinct = words.distinct();
		StreamUtils.show("distinct", distinct);
		
		words = wordList.stream();
		Stream<String> sorted = words.sorted();
		StreamUtils.show("sorted", sorted);
		
		words = wordList.stream();
		Stream<String> distinctSorted = words.distinct().sorted();
		StreamUtils.show("distinctSorted", distinctSorted);
		
		words = wordList.stream();
		Stream<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed());
		StreamUtils.show("longestFirst", longestFirst);
	}
}
