package game.View;

import game.Engine.GameView;
import game.Game;
import game.Object.GameMap;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeView extends GameView {
    private Logger logger = LogManager.getLogger(getClass());

    public HomeView() {
        root = new Pane();

        GameMap map = Game.getInstance().getMapManager().getCurrentMap();
        ImageView mapIv = new ImageView(map.getMapImage());
        mapIv.setFitWidth(Game.getInstance().getWidth());
        mapIv.setFitHeight(Game.getInstance().getHeight());

        Label nameLbl = new Label(Game.name);
        nameLbl.setTextFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT,
                new Stop(0, Color.BLACK), new Stop(1f, Color.RED)));
        nameLbl.setWrapText(true);
        nameLbl.setFont(Game.getInstance().getResouceManager().getFont("Starcraft", 80));
        nameLbl.setLayoutX(50);
        nameLbl.setLayoutY(50);

        Label authorLbl = new Label("Design By: " + Game.author);
        authorLbl.setTextFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT,
                new Stop(0, Color.BLACK), new Stop(1f, Color.BLUE)));
        authorLbl.setFont(Game.getInstance().getResouceManager().getFont("Origram", 40));
        authorLbl.setLayoutX(50);
        authorLbl.setLayoutY(map.getHeight() - authorLbl.getHeight() - 140);

        Label versionLbl = new Label("Version: " + Game.version);
        versionLbl.setTextFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT,
                new Stop(0, Color.BLACK), new Stop(1f, Color.AQUA)));
        versionLbl.setFont(Game.getInstance().getResouceManager().getFont("Origram", 40));
        versionLbl.setLayoutX(50);
        versionLbl.setLayoutY(map.getHeight() - versionLbl.getHeight() - 100);

        Reflection reflection1 = new Reflection();
        reflection1.setFraction(1.0);
        nameLbl.setEffect(reflection1);

        Reflection reflection02 = new Reflection();
        reflection02.setFraction(0.4);

        ImageView startBtn = new ImageView(Game.getInstance().getResouceManager().getControl("btn_start"));
        startBtn.setFitWidth(165 * 1.5);
        startBtn.setFitHeight(65 * 1.5);
        ImageView exitBtn = new ImageView(Game.getInstance().getResouceManager().getControl("btn_exit"));
        exitBtn.setFitWidth(165 * 1.5);
        exitBtn.setFitHeight(65 * 1.5);

        startBtn.setLayoutX(map.getWidth() - startBtn.getFitWidth() - 20);
        startBtn.setLayoutY(map.getHeight() - startBtn.getFitHeight() - exitBtn.getFitHeight() - 120);
        startBtn.setEffect(reflection02);
        EventHandler<MouseEvent> mouseEventEventHandler = event -> startBtn.setEffect(new Glow(0.8));
        startBtn.setOnMouseEntered(mouseEventEventHandler);
        startBtn.setOnMouseExited(event -> startBtn.setEffect(reflection02));
        startBtn.setOnMousePressed(event -> {
            startBtn.setEffect(new GaussianBlur());
            Game.getInstance().start();
        });
        startBtn.setOnMouseReleased(mouseEventEventHandler);

        exitBtn.setLayoutX(map.getWidth() - exitBtn.getFitWidth() - 20);
        exitBtn.setLayoutY(map.getHeight() - exitBtn.getFitHeight() - 60);
        exitBtn.setEffect(reflection02);
        exitBtn.setOnMouseEntered(event -> exitBtn.setEffect(new Glow(0.8)));
        exitBtn.setOnMouseExited(event -> exitBtn.setEffect(reflection02));
        exitBtn.setOnMousePressed(event -> exitBtn.setEffect(new GaussianBlur()));
        exitBtn.setOnMouseReleased(event -> {
            exitBtn.setEffect(new Glow(0.8));
            Game.getInstance().exit();
        });


        root.getChildren().add(mapIv);
        root.getChildren().add(nameLbl);
        root.getChildren().add(authorLbl);
        root.getChildren().add(versionLbl);
        root.getChildren().add(startBtn);
        root.getChildren().add(exitBtn);

        makeFadeTransition(startBtn, 2000, 1, 0.7);
        makeFadeTransition(exitBtn, 2000, 1, 0.7);
        makeFadeTransition(mapIv, 3000, 1, 0.8);
        makeScaleTransition(mapIv, 10000, 0.25, 0.25);
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
