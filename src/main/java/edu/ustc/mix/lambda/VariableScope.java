package edu.ustc.mix.lambda;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 含有自由变量（不是参数，没有在代码中定义的变量）的代码块称为闭包
 */
public class VariableScope {
	
	public static void main(String[] args) {
		repeatMessage("Hello", 100);
	}
	
	public static void repeatMessage(String text, int count) {
		Runnable r = () -> {
			for (int i = 0; i < count; i++) {
				System.out.println(text);
				Thread.yield();
			}
		};
		new Thread(r).start();
	}
	
	public static void repeatMessage2(String text, int count) {
		Runnable r = () -> {
			while (count > 0) {
				// count--; // Error: Can't mutate captured variable
				System.out.println(text);
				Thread.yield();
			}
		};
		new Thread(r).start();
	}
	
	@SuppressWarnings("unused")
	public static void countMatches(Path dir, String word) throws IOException {
		Path[] files = getDescendants(dir);
		int matches = 0;
		for (Path p : files)
			new Thread(() -> {
				if (contains(p, word)) {
					// matches++;
					// ERROR: Illegal to mutate matches
				}
			}).start();
	}
	
	private static int matches;
	
	public static void countMatches2(Path dir, String word) {
		Path[] files = getDescendants(dir);
		for (Path p : files)
			new Thread(() -> {
				if (contains(p, word)) {
					matches++;
					// CAUTION: Legal to mutate matches, but not threadsafe
				}
			}).start();
	}
	
	// Warning: Bad code ahead
	public static List<Path> collectMatches(Path dir, String word) {
		Path[] files = getDescendants(dir);
		List<Path> matches = new ArrayList<>();
		for (Path p : files)
			new Thread(() -> {
				if (contains(p, word)) {
					matches.add(p);
					// CAUTION: Legal to mutate matches, but not threadsafe
				}
			}).start();
		return matches;
	}
	
	public static Path[] getDescendants(Path dir) {
		try {
			try (Stream<Path> entries = Files.walk(dir)) {
				return entries.toArray(Path[]::new);
			}
		} catch (IOException ex) {
			return new Path[0];
		}
	}
	
	public static boolean contains(Path p, String word) {
		try {
			return new String(Files.readAllBytes(p), StandardCharsets.UTF_8).contains(word);
		} catch (IOException ex) {
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	public static void avoidDuplicateVariable() {
		
		// Path first = Paths.get("/usr/bin");
		// Uncomment to see error "variable first is already defined"
		// in the lambda expression below
		
		Comparator<String> comp = (first, second) -> Integer.compare(first.length(), second.length());
	}
	
	public void doWork() {
		Runnable runner = () -> {
			System.out.println(this.toString());
		};
		runner.run();
		// Prints Application@... since this refers to an Application object
	}
}
