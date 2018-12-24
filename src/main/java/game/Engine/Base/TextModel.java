package game.Engine.Base;

import game.Engine.Annotation.GameModel;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

@GameModel
public abstract class TextModel extends BaseModel {

    protected Font font;
    protected Paint fillColor;
    protected Paint strokeColor;
    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Paint getFillColor() {
        return fillColor;
    }

    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
    }

}
