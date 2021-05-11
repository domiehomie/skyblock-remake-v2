package live.mufin.skyblock.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickupEvent implements Listener {
    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if(!(event.getEntity().getType() == EntityType.PLAYER)) return;
        Player player = (Player) event.getEntity();
        Item item = event.getItem();
        ItemStack itemStack = item.getItemStack();
        Bukkit.getLogger().info(itemStack.getItemMeta().getDisplayName());
    }
}
