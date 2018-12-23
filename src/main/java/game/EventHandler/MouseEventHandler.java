package game.EventHandler;

import game.Engine.Annotation.GameEventHandler;
import game.Engine.Base.BaseModel;
import game.Game;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

@GameEventHandler
public class MouseEventHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
            BaseModel model = Game.getInstance().getModelManager().getAllModels()[0];
            model.setX(event.getX());
            model.setY(event.getY());
//            System.out.printf("getX=%f, getY=%f\n", model.x, model.y);
        }
    }
}
