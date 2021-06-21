package live.mufin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import live.mufin.skyblock.Main;

public class SetFlightCommand implements CommandExecutor {

	private Main plugin;
	public SetFlightCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(label.equalsIgnoreCase("setflight")) {
			if(!(sender instanceof Player)) return true;
				if(args.length != 2) return false;
				
				Player player = (Player) sender;
				try {
					NamespacedKey key = new NamespacedKey(plugin, "flightduration");
					Player target = Bukkit.getPlayer(args[0]);
					long duration = Long.parseLong(args[1]);
					if(duration == 0) plugin.utils.sendFormattedMessage(player, "&cFlight duration cannot be set to 0.");
					target.getPersistentDataContainer().set(key, PersistentDataType.LONG, duration);
					plugin.utils.sendFormattedMessage(player, "&7Set flight duration of &a" + target.getName() + "&7 to &a" + duration + "&7.");
					target.setAllowFlight(true);
					target.setFlying(true);
				} catch (IllegalArgumentException | NullPointerException e) {
					plugin.utils.sendFormattedMessage(player, "&cInvalid player/value.");
				}
				
		}
		
		return true;
	}
	
}
