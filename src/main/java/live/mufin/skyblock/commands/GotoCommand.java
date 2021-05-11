package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GotoCommand implements CommandExecutor {

    private Main plugin;
    public GotoCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("goto")) {
            if(!(sender instanceof Player)) return true;
            Player player = (Player) sender;

            try {
                World world = Bukkit.getWorld(args[0]); //getting world from string
                player.teleport(world.getSpawnLocation());
                plugin.utils.sendFormattedMessage(player, "&7Sent you to &a" + args[0] + "&7.");
                plugin.board.createBoard(player);
            } catch (NullPointerException e) {
                plugin.utils.sendFormattedMessage(player, "&c'" + args[0] + "' is an invalid world!");
            }




        }


        return true;
    }
}
