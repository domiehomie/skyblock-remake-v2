package live.mufin.skyblock.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.Stats.Stat;

public class SkyblockMenu implements Listener {

	private Main plugin;
	public SkyblockMenu(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		NamespacedKey key = new NamespacedKey(plugin, "sbname");
		if(!event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
		if(event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING).equals("SKYBLOCK_MENU")) {
			event.setCancelled(true);
			this.createSkyblockMenu(event.getPlayer());
		}
	}
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem() == null || event.getItem().getItemMeta() == null) return;
		if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK
				|| event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			NamespacedKey sbNameKey = new NamespacedKey(plugin, "sbname");
			if(!event.getItem().getItemMeta().getPersistentDataContainer().has(sbNameKey, PersistentDataType.STRING)) return;
			if (event.getItem().getItemMeta().getPersistentDataContainer().get(sbNameKey, PersistentDataType.STRING).equals("SKYBLOCK_MENU")) {
				Player p = event.getPlayer();
				this.createSkyblockMenu(p);
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getSlot() == 8) {
			this.createSkyblockMenu((Player) event.getWhoClicked());
		}
		
		if(event.getView().getTitle().equals("SkyBlock Menu")) {
			event.setCancelled(true);
		}
	}

	/**
	 * Used to open skyblockmenu for a player.
	 * @param p
	 */
	public void createSkyblockMenu(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54, "SkyBlock Menu");
		
		ItemStack pane = plugin.item.getItem("GUI_PANE");
		inv.setItem(0, pane);
		inv.setItem(1, pane);
		inv.setItem(2, pane);
		inv.setItem(3, pane);
		inv.setItem(4, pane);
		inv.setItem(5, pane);
		inv.setItem(6, pane);
		inv.setItem(7, pane);
		inv.setItem(8, pane);
		inv.setItem(9, pane);
		inv.setItem(10, pane);
		inv.setItem(11, pane);
		inv.setItem(12, pane);
		inv.setItem(14, pane);
		inv.setItem(15, pane);
		inv.setItem(16, pane);
		inv.setItem(17, pane);
		inv.setItem(18, pane);
		inv.setItem(26, pane);
		inv.setItem(27, pane);
		inv.setItem(28, pane);
		inv.setItem(34, pane);
		inv.setItem(35, pane);
		inv.setItem(37, pane);
		inv.setItem(38, pane);
		inv.setItem(39, pane);
		inv.setItem(40, pane);
		inv.setItem(41, pane);
		inv.setItem(42, pane);
		inv.setItem(46, pane);
		
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setOwningPlayer(p);
		List<String> lore = new ArrayList<String>();
		for(Stat stat : Stat.values()) {
			lore.add(ChatColor.translateAlternateColorCodes('&', "  " + plugin.stats.getStatString(stat, p)));
		}
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GREEN + "Your SkyBlock Profile: ");
		head.setItemMeta(meta);
		
		inv.setItem(13, head);
		p.openInventory(inv);
	}
}
