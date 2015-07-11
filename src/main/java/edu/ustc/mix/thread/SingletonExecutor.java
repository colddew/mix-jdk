package edu.ustc.mix.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SingletonExecutor {
	
	private static final SingletonExecutor executor = new SingletonExecutor();
	
	private ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
	
	private SingletonExecutor() {
		
	}
	
	public static SingletonExecutor getInstance() {
		return executor;
	}
	
	public void execute() throws Exception {
		
		for (int i = 0; i < 100; i++) {
			pool.submit(new MixRunnable(i));
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		SingletonExecutor instance = SingletonExecutor.getInstance();
		System.out.println(instance.pool);
		
		instance = SingletonExecutor.getInstance();
		System.out.println(instance.pool);
		
		//TODO print consume time log
	}
}
