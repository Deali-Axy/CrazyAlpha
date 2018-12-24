package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Engine.Enum.ObjectStatus;
import game.Game;
import javafx.scene.paint.Color;

/**
 * 在命中后显示 +2 +5 等效果
 */
public class ScoreGadget extends TextModel {
    public ScoreGadget() {
        font = Game.getInstance().getResouceManager().getFont("Starcraft", 20);
        fillColor = Color.WHITE;
        strokeColor = Color.TRANSPARENT;
        verticalSpeed = 0;
    }

    @Override
    public void update() {
        if (status!=ObjectStatus.ANIMATION)
            return;

        verticalSpeed += 0.2;
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
