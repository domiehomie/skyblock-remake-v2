package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.Stats.Stat;

public class StatsCommand implements CommandExecutor {

	private Main plugin;

	public StatsCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("stats")) {
			if (!(sender instanceof Player))
				return true;

			Player player = (Player) sender;

			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				plugin.utils.sendFormattedMessage(player, "&7Stats for &a" + target.getDisplayName() + "&7:");
				
				for(Stat stat : Stat.values()) {
					plugin.utils.sendFormattedMessage(player, plugin.stats.getStatString(stat, target));
				}
				return true;
			}

			plugin.utils.sendFormattedMessage(player, "&7Stats for &a" + player.getDisplayName() + "&7:");

			for(Stat stat : Stat.values()) {
				plugin.utils.sendFormattedMessage(player, plugin.stats.getStatString(stat, player));
			}

			return true;
		}

		return true;
	}

}
