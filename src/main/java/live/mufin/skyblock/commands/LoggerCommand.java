package live.mufin.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import live.mufin.skyblock.Main;

public class LoggerCommand implements CommandExecutor {

	private Main plugin;

	public LoggerCommand(Main plugin) {
		this.plugin = plugin;
	}

	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;
		plugin.utils.sendLoggerMessage(player);
		return true;
	}

}
