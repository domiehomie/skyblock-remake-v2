package live.mufin.skyblock.events;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.glow.GlowAPI;

public class ItemColourEvent implements Listener  {

    private ItemManager.Rarity rarity;
    private int i = 1;
    private final Main plugin;
    public ItemColourEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.getItemDrop().setGlowing(true);
        new BukkitRunnable() {
            @Override
            public void run() {

                NamespacedKey key = new NamespacedKey(plugin, "rarity");
                try{
                    if(event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING))
                        rarity = ItemManager.Rarity.valueOf(event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING));
                    else
                        rarity = ItemManager.Rarity.COMMON;
                }catch(IllegalArgumentException e) {

                }
                switch (rarity) {
                    default:
                        GlowAPI.setGlowing(event.getItemDrop(), GlowAPI.Color.WHITE, Bukkit.getOnlinePlayers());
                    case RARE:
                        GlowAPI.setGlowing(event.getItemDrop(), GlowAPI.Color.DARK_AQUA, Bukkit.getOnlinePlayers());
                        break;
                    case EPIC:
                        GlowAPI.setGlowing(event.getItemDrop(), GlowAPI.Color.DARK_PURPLE, Bukkit.getOnlinePlayers());
                        break;
                    case LEGENDARY:
                        GlowAPI.setGlowing(event.getItemDrop(), GlowAPI.Color.GOLD, Bukkit.getOnlinePlayers());
                        break;
                    case MYTHIC:
                        GlowAPI.setGlowing(event.getItemDrop(), GlowAPI.Color.PURPLE, Bukkit.getOnlinePlayers());
                        break;
                    case SPECIAL:
                        GlowAPI.setGlowing(event.getItemDrop(), GlowAPI.Color.RED, Bukkit.getOnlinePlayers());
                        break;
                    case RAINBOW:
                        rainbowGlow(event.getItemDrop());
                        return;
                    case COMMON:
                        GlowAPI.setGlowing(event.getItemDrop(), GlowAPI.Color.WHITE, Bukkit.getOnlinePlayers());
                        break;
                }


            }
        }.runTaskTimer(plugin, 0, 4);
    }

    private void rainbowGlow(Item item) {
        new BukkitRunnable() {
            public void run() {
                switch(i){
                    case 1:
                        GlowAPI.setGlowing(item, GlowAPI.Color.RED, Bukkit.getOnlinePlayers());
                        break;
                    case 2:
                        GlowAPI.setGlowing(item, GlowAPI.Color.DARK_RED, Bukkit.getOnlinePlayers());
                        break;
                    case 3:
                        GlowAPI.setGlowing(item, GlowAPI.Color.YELLOW, Bukkit.getOnlinePlayers());
                        break;
                    case 4:
                        GlowAPI.setGlowing(item, GlowAPI.Color.GOLD, Bukkit.getOnlinePlayers());
                        break;
                    case 5:
                        GlowAPI.setGlowing(item, GlowAPI.Color.AQUA, Bukkit.getOnlinePlayers());
                        break;
                    case 6:
                        GlowAPI.setGlowing(item, GlowAPI.Color.DARK_AQUA, Bukkit.getOnlinePlayers());
                        break;
                    case 7:
                        GlowAPI.setGlowing(item, GlowAPI.Color.BLUE, Bukkit.getOnlinePlayers());
                        break;
                    case 8:
                        GlowAPI.setGlowing(item, GlowAPI.Color.DARK_BLUE, Bukkit.getOnlinePlayers());
                        break;
                    case 9:
                        GlowAPI.setGlowing(item, GlowAPI.Color.PURPLE, Bukkit.getOnlinePlayers());
                        break;
                    case 10:
                        GlowAPI.setGlowing(item, GlowAPI.Color.DARK_PURPLE, Bukkit.getOnlinePlayers());
                        break;
                    case 11:
                        GlowAPI.setGlowing(item, GlowAPI.Color.WHITE, Bukkit.getOnlinePlayers());
                        break;
                    case 12:
                        GlowAPI.setGlowing(item, GlowAPI.Color.GRAY, Bukkit.getOnlinePlayers());
                        break;
                    case 13:
                        GlowAPI.setGlowing(item, GlowAPI.Color.DARK_GRAY, Bukkit.getOnlinePlayers());
                        break;
                    case 14:
                        GlowAPI.setGlowing(item, GlowAPI.Color.BLACK, Bukkit.getOnlinePlayers());
                        break;
                    case 15:
                        GlowAPI.setGlowing(item, GlowAPI.Color.GREEN, Bukkit.getOnlinePlayers());
                        break;
                    case 16:
                        GlowAPI.setGlowing(item, GlowAPI.Color.DARK_GREEN, Bukkit.getOnlinePlayers());
                        break;
                }
                i++;
                if(i==17) i = 1;
            }
        }.runTaskTimer(plugin, 0, 4);
    }

}
