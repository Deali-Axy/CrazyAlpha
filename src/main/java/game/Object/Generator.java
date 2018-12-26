package game.Object;

import game.Engine.Base.BaseModel;
import game.Engine.Enum.ObjectStatus;
import game.Engine.GameObject;
import game.Game;
import game.Object.Model.Alpha;
import game.Object.Model.ScoreIndicator;

import java.util.concurrent.ThreadLocalRandom;

@game.Engine.Annotation.GameObject
public class Generator extends GameObject {
    private long lastTime = 0;
    private long interval = 1000;  // 发射字母的时间间隔
    private int maxAlphaCount = 8;
    private int currentAlphaCount = 0;

    @Override
    public void update() {
        if (status != ObjectStatus.ANIMATION)
            return;

        // 判断是否到达发射时间
        if (System.currentTimeMillis() - lastTime < interval)
            return;

        // 判断是否达到最大限制
        if (getAnimationAlphaCount() >= maxAlphaCount)
            return;

        ScoreIndicator scoreIndicator = ((ScoreIndicator) Game.getInstance().getModelManager().get("ScoreIndicator"));
        interval -= scoreIndicator.getScore() / 10;

        String rndAlpha = getRandomAlpha();
        if (Game.getInstance().getModelManager().contains(rndAlpha)) {
            BaseModel alpha = Game.getInstance().getModelManager().get(rndAlpha);
            if (alpha.getStatus() != ObjectStatus.ANIMATION)
                alpha.start();
        } else {
            Game.getInstance().getModelManager().register(new Alpha(rndAlpha), rndAlpha);
        }

        lastTime = System.currentTimeMillis();
    }

    @Override
    public void start() {
        status = ObjectStatus.ANIMATION;
    }

    @Override
    public void stop() {
        status = ObjectStatus.STOP;
    }

    /**
     * 获取随机字母
     *
     * @return
     */
    private String getRandomAlpha() {
        int rnd = ThreadLocalRandom.current().nextInt(65, 90);
        return String.valueOf((char) rnd);
    }

    /**
     * 获取当前正在屏幕上运动的字母数量
     *
     * @return 数量
     */
    private int getAnimationAlphaCount() {
        int result = 0;
        for (BaseModel model : Game.getInstance().getModelManager().getAllModels()) {
            if (model instanceof Alpha) {
                if (model.getStatus() == ObjectStatus.ANIMATION)
                    result++;
            }
        }
        return result;
    }

    /**
     * 重置字母生成器
     */
    public void reset() {
        lastTime = 0;
        interval = 1000;
        currentAlphaCount = 0;
        for (BaseModel model : Game.getInstance().getModelManager().getAllModels()) {
            if (model instanceof Alpha)
                ((Alpha) model).stop(false);
        }
    }
}
