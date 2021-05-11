package live.mufin.skyblock.playerdata;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;
import org.bukkit.scheduler.BukkitRunnable;

import javax.naming.Name;

public class Stats {

	private Main plugin;

	public Stats(Main plugin) {
		this.plugin = plugin;
	}

	public enum Stat {
		DAMAGE, HEALTH, DEFENSE, TRUE_DEFENSE, STRENGTH, SPEED, CRIT_CHANCE, CRIT_DMG, INTELLIGENCE, MINING_SPEED,
		SEACREATURESPAWNRATE, MAGICFIND, PETLUCK, ABILITYDAMAGE, FEROCITY, MINING_FORTUNE, FARMING_FORTUNE,
		FORAGING_FORTUNE
	}

	/**
	 * Nicely formatted stats
	 * @param stat
	 * @param target
	 * @return The stat and value in formatted string.
	 */
	public String getStatString(Stat stat, Player target) {
		switch (stat) {
		case HEALTH:
			return "&c❤ Health &f"
					+ this.getStatValue(stat, target);
		case ABILITYDAMAGE:
			return "&c✹ Ability Damage &f"
					+ this.getStatValue(stat, target);

		case CRIT_CHANCE:
			return "&9☣ Crit Chance &f"
					+ this.getStatValue(stat, target);

		case CRIT_DMG:
			return "&9☠ Crit Damage &f"
					+ this.getStatValue(stat, target);

		case DAMAGE:
			return "&c❁ Damage &f"
					+ this.getStatValue(stat, target);

		case DEFENSE:
			return "&a❈ Defense &f"
					+ this.getStatValue(stat, target);

		case FARMING_FORTUNE:
			return "&6☘ Farming Fortune &f" + this.getStatValue(stat, target);

		case FEROCITY:
			return "&c⫽ Ferocity &f"
					+ this.getStatValue(stat, target);

		case FORAGING_FORTUNE:
			return "&6☘ Foraging Fortune &f" + this.getStatValue(stat, target);

		case INTELLIGENCE:
			return "&b✎ Intelligence &f"
					+ this.getStatValue(stat, target);

		case MAGICFIND:
			return "&b✯ Magic Find &f"
					+ this.getStatValue(stat, target);

		case MINING_FORTUNE:
			return "&6☘ Mining Fortune &f"
					+ this.getStatValue(stat, target);

		case MINING_SPEED:
			return "&6⸕ Mining Speed &f"
					+ this.getStatValue(stat, target);

		case PETLUCK:
			return "&d♣ Pet Luck &f"
					+ this.getStatValue(stat, target);

		case SEACREATURESPAWNRATE:
			return "&3α Sea Creature Chance &f" + this.getStatValue(stat, target);

		case SPEED:
			return "&f✦ Speed "
					+ this.getStatValue(stat, target);

		case STRENGTH:
			return "&c❁ Strength &f"
					+ this.getStatValue(stat, target);

		case TRUE_DEFENSE:
			return "&f❂ True Defense "
					+ this.getStatValue(stat, target);

		}
		return null;
	}

	/**
	 * Used to get the stat value of a player
	 * @param stat
	 * @param target
	 * @return value of the player's stat
	 */
	public double getStatValue(Stat stat, Player target) {
		NamespacedKey key = new NamespacedKey(plugin, stat.toString());
		NamespacedKey itemKey = new NamespacedKey(plugin, "item_" + stat.toString());
		if(target.getPersistentDataContainer().has(itemKey, PersistentDataType.DOUBLE)) {
			return target.getPersistentDataContainer().get(key, PersistentDataType.DOUBLE) + target.getPersistentDataContainer().get(itemKey, PersistentDataType.DOUBLE);
		}else {
			return target.getPersistentDataContainer().get(key, PersistentDataType.DOUBLE);
		}
		
	}

	public void runnable() {
		NamespacedKey currentHealthKey = new NamespacedKey(plugin, "currentHealth");
		NamespacedKey manaKey = new NamespacedKey(plugin, "mana");
		new BukkitRunnable() {
			/**
			 * ActionBar (Health, Defense and Mana)
			 */
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					PersistentDataContainer container = player.getPersistentDataContainer();
					double maxHealth = plugin.stats.getStatValue(Stat.HEALTH, player);
					double currentHealth = container.get(currentHealthKey, PersistentDataType.DOUBLE);
					double defense = plugin.stats.getStatValue(Stat.DEFENSE, player);
					double mana = container.get(manaKey, PersistentDataType.DOUBLE);
					double intelligence = plugin.stats.getStatValue(Stat.INTELLIGENCE, player);
					String text = ChatColor.RED + String.valueOf(Math.round(currentHealth)) + "/" + Math.round(maxHealth) + "❤    "  +
							ChatColor.GREEN + Math.round(defense) + "❈ Defense"
							+ ChatColor.AQUA + "    " + Math.round(mana + 100d) +  "/" + Math.round(intelligence + 100d) + "✎ Mana " ;
					TextComponent msg = new TextComponent(TextComponent
							.fromLegacyText(text));
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR, msg);
				}
			}
		}.runTaskTimer(plugin, 0, 1);

		new BukkitRunnable() {
			/**
			 * Mana regeneration
			 */
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					NamespacedKey manaKey = new NamespacedKey(plugin, "mana");
					if(player.getPersistentDataContainer().has(manaKey, PersistentDataType.DOUBLE)) {
						double mana = player.getPersistentDataContainer().get(manaKey, PersistentDataType.DOUBLE);
						double intelligence = plugin.stats.getStatValue(Stat.INTELLIGENCE, player);

						if(mana != intelligence) {
							mana = mana + (intelligence / 50);
							if(mana > intelligence) {
								mana = intelligence;
							}
							player.getPersistentDataContainer().set(manaKey, PersistentDataType.DOUBLE, mana);
						}
					}
				}
			}
		}.runTaskTimer(plugin, 0,20);
	}



}
