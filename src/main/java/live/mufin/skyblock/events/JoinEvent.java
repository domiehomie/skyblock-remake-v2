package live.mufin.skyblock.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.Stats.Stat;

public class JoinEvent implements Listener {

    private Main plugin;

    public JoinEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        /**
         * Getting required variables
         */
        Player player = event.getPlayer();
        PersistentDataContainer container = player.getPersistentDataContainer();
        NamespacedKey coinsKey = new NamespacedKey(plugin, "coins");
        NamespacedKey bitsKey = new NamespacedKey(plugin, "bits");
        NamespacedKey flightDurationKey = new NamespacedKey(plugin, "flightduration");
        NamespacedKey buildModeKey = new NamespacedKey(plugin, "buildmode");

        /**
         * Sets data for new players
         */
        if (!container.has(coinsKey, PersistentDataType.LONG))
            container.set(coinsKey, PersistentDataType.LONG, 0L);
        if (!container.has(buildModeKey, PersistentDataType.INTEGER))
            container.set(buildModeKey, PersistentDataType.INTEGER, 0);
        if (!container.has(bitsKey, PersistentDataType.INTEGER))
            container.set(bitsKey, PersistentDataType.INTEGER, 0);
        if (!container.has(flightDurationKey, PersistentDataType.LONG))
            container.set(flightDurationKey, PersistentDataType.LONG, 0L);

        for (Stat stat : Stat.values()) {
            NamespacedKey key = new NamespacedKey(plugin, stat.toString());
            if (!container.has(key, PersistentDataType.DOUBLE))
                container.set(key, PersistentDataType.DOUBLE, 0.0D);
        }
        for (Stat stat : Stat.values()) {
            NamespacedKey key = new NamespacedKey(plugin, "item_" + stat.toString());
            if (!container.has(key, PersistentDataType.DOUBLE)) {
                container.set(key, PersistentDataType.DOUBLE, 0.0D);
            }
        }


        NamespacedKey itemDropsKey = new NamespacedKey(plugin, "logging_itemdrops");
        NamespacedKey itemClicksKey = new NamespacedKey(plugin, "logging_itemclicks");
        if (!container.has(itemDropsKey, PersistentDataType.INTEGER))
            container.set(itemDropsKey, PersistentDataType.INTEGER, 0);
        if (!container.has(itemClicksKey, PersistentDataType.INTEGER))
            container.set(itemClicksKey, PersistentDataType.INTEGER, 0);

        /**
         * Sets flying true if you have flight duration.
         */
        if (container.has(flightDurationKey, PersistentDataType.LONG)) {
            if (container.get(flightDurationKey, PersistentDataType.LONG) != 0) {
                player.setAllowFlight(true);
                player.setFlying(true);
            }
        }

        /**
         * Sets current health to correct value
         */
        NamespacedKey maxHealthKey = new NamespacedKey(plugin, Stat.HEALTH.toString());
        NamespacedKey currentHealthKey = new NamespacedKey(plugin, "currentHealth");
        if(container.has(maxHealthKey, PersistentDataType.DOUBLE))
            container.set(currentHealthKey, PersistentDataType.DOUBLE, container.get(maxHealthKey, PersistentDataType.DOUBLE));

        NamespacedKey manaKey = new NamespacedKey(plugin, "mana");
        NamespacedKey intelligenceKey = new NamespacedKey(plugin, Stat.INTELLIGENCE.toString());
        if(container.has(intelligenceKey, PersistentDataType.DOUBLE))
            container.set(manaKey, PersistentDataType.DOUBLE, container.get(intelligenceKey, PersistentDataType.DOUBLE));


        /**
         * Give skyblockmenu to player
         */
        ItemStack skyblockmenu = plugin.item.getItem("SKYBLOCK_MENU");
        if (!(player.getInventory().contains(skyblockmenu)))
            player.getInventory().setItem(8, skyblockmenu);

        plugin.collections.createPlayer(player);
        plugin.profiles.createPlayer(player);

        // Create scoreboard
        plugin.board.createBoard(player);

    }

}
