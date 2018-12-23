package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Game;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DebugInfo extends TextModel {

    public DebugInfo() {
        font = Font.font(20);
        fillColor = Color.WHITE;
        strokeColor = Color.TRANSPARENT;
        x = 10;
        y = Game.getInstance().getRender().getHeight() - 50;
    }

    @Override
    public void update() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
