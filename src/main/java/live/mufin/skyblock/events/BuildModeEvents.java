package live.mufin.skyblock.events;

import live.mufin.skyblock.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import javax.xml.stream.events.Namespace;

public class BuildModeEvents implements Listener {

    private Main plugin;
    private NamespacedKey key;
    public BuildModeEvents(Main plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "buildmode");
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if(player.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)){
            if(plugin.utils.intToBoolean(player.getPersistentDataContainer().get(key, PersistentDataType.INTEGER)))
                e.setCancelled(true);
            return;
        }
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if(player.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)){
            if(plugin.utils.intToBoolean(player.getPersistentDataContainer().get(key, PersistentDataType.INTEGER)))
                e.setCancelled(true);
            return;
        }
    }

}
