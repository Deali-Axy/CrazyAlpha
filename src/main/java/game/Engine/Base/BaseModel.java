package game.Engine.Base;

import game.Engine.Annotation.GameModel;
import game.Engine.Enum.ObjectStatus;
import game.Engine.GameObject;
import javafx.scene.effect.Effect;


@GameModel
public abstract class BaseModel extends GameObject {
    protected double width;
    protected double height;
    protected double x;
    protected double y;
    protected Effect effect;

    /**
     * 水平方向的运动，1: 向右，-1: 向左
     */
    protected int horizontalMotion = 0;

    /**
     * 竖直方向运动：1: 向上，-1: 向下
     */
    protected int verticalMotion = 0;

    /**
     * 动画速度
     */
    protected double motionSpeed = 0;

    /**
     * 水平速度
     */
    protected double horizontalSpeed = 0;

    /**
     * 垂直速度
     */
    protected double verticalSpeed = 0;

    /**
     * 水平加速度
     */
    protected double horizontalAcceleration = 0;

    /**
     * 竖直加速度
     */
    protected double verticalAcceleration = 0;

    @Override
    public String toString() {
        return String.format("width=%f height=%f x=%f y=%f", width, height, x, y);
    }

    public void setWidth(double value) {
        this.width = value;
    }

    public double getWidth() {
        return this.width;
    }

    public void setHeight(double value) {
        this.height = value;
    }

    public double getHeight() {
        return this.height;
    }

    public void setX(double value) {
        this.x = value;
    }

    public double getX() {
        return this.x;
    }

    public void setY(double value) {
        this.y = value;
    }

    public double getY() {
        return this.y;
    }

    public ObjectStatus getStatus() {
        return status;
    }

    public void setStatus(ObjectStatus status) {
        this.status = status;
    }

    public double getHorizontalMotion() {
        return horizontalMotion;
    }

    public void setHorizontalMotion(int horizontalMotion) {
        this.horizontalMotion = horizontalMotion;
    }

    public double getVerticalMotion() {
        return verticalMotion;
    }

    public void setVerticalMotion(int verticalMotion) {
        this.verticalMotion = verticalMotion;
    }

    public double getMotionSpeed() {
        return motionSpeed;
    }

    public void setMotionSpeed(double motionSpeed) {
        this.motionSpeed = motionSpeed;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setHorizontalSpeed(double horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public double getHorizontalAcceleration() {
        return horizontalAcceleration;
    }

    public void setHorizontalAcceleration(double horizontalAcceleration) {
        this.horizontalAcceleration = horizontalAcceleration;
    }

    public double getVerticalAcceleration() {
        return verticalAcceleration;
    }

    public void setVerticalAcceleration(double verticalAcceleration) {
        this.verticalAcceleration = verticalAcceleration;
    }
}
