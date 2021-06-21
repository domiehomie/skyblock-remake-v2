package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.SQLCollectionGetter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CollectionCommand implements CommandExecutor {

    private Main plugin;
    public CollectionCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("collection")) {
            if(!(sender instanceof Player)) return true;
            Player player = (Player) sender;

            if(args.length == 2) {
                try {
                    // getting all values from strings (saved by try and catch's)
                    Player target = Bukkit.getPlayer(args[0]);
                    SQLCollectionGetter.Collection collection = SQLCollectionGetter.Collection.valueOf(args[1]);
                    int value = plugin.collections.getCollection(target.getUniqueId(), collection);

                    plugin.utils.sendFormattedMessage(player, "&7Collection &a" + collection + " &7of player &a" + target.getName() + "&7 is &a" + value + "&7.");
                } catch (NullPointerException | IllegalArgumentException e) {
                   plugin.utils.sendFormattedMessage(player, "&cInvalid player, collection and/or value.");
                }
            }

        }


        return true;
    }
}
