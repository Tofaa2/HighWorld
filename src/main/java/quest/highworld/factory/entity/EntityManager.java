package quest.highworld.factory.entity;

import java.util.HashMap;

public class EntityManager {

    private final HashMap<Integer, Object> persistentEntityMap;

    public EntityManager() {
        persistentEntityMap = new HashMap<>();
    }


    public boolean addPersistentEntity(int entityId, Object entity) {
        if (persistentEntityMap.containsKey(entityId)) {
            return false;
        }
        persistentEntityMap.put(entityId, entity);
        return true;
    }

    public boolean removePersistentEntity(int entityId) {
        if (!persistentEntityMap.containsKey(entityId)) {
            return false;
        }
        persistentEntityMap.remove(entityId);
        return true;
    }


}
