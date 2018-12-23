package game.Manager;

import game.Engine.Annotation.GameManager;
import game.Engine.Base.BaseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@GameManager
public class ModelManager {
    private Logger logger = LogManager.getLogger(ModelManager.class);
    private HashMap<String, BaseModel> modelMap = new HashMap<>();

    public void register(BaseModel model) {
        register(model, model.getClass().getSimpleName());
    }

    public void register(BaseModel model, String name) {
        if (!modelMap.containsKey(name))
            modelMap.put(name, model);
        else
            logger.error("model {} is exist!", name);
    }

    public boolean contains(String name) {
        return modelMap.containsKey(name);
    }

    public BaseModel get(String name) {
        if (modelMap.containsKey(name))
            return modelMap.get(name);
        else {
            logger.error("model {} is not exist.", name);
            return null;
        }
    }

    public BaseModel[] getAllModels() {
        return modelMap.values().toArray(new BaseModel[0]);
    }
}
