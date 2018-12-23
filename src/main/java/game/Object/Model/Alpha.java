package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Engine.Enum.ObjectStatus;
import game.Engine.Utils.RandomColor;
import game.Engine.Utils.RandomEx;
import game.Game;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Alpha extends TextModel {
    private Logger logger = LogManager.getLogger(Alpha.class);

    private int maxFontSize = 100;

    public Alpha(String text) {
        this.text = text;

        x = Game.getInstance().getRender().getWidth() / 2;
        y = Game.getInstance().getRender().getHeight() / 2;

        // 开始动画
        start();
    }

    @Override
    public void update() {
        if (status != ObjectStatus.ANIMATION)
            return;

        // 字体逐渐变大
        if (font.getSize() < maxFontSize)
            setFontSize(font.getSize() + 0.4);

        switch (horizontalMotion) {
            case 1:
                x += horizontalSpeed + horizontalAcceleration;
                if (x > Game.getInstance().getRender().getWidth())
                    stop(true);
                break;
            case -1:
                x -= horizontalSpeed + horizontalAcceleration;
                if (x < 0)
                    stop(true);
                break;
        }

        switch (verticalMotion) {
            case 1:
                y += verticalSpeed + verticalAcceleration;
                if (y > Game.getInstance().getRender().getHeight())
                    stop(true);
                break;
            case -1:
                y -= verticalSpeed + verticalAcceleration;
                if (y < 0)
                    stop(true);
                break;
        }

        logger.debug("%s x=%f y=%f speed=%f\n", this.hashCode(), x, y, motionSpeed);
    }

    public void setFontSize(double size) {
        font = Game.getInstance().getResouceManager().getFont("Starcraft", size);
    }

    /**
     * change alpha color
     */
    public void changeColor() {
        fillColor = RandomColor.next();
        strokeColor = RandomColor.next();
    }

    /**
     * 随机选择一个运动方向，开始动画
     */
    @Override
    public void start() {
        changeColor();
        status = ObjectStatus.ANIMATION;
        // 获取当前分数
        int score = ((ScoreIndicator) Game.getInstance().getModelManager().get("ScoreIndicator")).getScore();
        // 分数加速度，分数 / 100
        double scoreAcceleration = score / 100.0;
        // 生成随机速度
        horizontalSpeed = RandomEx.nextDouble();
        verticalSpeed = RandomEx.nextDouble();
        horizontalAcceleration = RandomEx.nextDouble() + scoreAcceleration;
        verticalAcceleration = RandomEx.nextDouble() + scoreAcceleration;

        font = Font.font(1);

        if (RandomEx.nextInt(10) < 6)
            horizontalMotion = 1;
        else
            horizontalMotion = -1;

        if (RandomEx.nextInt(10) < 6)
            verticalMotion = 1;
        else
            verticalMotion = -1;
    }

    /**
     * 动画停止，并且设置为不可见状态
     */
    @Override
    public void stop() {
        stop(false);
    }

    public void stop(boolean isMiss) {
        x = Game.getInstance().getRender().getWidth() / 2;
        y = Game.getInstance().getRender().getHeight() / 2;
        horizontalMotion = 0;
        verticalMotion = 0;
        this.status = ObjectStatus.INVISIBLE;

        if (isMiss)
            ((MissIndicator) Game.getInstance().getModelManager().get("MissIndicator")).miss();
    }

    public int getMaxFontSize() {
        return maxFontSize;
    }

    public void setMaxFontSize(int maxFontSize) {
        this.maxFontSize = maxFontSize;
    }
}
