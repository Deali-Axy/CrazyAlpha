package game;

import game.Engine.Enum.GameStatus;
import game.Manager.*;
import game.Object.Generator;
import game.Object.Model.DebugInfo;
import game.Object.Model.HPIndicator;
import game.Object.Model.ScoreIndicator;
import game.View.HomeView;
import game.View.OverView;
import game.View.PauseView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Game {
    private static Game instance;
    public static final String name = "CRAZY ALPHA!";
    public static final String author = "DealiAxy";
    public static final String version = "1.0.1";
    private Logger logger = LogManager.getLogger(Game.class);
    private AnimationTimer animationTimer;
    private EventManager eventManager = new EventManager();
    private MapManager mapManager = new MapManager();
    private ModelManager modelManager = new ModelManager();
    private ResouceManager resouceManager = new ResouceManager();
    private DataManager dataManager = new DataManager();
    private Render render = new Render();

    private Generator generator;

    private MediaPlayer backgroundMusic;
    private MediaPlayer buttonOverMusic;
    private MediaPlayer buttonClickMusic;
    private MediaPlayer hintMusic;
    private MediaPlayer missMusic;

    private GameStatus status = GameStatus.STOP;
    private int score;  // 上次游戏得分

    private double width;
    private double height;

    // scene
    private Scene gameScene;

    private Game() {
        backgroundMusic = new MediaPlayer(resouceManager.getMedia("background"));
        backgroundMusic.setVolume(0.5);
        backgroundMusic.setAutoPlay(true);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        buttonOverMusic = new MediaPlayer(resouceManager.getMedia("button_over"));
        buttonClickMusic = new MediaPlayer(resouceManager.getMedia("button_click"));
        hintMusic = new MediaPlayer(resouceManager.getMedia("hint"));
        missMusic = new MediaPlayer(resouceManager.getMedia("error2"));
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    /**
     * 输出调试信息
     *
     * @param template 　字符串
     * @param args     　参数
     */
    public void debug(String template, Object... args) {
        String text = "";
        for (Object object : args) {
            text = template.replace("{}", String.valueOf(object));
        }
        DebugInfo debugInfo = (DebugInfo) modelManager.get("DebugInfo");
        if (debugInfo == null)
            return;
        debugInfo.setText(text);
    }

    /**
     * 重置所有和界面效果有关的音效
     */
    public void resetMedia() {
        buttonClickMusic.stop();
        buttonOverMusic.stop();
    }

    public void pause() {
        setStatus(GameStatus.PAUSE);
        mapManager.next();
        render.init(new PauseView());
    }

    public void resume() {
        if (status == GameStatus.PAUSE) {
            setStatus(GameStatus.RUNNING);
            mapManager.next();
            render.init(gameScene);
            eventManager.refresh();
        }
    }

    public void start() {
        if (status == GameStatus.STOP) {
            setStatus(GameStatus.RUNNING);
            mapManager.next();
            render.init();
            gameScene = render.getScene();
            eventManager.refresh();
        } else if (status == GameStatus.OVER) {
            setStatus(GameStatus.RUNNING);
            mapManager.next();
            render.init(gameScene);
            eventManager.refresh();
        }
    }

    public void home() {
        mapManager.random();
        render.init(new HomeView());
    }

    public void over() {
        if (status != GameStatus.OVER) {
            // 保存分数
            score = ((ScoreIndicator) modelManager.get("ScoreIndicator")).getScore();
            // 重置分数
            ((ScoreIndicator) modelManager.get("ScoreIndicator")).reset();
            ((HPIndicator) modelManager.get("HPIndicator")).reset();
            generator.reset();
            setStatus(GameStatus.OVER);
            mapManager.next();
            render.init(new OverView());
        }
    }

    public void exit() {
        new AnimationTimer() {
            long time = System.currentTimeMillis();

            @Override
            public void handle(long l) {
                if (System.currentTimeMillis() - time < 550) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                } else
                    System.exit(0);
            }
        }.start();
    }

    public ResouceManager getResouceManager() {
        return resouceManager;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public Render getRender() {
        return render;
    }

    public Scene getScene() {
        return render.getScene();
    }

    public EventManager getEventManager() {
        return eventManager;
    }


    public MapManager getMapManager() {
        return mapManager;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus setStatus) {
        switch (setStatus) {
            case STOP:
            case PAUSE:
            case OVER:
                animationTimer.stop();
                break;
            case RUNNING:
                animationTimer.start();
        }

        this.status = setStatus;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public MediaPlayer getBackgroundMusic() {
        return backgroundMusic;
    }

    public MediaPlayer getButtonOverMusic() {
        return buttonOverMusic;
    }

    public MediaPlayer getHintMusic() {
        return hintMusic;
    }

    public MediaPlayer getMissMusic() {
        return missMusic;
    }

    public MediaPlayer getButtonClickMusic() {
        return buttonClickMusic;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
