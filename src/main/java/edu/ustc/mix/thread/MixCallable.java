package edu.ustc.mix.thread;

import java.util.concurrent.Callable;

public class MixCallable implements Callable<Object> {
	
	private int taskNo;
	
	public MixCallable(int taskNo) {
		this.taskNo = taskNo;
	}
	
	@Override
	public Object call() throws Exception {
		
		System.out.println("task " + taskNo + " start...");
		
		long start = System.currentTimeMillis();
		Thread.sleep(1000);
		long cost = System.currentTimeMillis() - start;
		
		System.out.println("task " + taskNo + " end...");
		
		return "task " + taskNo + " cost " + cost + " ms";
	}
}
