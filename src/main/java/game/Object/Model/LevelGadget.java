package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Engine.Enum.ObjectStatus;
import game.Engine.Utils.RandomColor;
import game.Game;
import javafx.scene.paint.Color;

public class LevelGadget extends TextModel {
    public LevelGadget() {
        font = Game.getInstance().getResouceManager().getFont("Starcraft", 30);
        fillColor = RandomColor.next();
        strokeColor = Color.TRANSPARENT;
        verticalSpeed = 2;
    }

    @Override
    public void update() {
        if (status != ObjectStatus.ANIMATION)
            return;

        y -= verticalSpeed;
        if (y < 0)
            stop();
    }

    @Override
    public void start() {
        status = ObjectStatus.ANIMATION;
    }

    @Override
    public void stop() {
        status = ObjectStatus.INVISIBLE;
    }
}
