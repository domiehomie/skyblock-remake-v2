package live.mufin.skyblock.playerdata;

import live.mufin.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLProfileGetter {

    public enum Profile {
        PROFILE1, PROFILE2, PROFILE3, PROFILE4, PROFILE5
    }

    private Main plugin;
    public SQLProfileGetter(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Creates table for when the server starts.
     */
    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS profiledata (UUID VARCHAR(100),PROFILE1 INT(100)," +
                    "PROFILE2 INT(100),PROFILE3 INT(100),PROFILE4 INT(100),PROFILE5 INT(100),PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a player to the database
     * @param player
     */
    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!exists(uuid)) {
                PreparedStatement ps = plugin.database.getConnection().prepareStatement("INSERT IGNORE INTO profiledata (UUID) VALUES (?)");
                ps.setString(1, uuid.toString());
                ps.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a uuid is in the database
     * @param uuid
     * @return true -> player is in the database || false -> player is not in the database
     */
    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("SELECT * FROM profiledata WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createProfile(UUID uuid, Profile profile, int id) {
        try{
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("UPDATE profiledata SET " + profile + "=? WHERE UUID=?");
            ps.setInt(1, id);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            this.createProfileWorld(Bukkit.getWorld("islandtemplate"), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createProfileWorld(World world, int id){
        File worldDir = world.getWorldFolder();
        String newName = "island_" + id;
        try {
            FileUtils.copyDirectory(worldDir, new File(worldDir.getParent(), newName));
            File file = new File(worldDir.getParent() + "/"+ newName + "/uid.dat");
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WorldCreator creator = new WorldCreator(newName);
        Bukkit.getServer().createWorld(WorldCreator.name(newName));
        World newWorld = Bukkit.createWorld(creator);
    }

    public void deleteProfile(UUID uuid, Profile profile) {
        try {
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(player.getWorld().getName().equals("island_" + this.getProfile(uuid, profile))){
                    player.teleport(Bukkit.getWorld("hub").getSpawnLocation());
                    plugin.utils.sendFormattedMessage(player,  "&cYou were teleported to the hub, because you were in a deleting world.");
                }
            }
            try{
                World world = Bukkit.getWorld("island_" + this.getProfile(uuid, profile));
                Bukkit.unloadWorld(world, true);
                try {
                    FileUtils.deleteDirectory(new File(world.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch(NullPointerException e) {

            }
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("UPDATE profiledata SET " + profile + "=0 WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getProfile(UUID uuid, Profile profile) {
        try {
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("SELECT " + profile + " FROM profiledata WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            int amount = 0;
            if (results.next()) {
                amount = results.getInt(profile.toString());
                return amount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
