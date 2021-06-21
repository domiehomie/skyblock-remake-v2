package live.mufin.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import live.mufin.skyblock.Main;

public class SkyblockCommand implements CommandExecutor {

	private Main plugin;

	public SkyblockCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		Player player = (Player) sender;
		if(label.equalsIgnoreCase("skyblock") || label.equalsIgnoreCase("sb")) {
			
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
					plugin.items.reloadConfig();
					plugin.utils.sendFormattedMessage(player, "&7Reloaded &aconfig.yml&7 and &aitems.yml&7.");
				}
			}else {
				plugin.utils.sendFormattedMessage(player, "&8========================================");
				plugin.utils.sendFormattedMessage(player, "&aSkyblock &7v&aBETA");
				plugin.utils.sendFormattedMessage(player, "&7Developed by &amufin&7.");
				plugin.utils.sendFormattedMessage(player, "&ahttps://mufin.live/");
				plugin.utils.sendFormattedMessage(player, "&7Use &a/help skyblock&7 for help or &a/skyblock reload&7 to");
				plugin.utils.sendFormattedMessage(player, "&7reload the configurations.");
				plugin.utils.sendFormattedMessage(player, "&8========================================");
			}
	
		}
		
		return true;
	}
	
}
