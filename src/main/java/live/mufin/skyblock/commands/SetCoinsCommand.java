package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;

public class SetCoinsCommand implements CommandExecutor{

	private Main plugin;
	private NamespacedKey key;

	public SetCoinsCommand(Main plugin) {
		this.plugin = plugin;
		this.key = new NamespacedKey(plugin, "coins");
	}
	

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("setcoins") || label.equalsIgnoreCase("setbalance")) {
			if(!(sender instanceof Player))
				return true;
			
			
			Player player = (Player) sender;
			if(!(args.length == 2))
				return false;
			
			try {
				Player target = Bukkit.getPlayer(args[0]);
				Long coins = Long.parseLong(args[1]);
				target.getPersistentDataContainer().set(key, PersistentDataType.LONG, coins);
				plugin.utils.sendFormattedMessage(player, "&7Set coins of &a" + target.getDisplayName() + "&7 to &6" + coins + "&7.");
				plugin.board.createBoard(target);
			} catch (NumberFormatException | NullPointerException e) {
				plugin.utils.sendFormattedMessage(player, "&cPlease input a valid number and player.");
			}
			
			
			
			
		}
		
		return true;
	}
	
}
