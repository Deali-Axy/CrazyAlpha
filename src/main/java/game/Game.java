package game;

import game.Engine.Enum.GameStatus;
import game.Manager.EventManager;
import game.Manager.MapManager;
import game.Manager.ModelManager;
import game.Manager.ResouceManager;
import game.Object.Model.DebugInfo;
import game.Object.Model.MissIndicator;
import game.Object.Model.ScoreIndicator;
import game.View.HomeView;
import game.View.OverView;
import game.View.PauseView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    private Render render = new Render();
    private Runnable backgroundMusic;
    private MediaPlayer mediaPlayer;

    private GameStatus status = GameStatus.STOP;
    private int score;  // 上次游戏得分

    private double width;
    private double height;

    // scene
    private Scene gameScene;

    private Game() {
        // todo 背景音乐
        backgroundMusic = () -> {
            logger.info("backgroundMusic define");
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("resources/music/all-your-heart.mp3"));
                logger.info("backgroundMusic buffer");
                Player player = new Player(bufferedInputStream);
                logger.info("backgroundMusic create player");
//                player.play();
                logger.info("backgroundMusic play");
            } catch (FileNotFoundException | JavaLayerException ex) {
                logger.error(ex.getMessage());
            }
        };

//        mediaPlayer = new MediaPlayer(resouceManager.getMusic("all-your-heart"));
//        mediaPlayer.play();
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

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

    // todo 还要重置字母生成器呢！
    public void over() {
        if (status != GameStatus.OVER) {
            // 保存分数
            score = ((ScoreIndicator) modelManager.get("ScoreIndicator")).getScore();
            // 重置分数
            ((ScoreIndicator) modelManager.get("ScoreIndicator")).reset();
            ((MissIndicator) modelManager.get("MissIndicator")).reset();
            setStatus(GameStatus.OVER);
            mapManager.next();
            render.init(new OverView());
        }
    }

    public void exit() {
        System.exit(0);
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
}
