package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import live.mufin.skyblock.Main;

public class ItemCommand implements CommandExecutor {

	private Main plugin;
	
	public ItemCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("item")) {
			if(!(sender instanceof Player)) return true;
			Player player = (Player) sender;
			if(args[0].equals("AIR")) {
				plugin.utils.sendFormattedMessage(player, "&cYou cannot give yourself Air lmao");
				return true;
			}
				if(args.length == 1) return false;
			if(args.length == 2) {
				if(plugin.items.getConfig().contains(args[0])) {
					try {
						int amount = Integer.parseInt(args[1]);
						ItemStack item = plugin.item.getItem(args[0]);
						item.setAmount(amount);
						player.getInventory().addItem(item);
						plugin.utils.sendFormattedMessage(player, "&7Given &a" + amount + "&7 of &r" + plugin.item.getItemName(args[0]) + "&7.");
					} catch(NumberFormatException e) {
						plugin.utils.sendFormattedMessage(player, "&cPlease input a valid amount.");
					}
				}else {
					plugin.utils.sendFormattedMessage(player, "&cInvalid Item.");
				}
			} else if(args.length == 3) {
				if(plugin.items.getConfig().contains(args[0])) {
					try {
						Player target = Bukkit.getPlayer(args[2]);
						int amount = Integer.parseInt(args[1]);
						ItemStack item = plugin.item.getItem(args[0]);
						item.setAmount(amount);
						target.getInventory().addItem(item);
						plugin.utils.sendFormattedMessage(player, "&7Given &a" + amount + "&7 of &r" + plugin.item.getItemName(args[0]) + "&7 to &a" + target.getName() + "&7.");
					} catch(NumberFormatException | NullPointerException e) {
						plugin.utils.sendFormattedMessage(player, "&cInvalid amount/player.");
					}
				}else {
					plugin.utils.sendFormattedMessage(player, "&cInvalid Item.");
				}
			}
			
			
			
		}
		return true;
	}
	
}
