package live.mufin.skyblock.events;


import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.SQLProfileGetter;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.persistence.PersistentDataType;

public class EnterPortalEvent implements Listener {

    private Main plugin;
    public EnterPortalEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEnterPortal(PlayerPortalEvent event) {

        /**
         * Teleport to island
         */
        if(event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            NamespacedKey key = new NamespacedKey(plugin, "profile");
            try{
                SQLProfileGetter.Profile profile = SQLProfileGetter.Profile.valueOf(player.getPersistentDataContainer().get(key, PersistentDataType.STRING));
                String world = "island_" + plugin.profiles.getProfile(player.getUniqueId(), profile);
                Bukkit.dispatchCommand(player, "goto " + world);
            } catch (IllegalArgumentException e) {
                event.setCancelled(true);
            }
        }
        /**
         * teleport to hub
         */
        else if(event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            player.performCommand("goto hub");
        }
    }

}
