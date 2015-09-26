package edu.ustc.mix.jdk7.special;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ProcessOperation {
	
	public static final String FILE_IN = "/Users/workspace-mars/mix-jdk/src/main/java/edu/ustc/mix/jdk7/special/ProcessOperation.java";
	public static final String FILE_OUT = "/tmp/jdk7/ProcessOperation.out";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ProcessBuilder builder = new ProcessBuilder("grep", "-o", "[A-Za-z_][A-Za-z_0-9]*");
		builder.redirectInput(Paths.get(FILE_IN).toFile());
		builder.redirectOutput(Paths.get(FILE_OUT).toFile());
		
		Process process = builder.start();
		process.waitFor(10, TimeUnit.SECONDS);
		
		builder = new ProcessBuilder("ls", "-al");
		builder.inheritIO();
		builder.start().waitFor();
	}
}
