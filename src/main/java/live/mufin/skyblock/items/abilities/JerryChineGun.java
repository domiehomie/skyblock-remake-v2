package live.mufin.skyblock.items.abilities;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.items.abilities.manager.Ability;
import live.mufin.skyblock.items.abilities.manager.ItemAbility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.UUID;


public class JerryChineGun implements ItemAbility {


  @Override
  public void ItemAbility(Ability ability) {
    Location loc = ability.getPlayer().getLocation();
    ArmorStand entity = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
    ItemStack head = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta meta = (SkullMeta) head.getItemMeta();
    meta.setOwningPlayer(Bukkit.getOfflinePlayer("Villager"));
    head.setItemMeta(meta);
    entity.getEquipment().setHelmet(head);
    entity.setInvisible(true);
    entity.setInvulnerable(true);
    entity.setGravity(false);
    runJerry(entity);
  }

  private void runJerry(ArmorStand entity) {
      BukkitTask t = new BukkitRunnable() {
        @Override
        public void run() {
          entity.remove();
        }
      }.runTaskLater(Main.plugin, 400);

    new BukkitRunnable() {
      @Override
      public void run() {
        Location newloc = entity.getLocation().toVector().add(entity.getLocation().getDirection().multiply(0.5)).toLocation(entity.getWorld(), entity.getLocation().getYaw(), entity.getLocation().getPitch()).add(0, 0, 0);
        entity.teleport(newloc);
        if(entity.getLocation().add(0, 1, 0).getBlock().getType() != Material.AIR && entity.getLocation().add(0, 1, 0).getBlock().getType() != Material.WATER) {
          entity.getWorld().createExplosion(entity.getLocation(), 8f, false, true);
          entity.remove();
          t.cancel();
        }
        entity.getNearbyEntities(3, 3, 3).forEach(entity1 -> {
          if(entity1 instanceof LivingEntity && entity1.getType() != EntityType.PLAYER) {
            ((LivingEntity) entity1).damage(((LivingEntity) entity1).getHealth());
          }
        });
      }
    }.runTaskTimer(Main.plugin, 0, 1);
    }


  @Override
  public String AbilityName() {
    return "Rapid-fire";
  }

  @Override
  public int ManaCost() {
    return 10;
  }

  @Override
  public Action[] Actions() {
    return new Action[]{Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK};
  }

  @Override
  public String targetItemName() {
    return "JERRY_CHINE_GUN";
  }
}
