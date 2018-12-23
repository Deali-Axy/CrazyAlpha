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

public class PauseView extends GameView {
    private GameMap map;
    private ImageView mapIv;
    private Label nameLbl;
    private ImageView resumeBtn;
    private ImageView exitBtn;

    public PauseView() {
        root = new Pane();
        map = Game.getInstance().getMapManager().getCurrentMap();
        mapIv = new ImageView(map.getMapImage());
        mapIv.setFitWidth(Game.getInstance().getWidth());
        mapIv.setFitHeight(Game.getInstance().getHeight());

        nameLbl = new Label("CrazyAlpha!");
        nameLbl.setTextFill(Color.WHITESMOKE);
        nameLbl.setFont(Game.getInstance().getResouceManager().getFont("Paranoid", 120));
        nameLbl.setLayoutX(50);
        nameLbl.setLayoutY(50);

        Reflection reflection1 = new Reflection();
        reflection1.setFraction(1.0);
        nameLbl.setEffect(reflection1);

        Reflection reflection02 = new Reflection();
        reflection02.setFraction(0.4);

        resumeBtn = new ImageView(Game.getInstance().getResouceManager().getControl("btn_resume"));
        resumeBtn.setFitWidth(165 * 1.5);
        resumeBtn.setFitHeight(65 * 1.5);
        exitBtn = new ImageView(Game.getInstance().getResouceManager().getControl("btn_exit"));
        exitBtn.setFitWidth(165 * 1.5);
        exitBtn.setFitHeight(65 * 1.5);

        resumeBtn.setLayoutX(map.getWidth() - resumeBtn.getFitWidth() - 20);
        resumeBtn.setLayoutY(map.getHeight() - resumeBtn.getFitHeight() - exitBtn.getFitHeight() - 120);
        resumeBtn.setEffect(reflection02);
        EventHandler<MouseEvent> mouseEventEventHandler = event -> resumeBtn.setEffect(new Glow(0.8));
        resumeBtn.setOnMouseEntered(mouseEventEventHandler);
        resumeBtn.setOnMouseExited(event -> resumeBtn.setEffect(reflection02));
        resumeBtn.setOnMousePressed(event -> {
            resumeBtn.setEffect(new GaussianBlur());
            Game.getInstance().resume();
        });
        resumeBtn.setOnMouseReleased(mouseEventEventHandler);

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
        root.getChildren().add(resumeBtn);
        root.getChildren().add(exitBtn);

        makeFadeTransition(resumeBtn, 2000, 1, 0.7);
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
