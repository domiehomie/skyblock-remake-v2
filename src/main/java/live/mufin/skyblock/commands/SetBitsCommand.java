package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;

public class SetBitsCommand implements CommandExecutor{

	private Main plugin;
	private NamespacedKey key;

	public SetBitsCommand(Main plugin) {
		this.plugin = plugin;
		this.key = new NamespacedKey(plugin, "bits");
	}
	

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("setbits")) {
			if(!(sender instanceof Player))
				return true;
			
			
			Player player = (Player) sender;
			if(args.length < 2) return true;
			
			try {
				Player target = Bukkit.getPlayer(args[0]);
				int bits = Integer.parseInt(args[1]);
				target.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, bits);
				plugin.utils.sendFormattedMessage(player, "&7Set bits of &a" + target.getDisplayName() + "&7 to &b" + bits + "&7.");
				plugin.board.createBoard(target);
			} catch (NumberFormatException | NullPointerException e) {
				plugin.utils.sendFormattedMessage(player, "&cPlease input a valid number and player.");
			}
			
			
			
			
		}
		
		return true;
	}
	
}
