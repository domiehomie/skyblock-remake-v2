package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import live.mufin.skyblock.Main;

public class ScoreboardReloadCommand implements CommandExecutor{

	private Main plugin;

	public ScoreboardReloadCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("sbrl") || label.equalsIgnoreCase("rlsb") || label.equalsIgnoreCase("scoreboardreload") || label.equalsIgnoreCase("reloadscoreboard")) {
			if(!(sender instanceof Player)) return true;
			Player player = (Player) sender;
			if(args.length == 1) {
				try {
					Player target = Bukkit.getPlayer(args[0]);
					this.plugin.board.createBoard(target);
					plugin.utils.sendFormattedMessage(player, "&7Reloaded scoreboard of &a" + target.getName() + "&7.");
				} catch (NullPointerException e ) {
					plugin.utils.sendFormattedMessage(player, "&cPlease specify a player.");
				}
			}else {
				plugin.utils.sendFormattedMessage(player, "&cPlease specify a player.");
			}
		}
		return true;
	}
	
}
