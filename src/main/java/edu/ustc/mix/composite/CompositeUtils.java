package edu.ustc.mix.composite;

import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class CompositeUtils {
	
	public static final String IMAGE_PATH_QUEEN = "queen-mary.png";
	public static final String IMAGE_PATH_EIFFEL = "eiffel-tower.jpg";
	
	public static Image transform(Image in, UnaryOperator<Color> f) {
		
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
			}
		}
		
		return out;
	}
	
	public static Image transform(Image in, ColorTransformer f) {
		
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
			}
		}
		
		return out;
	}
}
