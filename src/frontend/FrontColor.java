package frontend;

import backend.RGBColor;
import javafx.scene.paint.Color;

public class FrontColor extends RGBColor {

    public FrontColor(double red, double green, double blue) {
        super(red, green, blue);
    }

    public static Color RGBtoColor(RGBColor color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), 1);
    }
}
