package edu.ustc.mix.time;

import java.time.LocalTime;

public class LocalTimes {
	
	public static void main(String[] args) {
		
		LocalTime rightNow = LocalTime.now();
		System.out.println("rightNow: " + rightNow);
		
		LocalTime bedtime = LocalTime.of(22, 30);
		bedtime = LocalTime.of(22, 30, 0);
		System.out.println("bedtime: " + bedtime);
		
		LocalTime wakeup = bedtime.plusHours(8); // wakeup is 6:30
		System.out.println("wakeup: " + wakeup);
	}
}
