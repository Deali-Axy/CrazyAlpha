package game.View;

import game.Engine.GameView;
import game.Game;
import game.Object.GameMap;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OverView extends GameView {

    public OverView() {
        root = new Pane();
        GameMap map = Game.getInstance().getMapManager().getCurrentMap();
        ImageView mapIv = new ImageView(map.getMapImage());
        mapIv.setFitWidth(Game.getInstance().getWidth());
        mapIv.setFitHeight(Game.getInstance().getHeight());

        Label nameLbl = new Label("Game Over!");
        nameLbl.setTextFill(Color.WHITESMOKE);
        nameLbl.setFont(Game.getInstance().getResouceManager().getFont("Starcraft", 80));
        nameLbl.setLayoutX(50);
        nameLbl.setLayoutY(50);

        Label scoreLbl = new Label("Score: " + Game.getInstance().getScore());
        scoreLbl.setTextFill(Color.WHITESMOKE);
        scoreLbl.setFont(Game.getInstance().getResouceManager().getFont("Starcraft", 60));
        scoreLbl.setLayoutX(50);
        scoreLbl.setLayoutY(map.getHeight() - scoreLbl.getHeight() - 140);

        Reflection reflection = new Reflection();
        reflection.setFraction(1.0);
        nameLbl.setEffect(reflection);

        ImageView homeBtn = new ImageView(Game.getInstance().getResouceManager().getControl("btn_home"));
        homeBtn.setFitWidth(165 * 1.5);
        homeBtn.setFitHeight(65 * 1.5);

        homeBtn.setLayoutX(map.getWidth() - homeBtn.getFitWidth() - 20);
        homeBtn.setLayoutY(map.getHeight() - homeBtn.getFitHeight() - 60);
        homeBtn.setEffect(reflection);
        homeBtn.setOnMouseEntered(event -> homeBtn.setEffect(new Glow(0.8)));
        homeBtn.setOnMouseExited(event -> homeBtn.setEffect(reflection));
        homeBtn.setOnMousePressed(event -> homeBtn.setEffect(new GaussianBlur()));
        homeBtn.setOnMouseReleased(event -> {
            homeBtn.setEffect(new Glow(0.8));
            Game.getInstance().home();
        });

        root.getChildren().add(mapIv);
        root.getChildren().add(nameLbl);
        root.getChildren().add(scoreLbl);
        root.getChildren().add(homeBtn);

        makeFadeTransition(homeBtn, 2000, 1, 0.7);
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
