package edu.ustc.mix.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MixExecutor {
	
	public static final Integer THREAD_POOL_SIZE = 5;
	
	public static void execute() throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		for (int i = 0; i < 10; i++) {
			
			MixCallable callable = new MixCallable(i);
			Future<Object> future = pool.submit(callable);
			
			System.out.println(future.get().toString());
		}
		
		pool.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		execute();
	}
}
