package game.EventHandler;

import game.Engine.Annotation.GameEventHandler;
import game.Engine.Enum.GameStatus;
import game.Engine.Enum.ObjectStatus;
import game.Game;
import game.Object.Model.Alpha;
import game.Object.Model.CenterArc;
import game.Object.Model.MissIndicator;
import game.Object.Model.ScoreIndicator;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@GameEventHandler
public class KeyEventHandler implements EventHandler<KeyEvent> {
    private Logger logger = LogManager.getLogger(KeyEventHandler.class);

    @Override
    public void handle(KeyEvent keyEvent) {
        boolean mapShake = false;

        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            // 地图震动
            if (mapShake)
                Game.getInstance().getMapManager().getCurrentMap().start();
            ((CenterArc) Game.getInstance().getModelManager().get("CenterArc")).changeStrokeColor();

            String keyAlpha = keyEvent.getText().toUpperCase();
            Alpha alpha = (Alpha) Game.getInstance().getModelManager().get(keyAlpha);
            if (alpha == null) {
                ((MissIndicator) Game.getInstance().getModelManager().get("MissIndicator")).miss();
                logger.info("missed {} !", keyAlpha);
                return;
            }

            if (alpha.getStatus() == ObjectStatus.ANIMATION) {
                Game.getInstance().debug("hint {}!", keyAlpha);
                ((ScoreIndicator) Game.getInstance().getModelManager().get("ScoreIndicator")).hint(alpha);
                alpha.stop();
            } else {
                ((MissIndicator) Game.getInstance().getModelManager().get("MissIndicator")).miss();
                logger.info("missed {} !", keyAlpha);
            }
        }

        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            if (mapShake)
                Game.getInstance().getMapManager().getCurrentMap().stop();
            ((CenterArc) Game.getInstance().getModelManager().get("CenterArc")).changeFillColor();
        }

        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            if (Game.getInstance().getStatus() == GameStatus.PAUSE)
                Game.getInstance().resume();
            else if (Game.getInstance().getStatus() == GameStatus.RUNNING)
                Game.getInstance().pause();
        }
    }
}
