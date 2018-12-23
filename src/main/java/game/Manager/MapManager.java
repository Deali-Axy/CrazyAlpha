package game.Manager;

import game.Engine.Annotation.GameManager;
import game.Engine.Utils.RandomEx;
import game.Object.GameMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@GameManager
public class MapManager {
    private Logger logger = LogManager.getLogger(MapManager.class);

    private Map<String, GameMap> mapList = new HashMap<>();
    private GameMap currentMap;
    private int currentIndex = 0;

    public GameMap setCurrentMap(String mapName) {
        if (mapList.containsKey(mapName)) {
            currentMap = mapList.get(mapName);
            return currentMap;
        } else {
            logger.error("map {} is not exist!", mapName);
            return null;
        }
    }

    /**
     * 切换到下一张地图
     *
     * @return 地图
     */
    public GameMap next() {
        if (currentIndex > mapList.values().size() - 1)
            currentIndex = 0;
        currentMap = mapList.values().toArray(new GameMap[0])[currentIndex++];
        return currentMap;
    }

    /**
     * 随机地图
     *
     * @return 地图
     */
    public GameMap random() {
        currentMap = mapList.values().toArray(new GameMap[0])[RandomEx.nextInt(mapList.size())];
        return currentMap;
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    public GameMap load(String mapFilePath) {
        String fullName = new File(mapFilePath).getName();
        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
        return load(mapFilePath, fileName);
    }

    public GameMap load(String mapFilePath, String mapName) {
        this.currentMap = new GameMap(mapFilePath);
        this.mapList.put(mapName, currentMap);
        return currentMap;
    }

}
