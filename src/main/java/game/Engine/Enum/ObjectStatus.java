package game.Engine.Enum;

public enum ObjectStatus {
    /**
     * 停止不动
     */
    STOP,

    /**
     * 动画状态
     */
    ANIMATION,

    /**
     * 不可见状态：但是模型不会被销毁，等待下次调用
     */
    INVISIBLE,
}
