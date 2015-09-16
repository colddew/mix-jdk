package edu.ustc.mix.composite;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReturnFunction extends Application {
	
	public static UnaryOperator<Color> brighten(double factor) {
		return c -> c.deriveColor(0, 1, factor, 1);
	}
	
	public void start(Stage stage) {
		
		Image image = new Image(CompositeUtils.IMAGE_PATH_QUEEN);
		Image brightenedImage = CompositeUtils.transform(image, brighten(1.2));
		
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(brightenedImage))));
		stage.show();
	}
}
