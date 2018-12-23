package game.Engine.Base;

import game.Engine.Annotation.GameModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@GameModel
public abstract class ImageModel extends BaseModel {
    protected Image image;
    protected ImageView imageView;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public double getWidth() {
        return imageView.getFitWidth();
    }

    @Override
    public void setWidth(double value) {
        imageView.setFitWidth(value);
    }

    @Override
    public double getHeight() {
        return imageView.getFitHeight();
    }

    @Override
    public void setHeight(double value) {
        imageView.setFitHeight(value);
    }
}
