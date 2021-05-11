package live.mufin.skyblock.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.Stats.Stat;
import net.md_5.bungee.api.ChatColor;

public class ItemManager {

	private Main plugin;

	public ItemManager(Main plugin) {
		this.plugin = plugin;
	}

	public enum Rarity {
		COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC, SUPREME, SPECIAL, RAINBOW
	}

	/**
	 * Turn Skyblock item id into the name of the item.
	 * @param item
	 * @return Name of the item
	 */
	public String getItemName(String item) {
		if (plugin.items.getConfig().contains(item + ".name"))
			return ChatColor.translateAlternateColorCodes('&', plugin.items.getConfig().getString(item + ".name"));
		else
			return null;
	}

	/**
	 * Turn Skyblock item id into lore of the item.
	 * @param item
	 * @return Lore of the item.
	 */
	public List<String> getItemLore(String item) {
		List<String> lore = new ArrayList<String>();

		for (Stat stat : Stat.values()) {
			if (plugin.items.getConfig().contains(item + ".stats." + stat)) {
				
				switch(stat) {
				case DAMAGE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Damage: &c+" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case ABILITYDAMAGE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Ability Damage: &c" + plugin.items.getConfig().getDouble(item + ".stats." + stat) + "%"));
					break;
				case CRIT_CHANCE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Crit Chance: &c" + plugin.items.getConfig().getDouble(item + ".stats." + stat) + "%"));
					break;
				case CRIT_DMG:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Crit Damage: &c" + plugin.items.getConfig().getDouble(item + ".stats." + stat) + "%"));
					break;
				case STRENGTH:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Crit Damage: &c" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case FEROCITY:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&7Crit Damage: &c" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case DEFENSE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Defense: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case FARMING_FORTUNE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Farming Fortune: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case FORAGING_FORTUNE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Foraging Fortune: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case HEALTH:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Health: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case INTELLIGENCE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Intelligence: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case MAGICFIND:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Magic Find: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case MINING_FORTUNE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Mining Fortune: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case MINING_SPEED:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Mining Speed: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case PETLUCK:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Pet Luck: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case SEACREATURESPAWNRATE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Sea Creature Chance: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case SPEED:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7Speed: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				case TRUE_DEFENSE:
					lore.add(ChatColor.translateAlternateColorCodes('&',
							"&7True Defense: &a" + plugin.items.getConfig().getDouble(item + ".stats." + stat)));
					break;
				
				}
				
			}
		}


		if (plugin.items.getConfig().contains(item + ".lore")) {
			List<String> configLore = plugin.items.getConfig().getStringList(item + ".lore");
			for (String loreitem : configLore) {
				lore.add(ChatColor.translateAlternateColorCodes('&', loreitem));
			}

			if (plugin.items.getConfig().contains(item + ".rarity")) {

			}
		}
		if (plugin.items.getConfig().contains(item + ".rarity")) {
			try {
				Rarity rarity = Rarity.valueOf(plugin.items.getConfig().getString(item + ".rarity"));
				switch (rarity) {
				case COMMON:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&f&lCOMMON"));
					break;
				case UNCOMMON:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&a&lUNCOMMON"));
					break;
				case RARE:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&9&lRARE"));
					break;
				case EPIC:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&5&lEPIC"));
					break;
				case LEGENDARY:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&6&lLEGENDARY"));
					break;
				case MYTHIC:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&d&lMYTHIC"));
					break;
				case SUPREME:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&4&lSUPREME"));
					break;
				case SPECIAL:
					lore.add(ChatColor.translateAlternateColorCodes('&', "&c&lSPECIAL"));
					break;
				}
			} catch (IllegalArgumentException e) {

			}
		}

		return lore;
	}

	/**
	 * Turn Skyblck item id into Material
	 * @param item
	 * @return Material of the item.
	 */
	public Material getItemMaterial(String item) {
		if (plugin.items.getConfig().contains(item + ".material")) {
			try {
				return Material.valueOf(plugin.items.getConfig().getString(item + ".material"));
			} catch (IllegalArgumentException e) {
				return null;
			}
		} else
			return null;
	}

	/**
	 * Gives you item based on skyblock item id
	 * @param itemname
	 * @return Item from sbid
	 */
	public ItemStack getItem(String itemname) {
		if (plugin.items.getConfig().contains(itemname)) {
			if(itemname.equals("AIR")) return null;
			Material material = this.getItemMaterial(itemname);
			ItemStack item = new ItemStack(material);
			ItemMeta meta = item.getItemMeta();

			meta.setDisplayName(this.getItemName(itemname));
			meta.setLore(this.getItemLore(itemname));
			if (plugin.items.getConfig().getBoolean(itemname + ".enchanted")) {
				meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			}

			// Stats
			for (Stat stat : Stat.values()) {
				if (plugin.items.getConfig().contains(itemname + ".stats." + stat)) {
					NamespacedKey key = new NamespacedKey(plugin, stat.toString());
					meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE,
							plugin.items.getConfig().getDouble(itemname + ".stats." + stat));
				}
			}

			// SbName
			NamespacedKey sbNameKey = new NamespacedKey(plugin, "sbname");
			meta.getPersistentDataContainer().set(sbNameKey, PersistentDataType.STRING, itemname);

			// Rarity
			NamespacedKey rarityKey = new NamespacedKey(plugin, "rarity");
			if (plugin.items.getConfig().contains(itemname + ".rarity"))
				meta.getPersistentDataContainer().set(rarityKey, PersistentDataType.STRING,
						plugin.items.getConfig().getString(itemname + ".rarity"));
			item.setItemMeta(meta);
			return item;
		}
		return null;
	}

}
