package game.Object.Model;

import game.Engine.Base.TextModel;
import game.Engine.Enum.ObjectStatus;
import game.Game;
import javafx.scene.paint.Color;

public class HPGadget extends TextModel {
    private double destinationX;
    private double destinationY;

    public HPGadget() {
        font = Game.getInstance().getResouceManager().getFont("Starcraft", 20);
        fillColor = Color.WHITE;
        strokeColor = Color.TRANSPARENT;
        verticalSpeed = 0;
    }

    @Override
    public void update() {
        if (status != ObjectStatus.ANIMATION)
            return;

        y -= verticalSpeed;
        x -= horizontalSpeed;


        if (y < destinationY)
            stop();

        if (x < destinationX)
            stop();
    }

    @Override
    public void start() {
        verticalSpeed = (y - destinationY) / 100;
        horizontalSpeed = (x - horizontalSpeed) / 100;
        status = ObjectStatus.ANIMATION;
    }

    @Override
    public void stop() {
        status = ObjectStatus.INVISIBLE;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }
}
