package edu.ustc.mix.jdk7.special;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

// 其它jdk7新语言特性：字符串switch语句、二进制数字表示、下滑线数字表示、改进的类型推断、
public class TryWithResources {
	
	public static final String FILE_PATH_CITIES = "/Users/workspace-mars/mix-jdk/src/main/java/edu/ustc/mix/stream/cities.txt";
	
	public static void main(String[] args) {
		
		try {
			int count = 0;
			
			// 自动关闭实现了AutoCloseable接口的资源
			try (Scanner in = new Scanner(Paths.get(FILE_PATH_CITIES))) {
				while (in.hasNext() && ++count < 5) {
					System.out.println(in.next().toLowerCase());
				}
			}
			
			try (Scanner in = new Scanner(Paths.get(FILE_PATH_CITIES));
				PrintWriter out = new PrintWriter("/tmp/out.txt")) {
				while (in.hasNext()) {
					out.println(in.next().toLowerCase());
				}
			}
		} catch (IOException ex) { // Separate try-with-resources from try/catch
			ex.printStackTrace();
		}
		
		// This works as it should
		try {
			try (InputStream in = new InputStream() {
				
				public int read() throws IOException {
					throw new IOException("read failed");
				}
				
				public void close() throws IOException {
					throw new IOException("close failed");
				}
			}) {
				System.out.println(in.read());
			}
		} catch (Exception ex) {
			System.out.println(ex);
			Throwable[] secondaryExceptions = ex.getSuppressed();
			System.out.println(Arrays.toString(secondaryExceptions));
		}
		
		// This doesn't since, depressingly, the Scanner, changes the exception 
		// type without also carrying along the suppressed exceptions
		try {
			try (Scanner in = new Scanner(new InputStream() {
				
				public int read() throws IOException {
					System.out.println("reading");
					throw new IOException("read failed");
				}
				
				public void close() throws IOException {
					System.out.println("closing");
					throw new IOException("close failed");
				}
			})) {
				System.out.println(in.next());
			}
		} catch (Exception ex) {
			System.out.println(ex);
			Throwable[] secondaryExceptions = ex.getSuppressed();
			System.out.println(Arrays.toString(secondaryExceptions));
		}
		
		// As you can see, the read and close method got called, and both
		// threw exceptions, but both the read and the close exception
		// are still lost. The scanner takes it upon itself to catch
		// exceptions from the underlying stream without linking them,
		// and then it throws a different exception
	}
}
