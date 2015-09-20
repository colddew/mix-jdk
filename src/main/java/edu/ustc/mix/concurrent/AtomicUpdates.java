package edu.ustc.mix.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicUpdates {
	
	public static int ntasks = 1000;
	public static int iterations = 1000;

	public static AtomicLong nextNumber = new AtomicLong(ntasks * iterations / 2);
	public static AtomicLong largest = new AtomicLong();

	public static void main(String[] args) throws InterruptedException {
//		update();
//		update2();
		update3();
	}

	public static void update() throws InterruptedException {
		
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int t = 0; t < ntasks; t++) {
			pool.submit(() -> {
				
				long start = nextNumber.incrementAndGet();
				
				for (int i = 0; i < iterations; i++) {
					long observed = (start + ntasks * i) % (ntasks * iterations);
					// Error—race condition!
					largest.set(Math.max(largest.get(), observed));
				}
			});
		}
		
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println(largest);
		// Should be 999999, but ever so often, it isn't
	}
	
	public static void update2() throws InterruptedException {
		
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int t = 0; t < ntasks; t++) {
			pool.submit(() -> {
				
				long start = nextNumber.incrementAndGet();
				
				for (int i = 0; i < iterations; i++) {
					
					long observed = (start + ntasks * i) % (ntasks * iterations);
					long oldValue, newValue;
					
					do {
						oldValue = largest.get();
						newValue = Math.max(oldValue, observed);
					} while (!largest.compareAndSet(oldValue, newValue));
				}
			});
		}
		
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println(largest);
	}
	
	public static void update3() throws InterruptedException {
		
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int t = 0; t < ntasks; t++) {
			pool.submit(() -> {
				
				long start = nextNumber.incrementAndGet();
				
				for (int i = 0; i < iterations; i++) {
					long observed = (start + ntasks * i) % (ntasks * iterations);
					// largest.updateAndGet(x -> Math.max(x, observed));
					largest.accumulateAndGet(observed, Math::max);
				}
			});
		}
		
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println(largest);
	}
}
