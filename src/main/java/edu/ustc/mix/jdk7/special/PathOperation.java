package edu.ustc.mix.jdk7.special;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathOperation {
	
	public static void main(String[] args) {
		
		Path absolute = Paths.get("/", "tmp", "jdk");
		System.out.println(absolute);
		
		Path relative = Paths.get("config", "user.properties");
		System.out.println(relative);
		
		Path homeDirectory = Paths.get("/home/jdk");
		System.out.println(homeDirectory);
		
		Path configPath = homeDirectory.resolve("config/user.properties");
		System.out.println(configPath);
		
		Path workPath = Paths.get("/home/jdk/config");
		Path tempPath = workPath.resolveSibling("tmp");
		System.out.println(tempPath);
		
		System.out.println(Paths.get("/home/usr").relativize(Paths.get("/home/jdk/tmp")));
		System.out.println(Paths.get("/home/jdk/../tmp/./config").normalize());
		
		Path p = Paths.get("/home", "jdk", "config", "user.properties");
		Path parent = p.getParent(); // the path /home/jdk/config
		System.out.println(parent);
		
		Path file = p.getFileName(); // the path user.properties
		System.out.println(file);
		
		Path root = p.getRoot(); // the path /
		System.out.println(root);
		System.out.println(file.getRoot());
	}
}
