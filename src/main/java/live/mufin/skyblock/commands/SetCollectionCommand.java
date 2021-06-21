package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.playerdata.SQLCollectionGetter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCollectionCommand implements CommandExecutor {

    private Main plugin;
    public SetCollectionCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("setcollection")){
            if(args.length == 3) {
                if(!(sender instanceof Player)) return true;
                Player player = (Player) sender;

                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    SQLCollectionGetter.Collection collection = SQLCollectionGetter.Collection.valueOf(args[1]);
                    int value =  Integer.parseInt(args[2]);

                    plugin.collections.setCollection(target.getUniqueId(), collection, value);
                    plugin.utils.sendFormattedMessage(player, "&7Set the collection &a" + collection + " &7of player &a" + target.getName() + " &7to &a" + value + "&7.");
                } catch(NullPointerException | IllegalArgumentException e) {
                    plugin.utils.sendFormattedMessage(player, "&cInvalid player, stat or value.");
                }
            }
        }



        return true;
    }
}
