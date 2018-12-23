package game.Object.Model;

import game.Engine.Base.ShapeModel;
import game.Engine.Enum.ObjectStatus;
import game.Game;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class CenterArc extends ShapeModel {
    private Logger logger = LogManager.getLogger(CenterArc.class);

    public CenterArc() {
        fillColor = Color.color(Math.random(), Math.random(), Math.random());
        strokeColor = Color.YELLOW;
        width = 120;
        height = 120;
        x = (Game.getInstance().getRender().getWidth() - width) / 2;
        y = (Game.getInstance().getRender().getHeight() - height) / 2;
        for (int i = 0; i < 4; i++) {
            Arc arc = new Arc(x, y, width, height, i * 90, 30);
            arc.setType(ArcType.ROUND);
            shapes.add(arc);
        }
        effect = new GaussianBlur();

        start();
    }

    @Override
    public void update() {
        if (status != ObjectStatus.ANIMATION)
            return;

        for (int i = 0; i < shapes.size(); i++) {
            Arc arc = (Arc) shapes.get(i);
            arc.setStartAngle(arc.getStartAngle() + 8);
        }
    }

    public void changeStrokeColor() {
        strokeColor = Color.color(ThreadLocalRandom.current().nextDouble(1), ThreadLocalRandom.current().nextDouble(1), ThreadLocalRandom.current().nextDouble(1));
    }

    public void changeFillColor() {
        fillColor = Color.color(ThreadLocalRandom.current().nextDouble(1), ThreadLocalRandom.current().nextDouble(1), ThreadLocalRandom.current().nextDouble(1));
    }

    @Override
    public void start() {
        status = ObjectStatus.ANIMATION;
    }

    @Override
    public void stop() {
        status = ObjectStatus.STOP;
    }
}
