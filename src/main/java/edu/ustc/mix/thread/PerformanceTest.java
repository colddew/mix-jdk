package edu.ustc.mix.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceTest {
	
	public static void serialize() throws Exception {
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			Thread.sleep(100);
		}
		
		System.out.println("serialize cost " + (System.currentTimeMillis() - start) + " ms");
	}
	
	public static void parallel() throws Exception {
		
		long start = System.currentTimeMillis();
		
		ExecutorService pool = Executors.newFixedThreadPool(20);
		
		for (int i = 0; i < 100; i++) {
			
			Runnable task = new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			
			pool.submit(task);
		}
		
		pool.shutdown();
		
		pool.awaitTermination(10, TimeUnit.SECONDS);
		
		System.out.println("serialize cost " + (System.currentTimeMillis() - start) + " ms");
	}
	
	public static void main(String[] args) throws Exception {
		
//		serialize();
		
		parallel();
	}
}
