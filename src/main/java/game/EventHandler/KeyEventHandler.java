package game.EventHandler;

import game.Engine.Annotation.GameEventHandler;
import game.Engine.Enum.GameStatus;
import game.Engine.Enum.ObjectStatus;
import game.Engine.Utils.RandomEx;
import game.Game;
import game.Object.Model.*;
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
                Game.getInstance().getMissMusic().play();
                ((HPIndicator) Game.getInstance().getModelManager().get("HPIndicator")).miss();
                logger.info("missed {} !", keyAlpha);
                return;
            }

            if (alpha.getStatus() == ObjectStatus.ANIMATION) {
                Game.getInstance().debug("hint {}!", keyAlpha);
                Game.getInstance().getHintMusic().play();
                int gainScore = ((ScoreIndicator) Game.getInstance().getModelManager().get("ScoreIndicator")).hint(alpha);

                ScoreGadget scoreGadget = (ScoreGadget) Game.getInstance().getModelManager().getInvisibleModel(ScoreGadget.class);
                if (scoreGadget == null)
                    scoreGadget = new ScoreGadget();
                scoreGadget.setText("+" + String.valueOf(gainScore));
                scoreGadget.setX(alpha.getX());
                scoreGadget.setY(alpha.getY());
                scoreGadget.setFillColor(alpha.getFillColor());
                Game.getInstance().getModelManager().register(scoreGadget, String.valueOf(RandomEx.nextInt()));
                scoreGadget.start();
                alpha.stop();
            } else {
                Game.getInstance().getMissMusic().play();
                ((HPIndicator) Game.getInstance().getModelManager().get("HPIndicator")).miss();
                logger.info("missed {} !", keyAlpha);
            }
        }

        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            if (mapShake)
                Game.getInstance().getMapManager().getCurrentMap().stop();
            Game.getInstance().getHintMusic().stop();
            Game.getInstance().getMissMusic().stop();
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
