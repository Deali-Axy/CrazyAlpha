package game.Object;

import game.Engine.Annotation.GameModel;
import game.Engine.Base.ImageModel;
import javafx.scene.image.Image;

@GameModel
public class Simple extends ImageModel {
    public Simple() {
        this.image = new Image("file:resources/images/icon_50.png");
    }

    @Override
    public void update() {
//        this.x++;
//        this.y++;
//        System.out.printf("%f,%f\n", x, y);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
