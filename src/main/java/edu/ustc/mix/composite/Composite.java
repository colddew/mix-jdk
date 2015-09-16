package edu.ustc.mix.composite;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Composite extends Application {
	
	public static <T> UnaryOperator<T> compose(UnaryOperator<T> op1, UnaryOperator<T> op2) {
		return t -> op2.apply(op1.apply(t));
	}

	public void start(Stage stage) {
		
		Image image = new Image(CompositeUtils.IMAGE_PATH_EIFFEL);
		// Image image2 = transform(image, Color::brighter);
		// Image image3 = transform(image2, Color::grayscale);
		Image image3 = CompositeUtils.transform(image, compose(Color::brighter, Color::grayscale));
		
		stage.setScene(new Scene(new HBox(new ImageView(image), // new ImageView(image2), 
			new ImageView(image3))));
		stage.show();
	}
}
