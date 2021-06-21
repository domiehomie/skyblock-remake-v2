package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class BuildCommand implements CommandExecutor {

    private Main plugin;
    public BuildCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("build")) {
            NamespacedKey key = new NamespacedKey(plugin, "buildmode"); // init buildmode from playernbt
            if(!(sender instanceof Player)) return true;
            Player player = (Player) sender;
            if(player.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
                boolean value = plugin.utils.intToBoolean(player.getPersistentDataContainer().get(key, PersistentDataType.INTEGER)); // turns int from playernbt into boolean
                // sets build mode depending on if it was on or off
                if (value) {
                    player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 0);
                    plugin.utils.sendFormattedMessage(player, "&aEnabled &7build mode.");
                } else {
                    player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
                    plugin.utils.sendFormattedMessage(player, "&cDisabled &7build mode.");
                }
            }
        }
        return true;

    }
}
