package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.SQLProfileGetter;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;

public class IslandCommand implements CommandExecutor {

    private Main plugin;
    public IslandCommand(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("profile") || label.equalsIgnoreCase("is")) {
            if(!(sender instanceof Player)) return true;
            NamespacedKey key = new NamespacedKey(plugin, "profile");
            Player player = (Player) sender;
            if(player.getPersistentDataContainer().has(key, PersistentDataType.STRING)){
                try{
                    SQLProfileGetter.Profile profile = SQLProfileGetter.Profile.valueOf(player.getPersistentDataContainer().get(key, PersistentDataType.STRING));
                    World world = Bukkit.getWorld("island_" + plugin.profiles.getProfile(player.getUniqueId(), profile));
                    player.teleport(world.getSpawnLocation());
                    plugin.utils.sendFormattedMessage(player, "&7You have been sent to &ayour island&7.");
                } catch(IllegalArgumentException e) {
                    plugin.utils.sendFormattedMessage(player, "&cYour profile is invalid! Please select another.");
                }
            }else {
               plugin.utils.sendFormattedMessage(player, "&cYou do not have any profile selected.");
            }


        }

        return true;
    }
}
