package quest.highworld.data;


import net.minestom.server.MinecraftServer;
import quest.highworld.Highworld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private final Connection connection = connect();

    public Database() {
        createTables();
    }
    private void createTables() {
        try {

            PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS players " +
                    "(uuid VARCHAR(255) PRIMARY KEY," +
                    " rank VARCHAR(255), coins INT," +
                    " level INT, xp INT," +
                    " max_health INT," +
                    " max_mana INT," +
                    " defense INT," +
                    " strength INT," +
                    " critical_chance INT," +
                    " critical_damage INT," +
                    " last_login DATETIME)");
            ps.execute();
        }
        catch(SQLException e) {
            MinecraftServer.LOGGER.error("Error while creating tables", e);
        }
    }
    private Connection connect() {

        // SQLite
        String url = "jdbc:sqlite:" + Highworld.folder().getPath() + "/highworld.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            MinecraftServer.LOGGER.error("Error while connecting to the database", e);
        }
        return conn;
    }

    public Connection getConnection() {return this.connect();}

}
