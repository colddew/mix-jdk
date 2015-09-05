package edu.ustc.mix.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamGeneration {
	
	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("/Users/workspace-mars/mix-jdk/src/main/java/edu/ustc/mix/stream", "alice.txt");
		String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		
		Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));
		StreamUtils.show("words", words);
		
		Stream<String> song = Stream.of("gently", "down", "the", "stream");
		StreamUtils.show("song", song);
		
		Stream<String> silence = Stream.empty();
		silence = Stream.<String> empty(); // Explicit type specification
		StreamUtils.show("silence", silence);
		
		Stream<String> echos = Stream.generate(() -> "Echo");
		StreamUtils.show("echos", echos);
		
		Stream<Double> randoms = Stream.generate(Math::random);
		StreamUtils.show("randoms", randoms);
		
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
		StreamUtils.show("integers", integers);
		
		Stream<String> wordsAnotherWay = Pattern.compile("[\\P{L}]+").splitAsStream(contents);
		StreamUtils.show("wordsAnotherWay", wordsAnotherWay);
		
		// try-with-resources
		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			StreamUtils.show("lines", lines);
		}
	}
}
