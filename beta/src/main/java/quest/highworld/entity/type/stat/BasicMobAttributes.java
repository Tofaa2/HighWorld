package quest.highworld.entity.type.stat;

import quest.highworld.entity.type.HighworldMob;

public class BasicMobAttributes {

    private final int level;
    private final int health;
    private final int defense;
    private final int strength;
    private final int speed;

    public BasicMobAttributes(HighworldMob mob, int level, int health, int defense, int strength, int speed) {
        this.level = level;
        this.health = health;
        this.defense = defense;
        this.strength = strength;
        this.speed = speed;

    }
}
