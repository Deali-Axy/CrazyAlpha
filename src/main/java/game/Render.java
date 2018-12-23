package game;

import game.Engine.Base.BaseModel;
import game.Engine.Base.ImageModel;
import game.Engine.Base.ShapeModel;
import game.Engine.Base.TextModel;
import game.Engine.Enum.ObjectStatus;
import game.Engine.GameView;
import game.Object.GameMap;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;


public class Render extends Stage {
    private Logger logger = LogManager.getLogger(Render.class);
    private Group root = new Group();
    private HashMap<String, Canvas> canvasMap = new HashMap<>();

    public Render() {
    }

    public void init() {
        init(root);
    }

    public void init(GameView view) {
        init(view.getRoot());
    }

    public void init(Parent parent) {
        init(new Scene(parent));
    }

    public void init(Scene scene) {
        this.setScene(scene);
        this.setWidth(Game.getInstance().getMapManager().getCurrentMap().getWidth()); // 设置宽度
        this.setHeight(Game.getInstance().getMapManager().getCurrentMap().getHeight());   // 设置高度
        this.setTitle(Game.name + " Version: " + Game.version);
        this.show(); //显示出来啦
    }

    private Canvas getCanvas(String value) {
        Canvas canvas;
        if (canvasMap.containsKey(value))
            canvas = canvasMap.get(value);
        else {
            canvas = new Canvas();
            //绑定canvas的长宽，保持与stage的长宽一致
            canvas.widthProperty().bind(widthProperty());
            canvas.heightProperty().bind(heightProperty());
            canvasMap.put(value, canvas);
            root.getChildren().add(canvas);
        }

        // 画布擦除
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        return canvas;
    }

    public void update() {
        // 绘制地图
        GameMap map = Game.getInstance().getMapManager().getCurrentMap();
        Canvas mapCanvas = getCanvas("Map");
        mapCanvas.getGraphicsContext2D().drawImage(map.getMapImage(), map.getX(), map.getY(), map.getWidth(), map.getHeight());

        Canvas alphaCanvas = getCanvas("Alpha");

        // 绘制模型
        for (BaseModel modelItem : Game.getInstance().getModelManager().getAllModels()) {
            if (modelItem.getStatus() == ObjectStatus.INVISIBLE)
                continue;

            // ShapeModel 处理
            if (modelItem instanceof ShapeModel) {
                ShapeModel model = (ShapeModel) modelItem;
                GraphicsContext gc = getCanvas(model.hashValue()).getGraphicsContext2D();
                for (Shape shape : model.getShapes().toArray(new Shape[0])) {
                    gc.setFill(model.getFillColor());
                    gc.setStroke(model.getStrokeColor());
                    if (shape instanceof Arc) {
                        Arc arc = (Arc) shape;
                        gc.fillArc(arc.getCenterX(), arc.getCenterY(), arc.getRadiusX(), arc.getRadiusY(), arc.getStartAngle(), arc.getLength(), arc.getType());
                        gc.strokeArc(arc.getCenterX(), arc.getCenterY(), arc.getRadiusX(), arc.getRadiusY(), arc.getStartAngle(), arc.getLength(), arc.getType());
                    }
                }
                // 绘制效果
                gc.applyEffect(modelItem.getEffect());
            }

            // TextModel 处理
            if (modelItem instanceof TextModel) {
                GraphicsContext gc = alphaCanvas.getGraphicsContext2D();
                TextModel model = (TextModel) modelItem;
                gc.setFont(model.getFont());
                gc.setFill(model.getFillColor());
                gc.setStroke(model.getStrokeColor());
                gc.fillText(model.getText(), model.getX(), model.getY());
                gc.strokeText(model.getText(), model.getX(), model.getY());
                // 绘制效果
                gc.applyEffect(modelItem.getEffect());
            }

            // ImageModel 处理
            if (modelItem instanceof ImageModel) {
                ImageModel model = (ImageModel) modelItem;
                GraphicsContext gc = getCanvas(model.hashValue()).getGraphicsContext2D();
                gc.drawImage(model.getImageView().getImage(), model.getX(), model.getY());
                // 绘制效果
                gc.applyEffect(modelItem.getEffect());
            }
        }


        logger.debug("canvas count={}", root.getChildren().size());
    }
}
