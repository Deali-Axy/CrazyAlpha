package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Game;
import javafx.scene.paint.Color;

public class MissIndicator extends TextModel {
    private int miss;
    private int maxHP = 10;

    public MissIndicator() {
        font = Game.getInstance().getResouceManager().getFont("Starcraft", 30);

        fillColor = Color.WHITE;
        strokeColor = Color.TRANSPARENT;
        x = 170;
        y = 150;
    }

    @Override
    public void update() {
        if (getHP() <= 0)
            Game.getInstance().over();

        text = "HP: " + getHP();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    /**
     * 在GAMEOVER之后重置分数
     */
    public void reset() {
        miss = 0;
    }

    /**
     * miss
     *
     * @return 是否游戏结束
     */
    public boolean miss() {
        return maxHP - ++miss <= 0;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHP() {
        return maxHP - miss;
    }
}
