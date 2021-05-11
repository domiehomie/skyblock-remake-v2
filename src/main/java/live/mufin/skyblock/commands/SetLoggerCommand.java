package live.mufin.skyblock.commands;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;

public class SetLoggerCommand implements CommandExecutor {

	private Main plugin;

	public SetLoggerCommand(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("setlogger") || label.equalsIgnoreCase("sl")) {
			if (!(sender instanceof Player))
				return true;
			Player player = (Player) sender;

			boolean value = Boolean.parseBoolean(args[1]);
			int valueInt = plugin.utils.booleanToInt(value);

			NamespacedKey itemDropsKey = new NamespacedKey(plugin, "logging_itemdrops");
			NamespacedKey itemClicksKey = new NamespacedKey(plugin, "logging_itemclicks");

			if (args[0].equalsIgnoreCase("itemclicks"))
				if (player.getPersistentDataContainer().has(itemClicksKey, PersistentDataType.INTEGER)) {
					player.getPersistentDataContainer().set(itemClicksKey, PersistentDataType.INTEGER, valueInt);
					plugin.utils.sendLoggerMessage(player);
					return true;
				}

			if (args[0].equalsIgnoreCase("itemdrops"))
				if (player.getPersistentDataContainer().has(itemDropsKey, PersistentDataType.INTEGER)) {
					player.getPersistentDataContainer().set(itemDropsKey, PersistentDataType.INTEGER, valueInt);
					plugin.utils.sendLoggerMessage(player);
					return true;
				}

			
		}

		return true;
	}

}
