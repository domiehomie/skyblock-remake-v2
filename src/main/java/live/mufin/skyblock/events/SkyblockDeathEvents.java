package live.mufin.skyblock.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;
import net.md_5.bungee.api.ChatColor;

public class SkyblockDeathEvents implements Listener{
	

	private NamespacedKey deathKey;
	
	public SkyblockDeathEvents(Main plugin) {
		this.deathKey = new NamespacedKey(plugin, "death");
	}
	
	// VOID
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		Location l = p.getLocation();
		if(l.getY() <= 1) {
			this.setDeath(p, DeathCause.VOID);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		this.setDeath(p, DeathCause.OTHER);
	}
	
	
	public enum DeathCause {
		FALL,
		SUFFOCATE,
		EXPLOSION,
		FIRE,
		VOID,
		OTHER
	}

	/**
	 * Use when you want a player to die.
	 * @param p
	 * @param cause
	 */
	public void setDeath(Player p, DeathCause cause) {
		switch(cause) {
		case FALL:
			Bukkit.getServer().broadcastMessage(p.getDisplayName() + ChatColor.GRAY + " has fallen to their death.");
			break;
		case EXPLOSION:
			Bukkit.getServer().broadcastMessage(p.getDisplayName() + ChatColor.GRAY + " has exploded.");
			break;
		case FIRE:
			Bukkit.getServer().broadcastMessage(p.getDisplayName() + ChatColor.GRAY + " has burnt to death.");
			break;
		case OTHER:
			Bukkit.getServer().broadcastMessage(p.getDisplayName() + ChatColor.GRAY + " died.");
			break;
		case SUFFOCATE:
			Bukkit.getServer().broadcastMessage(p.getDisplayName() + ChatColor.GRAY + " suffocated to death.");
			break;
		case VOID:
			Bukkit.getServer().broadcastMessage(p.getDisplayName() + ChatColor.GRAY + " fell into the void.");
			break;
		}
		p.teleport(p.getLocation().getWorld().getSpawnLocation());
		p.getPersistentDataContainer().set(deathKey, PersistentDataType.INTEGER, 1);
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (!(event.getEntityType() == EntityType.PLAYER)) return;
		if(!(event.getCause() == DamageCause.FALL)) return;
		Player p = (Player) event.getEntity();
		if(p.getPersistentDataContainer().has(deathKey, PersistentDataType.INTEGER)) {
			if (p.getPersistentDataContainer().get(deathKey, PersistentDataType.INTEGER) == 0) return;
		}
		event.setCancelled(true);
		p.getPersistentDataContainer().set(deathKey, PersistentDataType.INTEGER, 0);
	}
	

	
	
	
}
