package game.Engine.Utils;

import javafx.scene.paint.*;

import java.util.Random;

public class RandomColor {
    public static Color next() {
        Random random = new Random(System.currentTimeMillis() + (long) (Math.random() * 1000));
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    /**
     * 彩虹渐变色
     *
     * @return
     */
    public static Paint rainbow() {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT,
                new Stop(0, next()),
                new Stop(0.5f, next()),
                new Stop(1f, next()));
        return gradient;
    }
}
