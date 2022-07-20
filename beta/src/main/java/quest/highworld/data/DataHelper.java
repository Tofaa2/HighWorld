package quest.highworld.data;

import net.minestom.server.entity.Player;

import java.sql.*;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataHelper {

    private final Database db = new Database();
    private final Connection cn = db.getConnection();

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public DataHelper() {

    }

    /**
     * Gets the specified player's rank.
     * @param player The player to get the rank of.
     * @return The rank of the player.
     */
    public Rank getRank(Player player) {
        UUID id = player.getUuid();
        Rank rank;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT rank FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                rank = Rank.valueOf(rs.getString("rank"));
                return rank;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets the specified player's rank.
     * @param player The player to set the rank of.
     * @param rank The rank to set the player to.
     */
    public void setRank(Player player, Rank rank) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET rank = ? WHERE uuid = ?");
            ps.setString(1, rank.toString());
            ps.setString(2, id.toString());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's XP.
     * @param player The player to get the XP of.
     * @return The XP of the player.
     */
    public double getXP(Player player) {
        UUID id = player.getUuid();
        int xp;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT xp FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                xp = rs.getInt("xp");
                return xp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's XP.
     * @param player The player to set the XP of.
     * @param xp The XP to set the player to.
     */
    public void setXP(Player player, double xp) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET xp = ? WHERE uuid = ?");
            ps.setInt(1, (int) xp);
            ps.setString(2, id.toString());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's coins.
     * @param player The player to get the coins of.
     * @return The coins of the player.
     */
    public int getCoins(Player player) {
        UUID id = player.getUuid();
        int coins;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT coins FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                coins = rs.getInt("coins");
                return coins;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's coins.
     * @param player The player to set the coins of.
     * @param coins The coins to set the player to.
     */
    public void setCoins(Player player, int coins) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET coins = ? WHERE uuid = ?");
            ps.setInt(1, coins);
            ps.setString(2, id.toString());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's level
     * @param player The player to get the level of.
     */
    public int getLevel(Player player) {
        UUID id = player.getUuid();
        int level;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT level FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                level = rs.getInt("level");
                return level;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's level.
     * @param player The player to set the level of.
     * @param level The level to set the player to.
     */
    public void setLevel(Player player, int level) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET level = ? WHERE uuid = ?");
            ps.setInt(1, level);
            ps.setString(2, id.toString());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's max health.
     * @param player The player to get the max health of.
     */
    public int getMaxHealth(Player player) {
        UUID id = player.getUuid();
        int maxHealth;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT max_health FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                maxHealth = rs.getInt("max_health");
                return maxHealth;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's max health.
     * @param player The player to set the max health of.
     * @param maxHealth The max health to set the player to.
     */
    public void setMaxHealth(Player player, int maxHealth) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET max_health = ? WHERE uuid = ?");
            ps.setInt(1, maxHealth);
            ps.setString(2, id.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's max mana.
     * @param player The player to get the max mana of.
     */
    public int getMaxMana(Player player) {
        UUID id = player.getUuid();
        int maxMana;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT max_mana FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                maxMana = rs.getInt("max_mana");
                return maxMana;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's max mana.
     * @param player The player to set the max mana of.
     * @param maxMana The max mana to set the player to.
     */
    public void setMaxMana(Player player, int maxMana) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET max_mana = ? WHERE uuid = ?");
            ps.setInt(1, maxMana);
            ps.setString(2, id.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Gets the specified player's defense.
     * @param player The player to get the defense of.
     */
    public int getDefense(Player player) {
        UUID id = player.getUuid();
        int defense;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT defense FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                defense = rs.getInt("defense");
                return defense;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's defense.
     * @param player The player to set the defense of.
     * @param defense The defense to set the player to.
     */
    public void setDefense(Player player, int defense) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET defense = ? WHERE uuid = ?");
            ps.setInt(1, defense);
            ps.setString(2, id.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's strength.
     * @param player The player to get the strength of.
     */
    public int getStrength(Player player) {
        UUID id = player.getUuid();
        int strength;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT strength FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                strength = rs.getInt("strength");
                return strength;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's strength.
     * @param player The player to set the strength of.
     * @param strength The strength to set the player to.
     */
    public void setStrength(Player player, int strength) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET strength = ? WHERE uuid = ?");
            ps.setInt(1, strength);
            ps.setString(2, id.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's critical chance.
     * @param player The player to get the critical chance of.
     * @return The critical chance of the player.
     */
    public int getCriticalChance(Player player) {
        UUID id = player.getUuid();
        int criticalChance;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT critical_chance FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                criticalChance = rs.getInt("critical_chance");
                return criticalChance;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's critical chance.
     * @param player The player to set the critical chance of.
     * @param criticalChance The critical chance to set the player to.
     */
    public void setCriticalChance(Player player, int criticalChance) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET critical_chance = ? WHERE uuid = ?");
            ps.setInt(1, criticalChance);
            ps.setString(2, id.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the specified player's critical damage.
     * @param player The player to get the critical damage of.
     * @return The critical damage of the player.
     */
    public int getCriticalDamage(Player player) {
        UUID id = player.getUuid();
        int criticalDamage;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT critical_damage FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                criticalDamage = rs.getInt("critical_damage");
                return criticalDamage;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the specified player's critical damage.
     * @param player The player to set the critical damage of.
     * @param criticalDamage The critical damage to set the player to.
     */
    public void setCriticalDamage(Player player, int criticalDamage) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET critical_damage = ? WHERE uuid = ?");
            ps.setInt(1, criticalDamage);
            ps.setString(2, id.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Gets the specified player's last login.
     * This method does not actually return the last ms they logged in at, it returns the last they logged off at.
     * @param player The player to get the last login of.
     */
    public Timestamp getLastLogin(Player player) {
        UUID id = player.getUuid();

        Timestamp lastLogin;
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT last_login FROM players WHERE uuid = ?");
            ps.setString(1, id.toString());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                lastLogin = rs.getTimestamp("last_login");
                return lastLogin;
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets the specified player's last login.
     * @param player The player to set the last login of.
     */
    public void setLastLogin(Player player) {
        UUID id = player.getUuid();
        long lastLogin = System.currentTimeMillis();
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE players SET last_login = ? WHERE uuid = ?");
            ps.setTimestamp(1, new Timestamp(lastLogin));
            ps.setString(2, id.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setDefaults(Player player) {
        UUID id = player.getUuid();
        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO players (uuid, rank, coins, level, xp, max_health, max_mana, defense, strength, critical_chance, critical_damage, last_login) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, id.toString());
            ps.setString(2, Rank.MEMBER.toString());
            ps.setInt(3, 0);
            ps.setInt(4, 1);
            ps.setInt(5, 0);
            ps.setInt(6, 100);
            ps.setInt(7, 100);
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            ps.setInt(10, 20);
            ps.setInt(11, 50);
            ps.setTimestamp(12, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
