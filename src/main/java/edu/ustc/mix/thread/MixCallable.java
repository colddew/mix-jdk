package edu.ustc.mix.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class MixCallable implements Callable<AtomicInteger> {
	
	private int taskNo;
	
	public MixCallable(int taskNo) {
		this.taskNo = taskNo;
	}
	
	@Override
	public AtomicInteger call() throws Exception {
		
		System.out.println("task " + taskNo + " start...");
		
		long start = System.currentTimeMillis();
		Thread.sleep(1000 * 5);
		long cost = System.currentTimeMillis() - start;
		
		System.out.println("task " + taskNo + " end...");
		System.out.println("task " + taskNo + " cost " + cost + " ms");
		
		return new AtomicInteger(new Random().nextInt(100));
	}
}
