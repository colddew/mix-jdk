package edu.ustc.mix.composite;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionHandle {
	
	public static void doInOrder(Runnable first, Runnable second, Consumer<Throwable> handler) {
		
		Thread t = new Thread() {
			public void run() {
				try {
					first.run();
					second.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		
		t.start();
	}
	
	public static <T> Supplier<T> unchecked(Callable<T> f) {
		return () -> {
			try {
				return f.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable t) {
				throw t;
			}
		};
	}
	
	public static void main(String[] args) {
		
		doInOrder(() -> System.out.println(args[0]), () -> System.out.println("Goodbye"),
			(e) -> System.out.println("Yikes: " + e));
		
		unchecked(() -> new String(Files.readAllBytes(Paths.get("/etc/passwd")), 
			StandardCharsets.UTF_8));
	}
}
