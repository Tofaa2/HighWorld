package quest.highworld.gui.guis.help.stats;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quest.highworld.gui.GUI;
import quest.highworld.utilities.Strings;

import java.util.List;

public class StatsInfoGUI extends GUI {

    public StatsInfoGUI(){
        super("&c&lStats Information", 6);
    }

    @Override
    public void setContents() {
        ItemStack strength = new ItemStack(Material.IRON_SWORD);
        ItemMeta strengthMeta = strength.getItemMeta();
        strengthMeta.setDisplayName(Strings.cc("&cStrength"));
        List<String> strengthLore = Strings.ccList("", "&7Strength is a measure of your", "&7physical power. It is used to", "&7determine your raw potential damage");
        strengthMeta.setLore(strengthLore);
        strength.setItemMeta(strengthMeta);

        ItemStack damage = new ItemStack(Material.RED_ROSE);
        ItemMeta damageMeta = damage.getItemMeta();
        damageMeta.setDisplayName(Strings.cc("&cDamage"));
        List<String> damageLore = Strings.ccList("", "&7Damage is a measure of your", "&7potential weapon damage. It is used to", "&7determine your raw potential damage");
        damageMeta.setLore(damageLore);
        damage.setItemMeta(damageMeta);

        ItemStack health = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta healthMeta = health.getItemMeta();
        healthMeta.setDisplayName(Strings.cc("&aHealth"));
        List<String> healthLore = Strings.ccList("", "&7Health is a measure of your", "&7current health. It is used to", "&7determine your current true health on damage");
        healthMeta.setLore(healthLore);
        health.setItemMeta(healthMeta);

        ItemStack defense = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta defenseMeta = defense.getItemMeta();
        defenseMeta.setDisplayName(Strings.cc("&ADefense"));
        List<String> defenseLore = Strings.ccList("", "&7Defense is a measure of your", "&7current defense. It is used to", "&7determine your current true health on damage");
        defenseMeta.setLore(defenseLore);
        defense.setItemMeta(defenseMeta);

        ItemStack speed = new ItemStack(Material.SUGAR);
        ItemMeta speedMeta = speed.getItemMeta();
        speedMeta.setDisplayName(Strings.cc("&fSpeed"));
        List<String> speedLore = Strings.ccList("", "&7Speed is a measure of your", "&7current speed. It is used to", "&7determine your current walking speed and jumping length");
        speedMeta.setLore(speedLore);
        speed.setItemMeta(speedMeta);

        ItemStack mana = new ItemStack(Material.POTION);
        ItemMeta manaMeta = mana.getItemMeta();
        manaMeta.setDisplayName(Strings.cc("&bMana"));
        List<String> manaLore = Strings.ccList("", "&7Mana is a measure of your", "&7current mana. It is used to", "&7cast spells and abilities");
        manaMeta.setLore(manaLore);
        mana.setItemMeta(manaMeta);

        ItemStack critChance = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta critChanceMeta = critChance.getItemMeta();
        critChanceMeta.setDisplayName(Strings.cc("&9Critical Chance"));
        List<String> critChanceLore = Strings.ccList("", "&7Critical Chance is a measure of your", "&7current critical chance. It is used to", "&7determine your chance to deal critical damage on hit");
        critChanceMeta.setLore(critChanceLore);
        critChance.setItemMeta(critChanceMeta);

        ItemStack critDamage = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta critDamageMeta = critDamage.getItemMeta();
        critDamageMeta.setDisplayName(Strings.cc("&9Critical Damage"));
        List<String> critDamageLore = Strings.ccList("", "&7Critical Damage is a measure of your", "&7current critical damage. It is used to", "&7determine how much extra damage you deal on critical hit");
        critDamageMeta.setLore(critDamageLore);
        critDamage.setItemMeta(critDamageMeta);

        getInventory().setItem(19, damage);
        getInventory().setItem(20, strength);
        getInventory().setItem(21, health);
        getInventory().setItem(22, defense);
        getInventory().setItem(23, mana);
        getInventory().setItem(24, speed);
        getInventory().setItem(25, critChance);
        getInventory().setItem(31, critDamage);

    }

    @Override
    public void onClick(InventoryClickEvent event) {

    }
}
