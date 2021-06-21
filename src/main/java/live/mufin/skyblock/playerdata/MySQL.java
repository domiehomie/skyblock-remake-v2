package live.mufin.skyblock.playerdata;

import live.mufin.skyblock.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Main plugin;

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private boolean useSSL;

    private Connection connection;

    public MySQL(Main plugin) {
        this.plugin = plugin;

        this.host = plugin.getConfig().getString("sql.host");
        this.port = plugin.getConfig().getString("sql.port");
        this.database = plugin.getConfig().getString("sql.database");
        this.username = plugin.getConfig().getString("sql.username");
        this.password = plugin.getConfig().getString("sql.password");
        this.useSSL = plugin.getConfig().getBoolean("sql.usessl");


    }

    public boolean isConnected() {
        return (connection == null ? false : true);
    }


    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected())
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL, username, password);
    }

    public void disconnect() {
        if(isConnected())
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
    }

    public Connection getConnection() {
        return connection;
    }


}
