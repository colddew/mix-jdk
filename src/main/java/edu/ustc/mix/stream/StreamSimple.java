package edu.ustc.mix.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StreamSimple {
	
	public static void main(String[] args) throws IOException {
		
		String contents = new String(Files.readAllBytes(Paths.get(StreamUtils.FILE_PATH_ALICE)), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		
		long count = 0;
		for (String w : words) {
			if (w.length() > 12)
				count++;
		}
		System.out.println(count);
		
		count = words.stream().filter(w -> w.length() > 12).count();
		System.out.println(count);
		
		count = words.parallelStream().filter(w -> w.length() > 12).count();
		System.out.println(count);
	}
}
