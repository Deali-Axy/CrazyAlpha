package game.Manager;

import game.Engine.Annotation.GameManager;
import game.Game;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;

@GameManager
public class EventManager {
    private HashMap<EventType, EventHandler> handlers = new HashMap<>();

    /**
     * 注册监听事件
     *
     * @param eventHandler 事件处理器
     * @param eventType    事件类型
     */
    public void register(EventHandler eventHandler, EventType eventType) {
        handlers.put(eventType, eventHandler);
        Game.getInstance().getScene().addEventHandler(eventType, eventHandler);
    }

    /**
     * 更换 scene 之后刷新绑定的事件
     */
    public void refresh() {
        for (Map.Entry<EventType, EventHandler> entry : handlers.entrySet()) {
            Game.getInstance().getScene().addEventHandler(entry.getKey(), entry.getValue());
        }
    }

    public EventHandler[] getAllEventHandlers() {
        return handlers.values().toArray(new EventHandler[0]);
    }
}
