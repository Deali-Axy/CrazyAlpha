package game.Engine.Base;

import game.Engine.Annotation.GameModel;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@GameModel
public abstract class TextModel extends BaseModel {

    protected Font font;
    protected Color fillColor;
    protected Color strokeColor;
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

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

}
