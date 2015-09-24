package edu.ustc.mix.miscellaneous;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64Handler {
	
	public static void main(String[] args) throws IOException {
		
		String username = "fred";
		String password = "secret";
		String original = username + ":" + password;
		
		Base64.Encoder encoder = Base64.getEncoder();
		String encoded = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoded);
		
		String decoded = new String(Base64.getDecoder().decode(encoded.getBytes()));
		System.out.println(decoded);
		
		Path originalPath = Paths.get("/Users/workspace-mars/mix-jdk/src/main/java/edu/ustc/mix/miscellaneous/Base64Handler.java"); 
		Path encodedPath = Paths.get("/Users/workspace-mars/mix-jdk/src/main/java/edu/ustc/mix/miscellaneous/Base64Handler.java.base64");
		encoder = Base64.getMimeEncoder();
		try (OutputStream output = Files.newOutputStream(encodedPath)) {
			Files.copy(originalPath, encoder.wrap(output));
		}
		
		Path decodedPath = Paths.get("/Users/workspace-mars/mix-jdk/src/main/java/edu/ustc/mix/miscellaneous/Base64Handler.java.decoded");
		Base64.Decoder decoder = Base64.getMimeDecoder();
		try (InputStream input = Files.newInputStream(encodedPath)) {
			Files.copy(decoder.wrap(input), decodedPath);
		}
	}
}
