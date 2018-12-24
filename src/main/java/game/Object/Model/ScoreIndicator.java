package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Engine.Utils.RandomColor;
import game.Game;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreIndicator extends TextModel {
    private Logger logger = LogManager.getLogger(ScoreIndicator.class);

    private int score;
    // 当前关卡
    private int level = 1;

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
        // 第二关20分，第三关40分，第四关60分
        if (score > level * 20) {
            Game.getInstance().getMapManager().next();
            level++;
            ((LevelIndicator) Game.getInstance().getModelManager().get("LevelIndicator")).nexxtLevel();

            // 增加HP
            HPIndicator hpIndicator = (HPIndicator) Game.getInstance().getModelManager().get("HPIndicator");
            hpIndicator.setMaxHP(hpIndicator.getHP() + level * 2);

            // 显示增加HP动画
            CenterArc centerArc = (CenterArc) Game.getInstance().getModelManager().get("CenterArc");
            HPGadget hpGadget = (HPGadget) Game.getInstance().getModelManager().get("HPGadget");
            hpGadget.setDestinationX(hpIndicator.getX());
            hpGadget.setDestinationY(hpIndicator.getY());
            hpGadget.setX(centerArc.getX());
            hpGadget.setY(centerArc.getY());
//            hpGadget.setFillColor(RandomColor.next());
            hpGadget.setText("HP +" + level * 2);
            hpGadget.start();

            logger.debug("关卡切换 level={}", level);
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
        level = 1;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int hint(Alpha alpha) {
        CenterArc centerArc = (CenterArc) Game.getInstance().getModelManager().get("CenterArc");
        double distance = Math.sqrt(Math.pow(alpha.getX() - centerArc.getX(), 2) + Math.pow(alpha.getY() - centerArc.getY(), 2));
        Game.getInstance().debug("distance={}", distance / 100);
        double gainScore = distance / 100;
        if (gainScore < 1)
            gainScore = 1;
        score += gainScore;
        return (int) gainScore;
    }

    public void miss() {

    }
}
