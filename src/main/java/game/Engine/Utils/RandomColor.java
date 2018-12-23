package game.Engine.Utils;

import javafx.scene.paint.Color;

import java.util.Random;

public class RandomColor {
    public static Color next() {
        Random random = new Random(System.currentTimeMillis());
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }
}
