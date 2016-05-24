package edu.ustc.mix.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class MixExecutor {
	
	public static final Integer THREAD_POOL_SIZE = 5;
	
	public static void executeRunnable() throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		for(int i = 0; i < THREAD_POOL_SIZE; i++) {
			Runnable task = new MixRunnable(i);
			pool.submit(task);
		}
		
		pool.shutdown();
	}
	
	public static void executeSerialCallable() throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		for (int i = 0; i < 10; i++) {
			
			MixCallable callable = new MixCallable(i);
			Future<AtomicInteger> future = pool.submit(callable);
			
			System.out.println(future.get().toString());
		}
		
		pool.shutdown();
	}
	
	public static void executeParallelCallable() throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		List<Future<AtomicInteger>> futureList = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			MixCallable callable = new MixCallable(i);
			Future<AtomicInteger> f = pool.submit(callable);
			futureList.add(f);
		}
		
		int success = 0;
		
		for(Future<AtomicInteger> future : futureList) {
			AtomicInteger result = future.get();
			System.out.println("########## " + result + " ##########");
			success = success + result.intValue();
		}
		
		System.out.println(success);
		
		pool.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
//		executeRunnable();
//		executeSerialCallable();
		executeParallelCallable();
	}
}
