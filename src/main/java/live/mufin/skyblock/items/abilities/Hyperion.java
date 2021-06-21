package live.mufin.skyblock.items.abilities;

import live.mufin.skyblock.Utils;
import live.mufin.skyblock.items.abilities.manager.Ability;
import live.mufin.skyblock.items.abilities.manager.ItemAbility;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Hyperion implements ItemAbility {
  @Override
  public void ItemAbility(Ability ability) {
    Player p = ability.getPlayer();
    int i = 10;
    teleportPlayer(p, i);

    Location l = p.getLocation();
    p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 2);
    p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, l, 2);
    p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, l, 2);
    p.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
    p.getNearbyEntities(8, 8, 8).forEach((entity) -> {
      if (entity instanceof LivingEntity) {
        LivingEntity e = (LivingEntity) entity;
        e.damage(e.getHealth());
      }
    });
  }

  static void teleportPlayer(Player p, int i) {
    int n = i;
    while(n > 0) {
      Material m = p.getLocation().toVector().add(p.getLocation().getDirection().multiply(n)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch()).add(0, 1, 0).getBlock().getType();
      if(m == Material.AIR || m == Material.WATER) {
        Location newloc = p.getLocation().toVector().add(p.getLocation().getDirection().multiply(n)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch()).add(0, 1, 0);
        p.teleport(newloc, PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
        return;
      }
      n--;
    }
    String text = ChatColor.translateAlternateColorCodes('&', "&cYou cannot teleport through blocks!");
    TextComponent t = new TextComponent(TextComponent.fromLegacyText(text));
    p.spigot().sendMessage(ChatMessageType.CHAT, t);
  }

  @Override
  public String AbilityName() {
    return "Wither Impact";
  }

  @Override
  public int ManaCost() {
    return 250;
  }

  @Override
  public Action[] Actions() {
    return new Action[]{Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR};
  }

  @Override
  public String targetItemName() {
    return "HYPERION";
  }
}
