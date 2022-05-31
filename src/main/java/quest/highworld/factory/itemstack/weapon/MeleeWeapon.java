package quest.highworld.factory.itemstack.weapon;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


/**
 *  Melee Weapon boilerplate class
 *  If an ability is needed the class should extend either BukkitHighWorldListener or PacketHighWorldListener
 *  Extending the class will allow creation of weapon items with abilities
 *
 *  Quality float is used to determine the quality of the weapon
 *  Quality is a float between 0 and 1
 *  Quality is used to determine the overall stats' addition to a weapon
 *  Default weapon quality is 0.5f
 */
public class MeleeWeapon {


    private final ItemStack item;

    private float quality;

    private int durability;
    private int damage;
    private int speed;
    private int strength;
    private int criticalChance;
    private int criticalDamage;
    private int defense;
    private int health;

    public MeleeWeapon(Material material, String id) {
        this.item = new ItemStack(material);
        this.quality = 0.5f;
    }



    private void rollQuality(){this.quality = (float) Math.random();}
    
    // For craftable items quality should be 1 so make sure to set it to 1
    private void craftQuality(){this.quality = 1f;}

    private void rollStatsQuality(){

    }

    public ItemStack getItem() {
        return item;
    }


}
