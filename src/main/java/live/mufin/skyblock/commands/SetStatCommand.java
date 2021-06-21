package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.Stats.Stat;

import javax.naming.Name;

public class SetStatCommand implements CommandExecutor {

	private Main plugin;

	public SetStatCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;

		Player player = (Player) sender;
		if (label.equalsIgnoreCase("setstat")) {
			if (!(args.length == 3)) {
				plugin.utils.sendFormattedMessage(player,
						"&7Incorrect usage. Use &a/help skyblock&7 or &a/help setstat&7 for more help.");
			} else {
				Player target = Bukkit.getPlayer(args[0]);

				try {
					Double value = Double.parseDouble(args[2]);
					Stat stat = Stat.valueOf(args[1]);
					NamespacedKey key = new NamespacedKey(plugin, stat.toString());
					target.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, value);

					plugin.utils.sendFormattedMessage(player, "&7Set the stat &a" + stat.toString() + "&7 of player &a"
							+ target.getName() + "&7 to &a" + value.toString() + "&7.");
				} catch (IllegalArgumentException | NullPointerException e) {
					plugin.utils.sendFormattedMessage(player,
							"&7Incorrect arguments. Use &a/liststats&7 for a list of stats or &a/help setstat&7.");
				}

			}

		}

		return true;
	}

}
