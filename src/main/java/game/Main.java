package game;

import game.Engine.Base.BaseModel;
import game.EventHandler.KeyEventHandler;
import game.Object.Generator;
import game.Object.Model.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    // 游戏帧数设置
    private final int frameNumber = 60;

    private Game gameObject;
    private Render render;
    private Generator generator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameObject = Game.getInstance();
        render = gameObject.getRender();
        generator = new Generator();

        // 设置大小
        gameObject.setWidth(1440);
        gameObject.setHeight(900);
        // 加载地图
        for (String s : gameObject.getResouceManager().getAllMaps()) {
            gameObject.getMapManager().load(s);
        }
        // 进入主界面
        gameObject.home();
        // 注册模型
        gameObject.getModelManager().register(new ScoreIndicator());
        gameObject.getModelManager().register(new MissIndicator());
        gameObject.getModelManager().register(new Avatar());
        gameObject.getModelManager().register(new DebugInfo());
        gameObject.getModelManager().register(new CenterArc());
        // 注册事件
        gameObject.getEventManager().register(new KeyEventHandler(), KeyEvent.ANY);

        // 启动字母生成器
        generator.start();

        gameObject.setAnimationTimer(new AnimationTimer() {
            long time = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time < 1000 / frameNumber) {
                    try {
                        Thread.sleep(1000 / frameNumber - (System.currentTimeMillis() - time));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for (BaseModel model : gameObject.getModelManager().getAllModels()) {
                    model.update();
                }
                gameObject.getMapManager().getCurrentMap().update();
                generator.update();
                render.update();
                time = System.currentTimeMillis();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
