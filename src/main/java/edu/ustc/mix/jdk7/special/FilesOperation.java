package edu.ustc.mix.jdk7.special;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

// Files类默认使用UTF-8编码
public class FilesOperation {
	
	public static final String FILE_IN = "/Users/workspace-mars/mix-jdk/src/main/java/edu/ustc/mix/jdk7/special/FilesOperation.java";
	public static final String FILE_OUT = "/tmp/jdk7/FilesOperation.out";
	public static final String FILE_OUT2 = "/tmp/jdk7/FilesOperation2.out";
	public static final String FILE_OUT3 = "/tmp/jdk7/FilesOperation3.out";
	public static final String FILE_OUT4 = "/tmp/jdk7/FilesOperation4.out";
	public static final String FILE_OUT5 = "/tmp/jdk7/FilesOperation5.out";
	public static final String FILE_OUT6 = "/tmp/jdk7/FilesOperation6.out";
	
	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get(FILE_IN);
		byte[] bytes = Files.readAllBytes(path);
		String content = new String(bytes, StandardCharsets.UTF_8);
		System.out.println(content.substring(0, 100) + "...");
		
		List<String> lines = Files.readAllLines(path);
		System.out.println("Last line: " + lines.get(lines.size() - 1));
		
		path = Paths.get(FILE_OUT);
		content = content.replaceAll("e", "x");
		Files.write(path, content.getBytes(StandardCharsets.UTF_8));
		
		path = Paths.get(FILE_OUT2);
		Files.write(path, lines);
		
		Collections.reverse(lines);
		Files.write(path, lines, StandardOpenOption.APPEND);
		
		boolean deleted = Files.deleteIfExists(Paths.get(FILE_OUT3));
		System.out.println(deleted);
		
		URL url = new URL("http://horstmann.com");
		Files.copy(url.openStream(), Paths.get(FILE_OUT3));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Files.copy(Paths.get(FILE_OUT3), out);
		System.out.println(out.toString("UTF-8").substring(0, 100) + "...");
		
		Files.copy(Paths.get(FILE_OUT3), Paths.get(FILE_OUT4));
		Files.move(Paths.get(FILE_OUT4), Paths.get(FILE_OUT5));
		
		// 覆盖已经存在的目标文件，复制所有的文件属性
		Files.copy(Paths.get(FILE_OUT3), Paths.get(FILE_OUT5), StandardCopyOption.REPLACE_EXISTING,
			StandardCopyOption.COPY_ATTRIBUTES);
		
		// 原子的方式移动文件，要么移动成功，要么原文件依然存在
		Files.move(Paths.get(FILE_OUT5), Paths.get(FILE_OUT6), StandardCopyOption.ATOMIC_MOVE);
		
		Files.delete(Paths.get(FILE_OUT6));
		
		Path newPath = Files.createTempFile(null, ".txt");
		System.out.println(newPath);
		
		newPath = Files.createTempDirectory("fred");
		System.out.println(newPath);
	}
}
