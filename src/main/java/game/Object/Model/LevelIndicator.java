package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Engine.Enum.ObjectStatus;
import game.Game;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class LevelIndicator extends TextModel {
    private int level = 1;
    // 上次显示时刻
    private long lastShow = 0;
    // 显示时间
    private long showTime = 1000;

    public LevelIndicator() {
        font = Game.getInstance().getResouceManager().getFont("Starcraft", 40);
        status = ObjectStatus.ANIMATION;
        fillColor = Color.WHITE;
        strokeColor = Color.TRANSPARENT;
        x = Game.getInstance().getWidth() / 2 - 50;
        y = 50;
    }

    @Override
    public void update() {
        if (status != ObjectStatus.ANIMATION)
            return;

//        if (System.currentTimeMillis() - lastShow > showTime)
//            stop();

        text = "Level " + level;
    }

    @Override
    public void start() {
        status = ObjectStatus.ANIMATION;
        lastShow = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        status = ObjectStatus.STOP;
    }

    public void nexxtLevel() {
        level++;

        // 显示升级动画
        LevelGadget levelGadget = (LevelGadget) Game.getInstance().getModelManager().get("LevelGadget");
        CenterArc centerArc=(CenterArc) Game.getInstance().getModelManager().get("CenterArc");
        levelGadget.setX(centerArc.getX());
        levelGadget.setY(centerArc.getY());
        levelGadget.setText("Level +1");
        levelGadget.start();

        start();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
