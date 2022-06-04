package quest.highworld.factory.entity;

import org.bukkit.Location;

public interface IHighWorldEntity {

    void spawn();

    void remove();

    HighWorldEntityType getType();


    int getHealth();

    void setHealth(int health);

    int getDefense();

    void setDefense(int defense);


    Location getLocation();

    void setLocation(Location location);



}
