package edu.ustc.mix.composite;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@FunctionalInterface
interface ColorTransformer {
	Color apply(int x, int y, Color colorAtXY);
}

public class FuntionalInterface extends Application {
	
	public void start(Stage stage) {
		
		Image image = new Image(CompositeUtils.IMAGE_PATH_QUEEN);
		Image brightenedImage = CompositeUtils.transform(image, Color::brighter);
		Image image2 = CompositeUtils.transform(image, (x, y, c) -> (x / 10) % 2 == (y / 10) % 2 ? Color.GRAY : c);
		
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(brightenedImage), 
			new ImageView(image2))));
		stage.show();
	}
}
