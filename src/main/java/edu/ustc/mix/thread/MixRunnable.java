package edu.ustc.mix.thread;

public class MixRunnable implements Runnable {
	
	private int taskNo;
	
	public MixRunnable(int taskNo) {
		this.taskNo = taskNo;
	}
	
	@Override
	public void run() {
		
		System.out.println("task " + taskNo + " start...");
		
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long cost = System.currentTimeMillis() - start;
		
		System.out.println("task " + taskNo + " end...");
		System.out.println("task " + taskNo + " cost " + cost + " ms");
	}

}
