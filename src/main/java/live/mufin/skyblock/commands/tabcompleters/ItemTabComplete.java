package live.mufin.skyblock.commands.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import live.mufin.skyblock.Main;

public class ItemTabComplete implements TabCompleter {

	private Main plugin;

	public ItemTabComplete(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		// Adds items to list.
		List<String> items = new ArrayList<String>();
		if(items.isEmpty()) {
			for(String item : plugin.items.getConfig().getKeys(false)) {
				items.add(item.toString());
			}
		}

		// Sorts through list depending on characters that have been put in.
		List<String> results = new ArrayList<String>();
		if(args.length == 1) {
			for(String result : items) {
				if(result.toUpperCase().startsWith(args[0].toUpperCase()))
					results.add(result);
			}
			return results;
		}
			
		
		return null;
	}
	
	
}
