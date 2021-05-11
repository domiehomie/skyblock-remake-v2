package live.mufin.skyblock.items.abilities;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.Stats.Stat;

public class ItemStats {

	private Main plugin;

	public ItemStats(Main plugin) {
		this.plugin = plugin;
	}


	/**
	 * Runs every tick to make sure your stats are amplified correctly depending on the item you hold.
	 */
	public void runnable() {
		new BukkitRunnable() {

			@Override
			public void run() {
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {

					if (p.getInventory().getItemInMainHand() == null
							|| p.getInventory().getItemInMainHand().getItemMeta() == null) {
						for (Stat stat : Stat.values()) {
							NamespacedKey key = new NamespacedKey(plugin, "item_" + stat.toString());
							p.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, 0.0d);
						}
						return;
					}

					ItemStack currentItem = p.getInventory().getItemInMainHand();
					for (Stat stat : Stat.values()) {
						NamespacedKey itemKey = new NamespacedKey(plugin, "item_" + stat.toString());
						NamespacedKey key = new NamespacedKey(plugin, stat.toString());
						if (currentItem.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
							p.getPersistentDataContainer().set(itemKey, PersistentDataType.DOUBLE, currentItem.getItemMeta()
									.getPersistentDataContainer().get(key, PersistentDataType.DOUBLE));
						}
					}
				}
			}

		}.runTaskTimer(plugin, 0, 1);
	}

}
