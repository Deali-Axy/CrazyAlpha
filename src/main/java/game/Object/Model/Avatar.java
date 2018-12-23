package game.Object.Model;

import game.Engine.Base.ImageModel;
import game.Game;
import javafx.scene.image.ImageView;

public class Avatar extends ImageModel {

    public Avatar() {
        image = Game.getInstance().getResouceManager().getImage("avatar");
        imageView = new ImageView(image);
        x = 30;
        y = 30;
        setWidth(50);
        setHeight(50);
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
