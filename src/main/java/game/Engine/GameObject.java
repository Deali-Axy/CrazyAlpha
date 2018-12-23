package game.Engine;

import game.Engine.Enum.ObjectStatus;

public abstract class GameObject {
    protected ObjectStatus status = ObjectStatus.STOP;

    public abstract void update();

    public abstract void start();

    public abstract void stop();

    public String hashValue() {
        return "0x" + Integer.toHexString(hashCode()).toUpperCase();
    }
}
