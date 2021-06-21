package live.mufin.skyblock.commands.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import live.mufin.skyblock.playerdata.Stats.Stat;

public class SetStatTabComplete implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

		// Adds stats to list.
		List<String> stats = new ArrayList<String>();
		if(stats.isEmpty()) {
			for(Stat stat : Stat.values()) {
				stats.add(stat.toString());
			}
		}

		// Sorts through list depending on characters that have been put in.
		List<String> results = new ArrayList<String>();
		if(args.length == 2) {
			for(String result : stats) {
				if(result.toUpperCase().startsWith(args[1].toUpperCase()))
					results.add(result);
			}
			return results;
		}
			
		
		return null;
	}
	
	
	
	
}
