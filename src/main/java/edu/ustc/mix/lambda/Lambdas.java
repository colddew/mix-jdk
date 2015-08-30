package edu.ustc.mix.lambda;

import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * lambda表达式是一个带有参数的代码块
 * 
 * 以多线程、排序、按钮事件等为例
 */
@SuppressWarnings("restriction")
public class Lambdas {
	
	@SuppressWarnings({ "unused" })
	public static void main(String[] args) {
		
		
		Comparator<String> comp = (String first, String second) -> Integer.compare(first.length(), second.length());
		
		comp = (String first, String second) -> {
			if (first.length() < second.length()) {
				return -1;
			} else if (first.length() > second.length()) {
				return 1;
			} else {
				return 0;
			}
		};
		
		Runnable runner = () -> {
			for (int i = 0; i < 1000; i++) {
				doWork();
			}
		};
		
		// Same as (String first, String second)
		comp = (first, second) -> Integer.compare(first.length(), second.length());
		
		// Instead of (e) -> or (ActionEvent e) ->
		EventHandler<ActionEvent> listener = e -> System.out.println(e.getTarget());
	}
	
	public static void doWork() {
		System.out.println("Doing work...");
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}