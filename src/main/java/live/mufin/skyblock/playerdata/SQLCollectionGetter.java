package live.mufin.skyblock.playerdata;

import com.google.common.base.Joiner;
import live.mufin.skyblock.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLCollectionGetter {


    public enum Collection {
        FARMING_WHEAT, FARMING_CARROT, FARMING_POTATO, FARMING_PUMPKIN, FARMING_MELON, FARMING_SEEDS, FARMING_MUSHROOM, FARMING_COCOABEAN, FARMING_CACTUS, FARMING_SUGARCANE, FARMING_FEATHER,
        FARMING_LEATHER, FARMING_PORKCHOP, FARMING_CHICKEN, FARMING_MUTTON, FARMING_RABBIT, FARMING_NETHERWART,

        MINING_COBBLESTONE, MINING_COAL, MINING_IRON, MINING_GOLD, MINING_DIAMOND, MINING_LAPIS, MINING_EMERALD, MINING_REDSTONE, MINING_QUARTZ, MINING_OBSIDIAN, MINING_GLOWSTONE, MINING_GRAVEL,
        MINING_ICE, MINING_NETHERRACK, MINING_SAND, MINING_ENDSTONE, MINING_MITHRIL,

        COMBAT_ROTTENFLESH, COMBAT_BONE, COMBAT_STRING, COMBAT_SPIDEREYE, COMBAT_GUNPOWDER, COMBAT_ENDERPEARL, COMBAT_GHASTTEAR, COMBAT_SLIMEBALL, COMBAT_BLAZEROD, COMBAT_MAGMACREAM,

        FORAGING_OAK, FORAGING_SPRUCE, FORAGING_BIRCH, FORAGING_DARKOAK, FORAGING_ACACIA, FORAGING_JUNGLE,

        FISHING_RAWFISH, FISHING_RAWSALMON, FISHING_CLOWNFISH, FISHING_PUFFERFISH, FISHING_PRISMARINESHARD, FISHING_PRISMARINECRYSTALS, FISHING_CLAY, FISHING_LILYPAD, FISHING_INKSACK, FISHING_SPONGE,
    }

    private Main plugin;

    public SQLCollectionGetter(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Used to format all collections into MySQL form, dynamically
     * @return String for preparedstatement
     */
    private String getCollections() {
        List<String> collections = new ArrayList<String>();
        for (Collection collection : Collection.values()) {
            collections.add(collection.toString() + " INT(100) DEFAULT 0");
        }
        String string = "(" + "UUID VARCHAR(100)," + String.join(",", collections) + ",PRIMARY KEY(UUID))";
        return string;
    }


    /**
     * Creates table for when the server starts.
     */
    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS collectiondata " + this.getCollections());
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
                PreparedStatement ps = plugin.database.getConnection().prepareStatement("INSERT IGNORE INTO collectiondata (UUID) VALUES (?)");
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
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("SELECT * FROM collectiondata WHERE UUID=?");
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

    /**
     * Used to add items to a collection.
     * @param uuid
     * @param collection
     * @param amount
     */
    public void addCollection(UUID uuid, Collection collection, int amount) {
        try {
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("UPDATE collectiondata SET " + collection + "=? WHERE UUID=?");
            ps.setInt(1, this.getCollection(uuid, collection));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to set the value of a player's collection
     * @param uuid
     * @param collection
     * @param amount
     */
    public void setCollection(UUID uuid, Collection collection, int amount) {
        try {
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("UPDATE collectiondata SET " + collection + "=? WHERE UUID=?");
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Used to get the value of a player's collection
     * @param uuid
     * @param collection
     * @return
     */
    public int getCollection(UUID uuid, Collection collection) {
        try {
            PreparedStatement ps = plugin.database.getConnection().prepareStatement("SELECT " + collection + " FROM collectiondata WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            int amount = 0;
            if (results.next()) {
                amount = results.getInt(collection.toString());
                return amount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
