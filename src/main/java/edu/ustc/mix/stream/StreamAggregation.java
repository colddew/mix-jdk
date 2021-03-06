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
 * 聚合操作适用于求和、求积、字符串追加、求最大值和最小值、求并集和交集
 */
public class StreamAggregation {
	
	public static void main(String[] args) throws IOException {
		
		Integer[] digits = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6, 4, 3, 3, 8, 3, 2, 7,
			9, 5, 0, 2, 8, 8, 4, 1, 9, 7, 1, 6, 9, 3, 9, 9, 3, 7, 5, 1, 0, 5, 8, 2, 0, 9, 7, 4, 9, 4, 4, 5, 9, 2, 3,
			0, 7, 8, 1, 6, 4, 0, 6, 2, 8, 6 };
		Stream<Integer> values = Stream.of(digits);
		Optional<Integer> sum = values.reduce((x, y) -> x + y);
		System.out.println("sum: " + sum);
		
		values = Stream.of(digits);
		sum = values.reduce(Integer::sum);
		System.out.println("sum: " + sum);
		
		values = Stream.empty();
		sum = values.reduce((x, y) -> x + y); // Or values.reduce(Integer::sum);
		System.out.println("sum: " + sum);
		
		values = Stream.of(digits);
		Integer sum2 = values.reduce(0, (x, y) -> x + y);
		System.out.println("sum2: " + sum2);
		
		values = Stream.empty();
		Integer sum3 = values.reduce(0, (x, y) -> x + y);
		System.out.println("sum3: " + sum3);
		
		String contents = new String(Files.readAllBytes(Paths.get(StreamUtils.FILE_PATH_ALICE)), StandardCharsets.UTF_8);
		List<String> wordList = Arrays.asList(contents.split("[\\P{L}]+"));
		Stream<String> words = wordList.stream();
		int result = words.reduce(0, (s, w) -> s + w.length(), (s1, s2) -> s1 + s2);
		System.out.println("result: " + result);
		
		words = wordList.stream();
		result = words.mapToInt(String::length).sum();
		System.out.println("result: " + result);
	}
}
