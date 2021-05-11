package live.mufin.skyblock.commands;

import live.mufin.skyblock.Main;
import live.mufin.skyblock.Utils;
import live.mufin.skyblock.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class CreateItemsCommand implements CommandExecutor {

    private Main plugin;

    public CreateItemsCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("createitems")) {
            for (Material material : Material.values()) {
                try {
                    // Formats item names
                    String itemName = material.toString().toLowerCase().replace('_', ' ');
                    plugin.items.getConfig().set(material + ".name", "&f" + Utils.capitalizeWord(itemName));

                    plugin.items.getConfig().set(material + ".rarity", ItemManager.Rarity.COMMON.toString());
                    plugin.items.getConfig().set(material + ".material", material.toString());
                    sender.sendMessage(ChatColor.GREEN + material.toString() + " created.");
                    plugin.items.saveConfig();
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + material.toString() + " failed to create.");
                }

            }

        }

        return true;
    }

}
