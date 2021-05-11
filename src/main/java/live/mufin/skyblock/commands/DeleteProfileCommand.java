package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.SQLProfileGetter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteProfileCommand implements CommandExecutor {

    private Main plugin;
    public DeleteProfileCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        try {
            SQLProfileGetter.Profile profile = SQLProfileGetter.Profile.valueOf(args[0]);
            plugin.profiles.deleteProfile(player.getUniqueId(), profile);
        } catch(IllegalArgumentException e) {
            plugin.utils.sendFormattedMessage(player, "&cInvalid profile.");
        }
        return true;
    }
}
