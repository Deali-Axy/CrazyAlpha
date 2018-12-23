package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Game;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreIndicator extends TextModel {
    private Logger logger = LogManager.getLogger(ScoreIndicator.class);

    private int score;
    private int levelCounter;

    public ScoreIndicator() {
        font = Game.getInstance().getResouceManager().getFont("Starcraft", 30);
        fillColor = Color.WHITE;
        strokeColor = Color.TRANSPARENT;
        x = 170;
        y = 100;
    }

    @Override
    public void update() {
        text = "Score: " + score;
        // 每50分切换地图
        int tempCounter = levelCounter - 50;
        if (tempCounter > 0 && tempCounter <= 10) {
            Game.getInstance().getMapManager().next();
            levelCounter = tempCounter;
            logger.info("关卡切换 tempCounter={}", tempCounter);
        }
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
        score = 0;
        levelCounter = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void hint(Alpha alpha) {
        CenterArc centerArc = (CenterArc) Game.getInstance().getModelManager().get("CenterArc");
        double distance = Math.sqrt(Math.pow(alpha.getX() - centerArc.getX(), 2) + Math.pow(alpha.getY() - centerArc.getY(), 2));
        Game.getInstance().debug("distance={}", distance / 100);
        score += distance / 100;
        levelCounter += score;
    }

    public void miss() {

    }
}
