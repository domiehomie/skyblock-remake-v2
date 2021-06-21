package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.SQLProfileGetter;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class CreateProfileCommand implements CommandExecutor {

    private Main plugin;
    public CreateProfileCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("createprofile")) {
            if (!(sender instanceof Player)) return true;
            Player player = (Player) sender;

            Random random = new Random();
            int id = random.nextInt(999999998) + 1;
            NamespacedKey key = new NamespacedKey(plugin, "profile");
            if (plugin.profiles.getProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE1) == 0) {
                plugin.profiles.createProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE1, id);
                player.getPersistentDataContainer().set(key, PersistentDataType.STRING, "PROFILE1");
            } else if (plugin.profiles.getProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE2) == 0) {
                plugin.profiles.createProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE2, id);
                player.getPersistentDataContainer().set(key, PersistentDataType.STRING, "PROFILE2");
            } else if (plugin.profiles.getProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE3) == 0) {
                plugin.profiles.createProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE3, id);
                player.getPersistentDataContainer().set(key, PersistentDataType.STRING, "PROFILE3");
            } else if (plugin.profiles.getProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE4) == 0) {
                plugin.profiles.createProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE4, id);
                player.getPersistentDataContainer().set(key, PersistentDataType.STRING, "PROFILE4");
            } else if (plugin.profiles.getProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE5) == 0) {
                plugin.profiles.createProfile(player.getUniqueId(), SQLProfileGetter.Profile.PROFILE5, id);
                player.getPersistentDataContainer().set(key, PersistentDataType.STRING, "PROFILE5");
            } else{
                plugin.utils.sendFormattedMessage(player, "&cYou do not have any open profile slots.");
            }
            plugin.utils.sendFormattedMessage(player, "&7Created new profile with id &a" + id + "&7.");
        }
        return true;
    }
}
