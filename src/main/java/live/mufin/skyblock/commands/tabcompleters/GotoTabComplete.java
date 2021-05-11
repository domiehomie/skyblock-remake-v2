package live.mufin.skyblock.commands.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GotoTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        // Adds places to list.
        List<String> worlds = new ArrayList<String>();
        if(worlds.isEmpty()) {
            for(World world : Bukkit.getWorlds()) {
                if(!world.getName().contains("_nether") || !world.getName().contains("_the_end")) {
                    worlds.add(world.getName());
                }
            }
        }
        // Sorts through list depending on characters that have been put in.
        List<String> results = new ArrayList<String>();
        if(args.length == 1) {
            for(String result : worlds) {
                if(result.toUpperCase().startsWith(args[0].toUpperCase()))
                    results.add(result);
            }
            return results;
        }

        return null;
    }
}
