package live.mufin.skyblock.items.abilities;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.Utils;
import live.mufin.skyblock.items.abilities.manager.Ability;
import live.mufin.skyblock.items.abilities.manager.ItemAbility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import static live.mufin.skyblock.items.abilities.Hyperion.teleportPlayer;

public class AspectOfTheEnd implements ItemAbility {

  @Override
  public void ItemAbility(Ability ability) {
    if (ability.getAction() != Action.RIGHT_CLICK_BLOCK && ability.getAction() != Action.RIGHT_CLICK_AIR) return;

    Player p = ability.getPlayer();
    int i = 5;
    Location loc = null;
    teleportPlayer(p, i);
  }

  @Override
  public String AbilityName() {
    return "Instant Transmission";
  }

  @Override
  public int ManaCost() {
    return 50;
  }

  @Override
  public Action[] Actions() {
    return new Action[]{Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR};
  }

  @Override
  public String targetItemName() {
    return "ASPECT_OF_THE_END";
  }
}
