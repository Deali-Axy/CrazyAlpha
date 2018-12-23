package game.Engine;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.in;

public abstract class GameView extends GameObject {
    protected Pane root;

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public static Initializable getInitializable(String viewName) throws IOException {
        FXMLLoader loader = new FXMLLoader();    // 创建对象

        loader.setBuilderFactory(new JavaFXBuilderFactory());    // 设置BuilderFactory
        loader.setLocation(GameView.class.getResource(viewName + ".fxml"));    // 设置路径基准

        try {
            InputStream in = GameView.class.getResourceAsStream(viewName + ".fxml");
            AnchorPane page = (AnchorPane) loader.load(in); // 对象方法的参数是InputStream，返回值是Object

            Scene scene = new Scene(page, 800, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.sizeToScene();

            return (Initializable) loader.getController();    // 可以得到Controller

        } finally {
            in.close();
            return null;
        }
    }

    public static void makeFadeTransition(Node node, int millis, double fromValue, double toValue) {
        FadeTransition ft = new FadeTransition(Duration.millis(millis));
        ft.setFromValue(fromValue);
        ft.setToValue(toValue);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);
        ft.setNode(node);
        ft.play();
    }

    public static void makeScaleTransition(Node node, int millis, double byX, double byY) {
        ScaleTransition st = new ScaleTransition(Duration.millis(millis));
        st.setByX(byX);
        st.setByY(byY);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);
        st.setNode(node);
        st.play();
    }

    public static void makeRotateTransition(Node node, int mills, double byAngle) {
        RotateTransition rt = new RotateTransition(Duration.millis(mills));
        rt.setByAngle(byAngle);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setNode(node);
        rt.play();
    }

    public static void makeRotateTransition(Node node, int mills, double fromAngle, double toAngle, boolean autoReverse) {
        RotateTransition rt = new RotateTransition(Duration.millis(mills));
        rt.setFromAngle(fromAngle);
        rt.setToAngle(toAngle);
        rt.setAutoReverse(autoReverse);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setNode(node);
        rt.play();
    }
}
