package live.mufin.skyblock.items.abilities.manager;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.Utils;
import live.mufin.skyblock.items.abilities.AspectOfTheEnd;
import live.mufin.skyblock.items.abilities.Hyperion;
import live.mufin.skyblock.items.abilities.JerryChineGun;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbilityHandler implements Listener {

  private final ItemAbility[] abilities = new ItemAbility[]{new Hyperion(), new AspectOfTheEnd(), new JerryChineGun()};

  public void registerInteraction(ItemAbility ability, PlayerInteractEvent e) {
      if(!contains(e.getAction(), ability.Actions())) return;
      NamespacedKey nkey = new NamespacedKey(Main.plugin, "sbname");
      if(!ability.targetItemName().equals(e.getItem().getItemMeta().getPersistentDataContainer().get(nkey, PersistentDataType.STRING))) return;
      Player p = e.getPlayer();
      NamespacedKey key = new NamespacedKey(Main.plugin, "mana");
      double mana = p.getPersistentDataContainer().get(key, PersistentDataType.DOUBLE);
      if(mana < ability.ManaCost()) {
        System.out.println(ability.ManaCost());
        Utils.sendFormattedMessage(p, "&cYou do not have enough mana.");
      }else {
        p.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, mana - ability.ManaCost());
        ability.ItemAbility(new Ability(p, e.getAction(), e.getItem()));
        Utils.sendFormattedMessage(e.getPlayer(), "&7Used &a" + ability.AbilityName() + " &b(" + ability.ManaCost() + " mana)");
      }
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    if(e.getItem() == null) return;
    for(ItemAbility ability : abilities) {
      registerInteraction(ability, e);
    }
  }

  private static boolean contains(Action action, Action[] actions) {
    for(Action a : actions) {
      if(a == action) return true;
    }
    return false;
  }
}
