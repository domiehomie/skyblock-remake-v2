package live.mufin.skyblock.commands.tabcompleters;

import live.mufin.skyblock.playerdata.SQLCollectionGetter;
import live.mufin.skyblock.playerdata.SQLProfileGetter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SeleteProfileTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        // Adds collections to list.
        List<String> profiles = new ArrayList<String>();
        if(profiles.isEmpty()) {
            for(SQLProfileGetter.Profile profile : SQLProfileGetter.Profile.values()) {
                profiles.add(profile.toString());
            }
        }

        // Sorts through list depending on characters that have been put in.
        List<String> results = new ArrayList<String>();
        if(args.length == 1) {
            for(String result : profiles) {
                if(result.toUpperCase().startsWith(args[0].toUpperCase()))
                    results.add(result);
            }
            return results;
        }


        return null;
    }

}
