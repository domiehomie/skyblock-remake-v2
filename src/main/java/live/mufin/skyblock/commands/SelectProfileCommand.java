package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.SQLProfileGetter;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class SelectProfileCommand implements CommandExecutor {

    private Main plugin;

    public SelectProfileCommand(Main plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("switchprofile")) {
            if (!(sender instanceof Player)) return true;
            Player player = (Player) sender;
            try{
                NamespacedKey key = new NamespacedKey(plugin, "profile");
                SQLProfileGetter.Profile profile = SQLProfileGetter.Profile.valueOf(args[0]);
                if(plugin.profiles.getProfile(player.getUniqueId(), profile) != 0) {
                    player.getPersistentDataContainer().set(key, PersistentDataType.STRING, profile.toString());
                }
                plugin.utils.sendFormattedMessage(player, "&7Your profile has been set to &a" + profile + "&7.");
            } catch(IllegalArgumentException e) {
                plugin.utils.sendFormattedMessage(player, "&cInvalid profile.");
            }

        }
        return true;
    }
}
