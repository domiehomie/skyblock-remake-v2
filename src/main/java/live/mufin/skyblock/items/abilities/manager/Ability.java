package live.mufin.skyblock.items.abilities.manager;

import live.mufin.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class Ability {
  Player player;
  Action action;
  String itemid;

  @Override
  public String toString() {
    return "Ability{" +
            "player=" + player +
            ", action=" + action +
            ", itemid='" + itemid + '\'' +
            ", item=" + item +
            '}';
  }

  ItemStack item;

  public Ability(Player p, Action a, ItemStack i) {
    this.player = p;
    this.action = a;
    this.item = i;
    NamespacedKey k = new NamespacedKey(Main.plugin, "sbname");
//    if(item.getItemMeta().getPersistentDataContainer().has(k, PersistentDataType.STRING)) {
//      throw new IllegalStateException("Item is not a skyblock item.");
//    }
    this.itemid = item.getItemMeta().getPersistentDataContainer().get(k, PersistentDataType.STRING);
  }

  public Player getPlayer() {
    return player;
  }

  public Action getAction() {
    return action;
  }

  public String getItemid() {
    return itemid;
  }

  public ItemStack getItem() {
    return item;
  }
}
