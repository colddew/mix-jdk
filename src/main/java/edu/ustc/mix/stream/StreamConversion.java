package edu.ustc.mix.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamConversion {
	
	public static void main(String[] args) throws IOException {
		
		String contents = new String(Files.readAllBytes(Paths.get(StreamUtils.FILE_PATH_ALICE)), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> words = wordList.stream();
		Stream<String> longWords = words.filter(w -> w.length() > 12);
		StreamUtils.show("longWords", longWords);
		
		words = wordList.stream();
		Stream<String> lowercaseWords = words.map(String::toLowerCase);
		StreamUtils.show("lowercaseWords", lowercaseWords);
		
		Stream<String> song = Stream.of("row", "row", "row", "your", "boat", "gently", "down", "the", "stream");
		Stream<Character> firstChars = song.filter(w -> w.length() > 0).map(s -> s.charAt(0));
		StreamUtils.show("firstChars", firstChars);
		
		song = Stream.of("row", "row", "row", "your", "boat", "gently", "down", "the", "stream");
		Stream<Character> letters = song.flatMap(w -> StreamUtils.characterStream(w));
		StreamUtils.show("letters", letters);
	}
}
