package edu.ustc.mix.lambda;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 传递已有方法等引用的方式（对象::实例方法、类::静态方法、类::实例方法）
 */
public class MethodReferences extends Application {
	
	public void start(Stage stage) {
		
		String[] strings = "Mary had a little lamb".split(" ");
		Arrays.sort(strings, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(strings));
		
		Button button = new Button("Click me!");
		button.setOnAction(System.out::println);
		stage.setScene(new Scene(button));
		stage.show();
	}
	
	public static void main(String[] args) {
		
		class Greeter {
			public void greet() {
				System.out.println("Hello, world!");
			}
		}
		
		class ConcurrentGreeter extends Greeter {
			public void greet() {
				Thread t = new Thread(super::greet);
				t.start();
			}
		}
		
		new ConcurrentGreeter().greet();
	}
}
