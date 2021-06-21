package live.mufin.skyblock.items.abilities;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import live.mufin.skyblock.Main;

public class MushroomSoup implements Listener {

	private Main plugin;
	private NamespacedKey key;

	public MushroomSoup(Main plugin) {
		this.plugin = plugin;
		this.key = new NamespacedKey(plugin, "flightduration");
	}

	

	@EventHandler
	public void onEatSoup(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = event.getPlayer();
			ItemStack i = event.getItem();
			if (i == null)
				return;
			NamespacedKey sbNameKey = new NamespacedKey(plugin, "sbname");
			if (!i.getItemMeta().getPersistentDataContainer().has(sbNameKey, PersistentDataType.STRING))
				return;
			if (i.getItemMeta().getPersistentDataContainer().get(sbNameKey, PersistentDataType.STRING)
					.equals("MAGICAL_MUSHROOM_SOUP")) {
				i.setAmount(i.getAmount() - 1);
				p.getInventory().setItem(p.getInventory().getHeldItemSlot(), i);
				p.setAllowFlight(true);
				p.setFlying(true);
				plugin.utils.sendFormattedMessage(p,
						"&aYou drank a mysterious soup and gained &c2&a minutes of flight.");

				p.getPersistentDataContainer().set(key, PersistentDataType.LONG,
						p.getPersistentDataContainer().get(key, PersistentDataType.LONG) + 120);
				plugin.board.createBoard(p);
			}
		}
	}

	public void runnable() {
		new BukkitRunnable() {
			
			@Override
			/**
			 * Runs every second to tick down flight duration for people that are on private islands.
			 */
			public void run() {
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					try {
						long time = p.getPersistentDataContainer().get(key, PersistentDataType.LONG);
						if (time != 0) {
							if (p.getWorld().getName().startsWith("island_")) {
								p.getPersistentDataContainer().set(key, PersistentDataType.LONG, time - 1);
								plugin.board.createBoard(p);
								if(time < 100) {
									String stringTime = String.valueOf(time);
									int intTime = Integer.parseInt(stringTime);
									switch (intTime) {
									case 60:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 60 seconds!");
										break;
									case 30:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 30 seconds!");
										break;
									case 10:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 10 seconds!");
										break;
									case 5:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 5 seconds!");
										break;
									case 4:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 4 seconds!");
										break;
									case 3:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 3 seconds!");
										break;
									case 2:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 2 seconds!");
										break;
									case 1:
										plugin.utils.sendFormattedMessage(p, "&cYour flight will run out in 1 second!");
										p.setFlying(false);
										p.setAllowFlight(false);
										break;
								}
								

								}

							}

						}
					} catch (NullPointerException e) {

					}
				}
			}

		}.runTaskTimer(plugin, 0, 20);
	}
}
