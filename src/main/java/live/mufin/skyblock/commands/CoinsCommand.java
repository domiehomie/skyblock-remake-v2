package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;

public class CoinsCommand implements CommandExecutor {

	private Main plugin;
	private NamespacedKey key;

	public CoinsCommand(Main plugin) {
		this.plugin = plugin;
		this.key = new NamespacedKey(plugin, "coins"); // init coins from playernbt
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("coins") || label.equalsIgnoreCase("balance")) {
			if(!(sender instanceof Player))
				return true;
			
			Player player = (Player) sender;
			if(args.length == 1) {
				try{
					Player target = Bukkit.getPlayer(args[0]);
					plugin.utils.sendFormattedMessage(player, "&7Coins for &a" + target.getDisplayName() + "&7: &6" + target.getPersistentDataContainer().get(key, PersistentDataType.LONG));

				} catch (NullPointerException e) { // checks if player is real
					plugin.utils.sendFormattedMessage(player, "&cInvalid player.");
				}
				}else {
			plugin.utils.sendFormattedMessage(player, "&7Coins for &a" + player.getDisplayName() + "&7: &6" + player.getPersistentDataContainer().get(key, PersistentDataType.LONG));
			}
		}

		return true;
	}
	
}
