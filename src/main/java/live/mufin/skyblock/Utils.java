package live.mufin.skyblock;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	private Main plugin;

	public Utils(Main plugin) {
		this.plugin = plugin;
	}


	/**
	 * Easy way of turning boolean into integer.
	 * @param value
	 * @return 1 if value is true, 0 if value is false.
	 */
	public int booleanToInt(boolean value) {
		if(value) return 1;
		else return 0;
	}

	/**
	 * Easy way of turning integer into boolean.
	 * @param value
	 * @return true if value is 1, falses if value is 0.
	 */
	public boolean intToBoolean(int value) {
		if(value == 0) return false;
		else return true;
	}


	/**
	 * Short way to send chatmessages formatted with [SB] and enabling color codes.
	 * @param player
	 * @param message
	 */
	public void sendFormattedMessage(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aSB&8]&r " + message));
		return;
	}

	public String formatSeconds(long timeInSeconds) {
		long secondsLeft = timeInSeconds % 3600 % 60;
		int minutes = (int) Math.floor(timeInSeconds % 3600 / 60);
		int hours = (int) Math.floor(timeInSeconds / 3600);

		String HH = ((hours < 10) ? "0" : "") + hours;
		String MM = ((minutes < 10) ? "0" : "") + minutes;
		String SS = ((secondsLeft < 10) ? "0" : "") + secondsLeft;

		return HH + ":" + MM + ":" + SS;
	}

	/**
	 * Send the logger message, used in multiple occasions
	 * @param player
	 */
	public void sendLoggerMessage(Player player) {
		List<String> features = new ArrayList<String>();
		features.add("itemdrops");
		features.add("itemclicks");

		plugin.utils.sendFormattedMessage(player, "&7Showing logger options: ");
		plugin.utils.sendFormattedMessage(player, "&8================================");

		NamespacedKey itemDropsKey = new NamespacedKey(plugin, "logging_itemdrops");
		NamespacedKey itemClicksKey = new NamespacedKey(plugin, "logging_itemclicks");

		if (this.intToBoolean(player.getPersistentDataContainer().get(itemClicksKey, PersistentDataType.INTEGER))) {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&aEnabled&8] &7- &aitemclicks")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemclicks false"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &cdisable &7this feature!"))));
			player.spigot().sendMessage(msg);
		} else {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&cDisabled&8] &7- &aitemclicks")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemclicks true"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &aenable &7this feature!"))));
			player.spigot().sendMessage(msg);
		}
		
		if (this.intToBoolean(player.getPersistentDataContainer().get(itemDropsKey, PersistentDataType.INTEGER))) {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&aEnabled&8] &7- &aitemdrops")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemdrops false"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &cdisable &7this feature!"))));
			player.spigot().sendMessage(msg);
		} else {
			TextComponent msg = new TextComponent(TextComponent
					.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&8[&cDisabled&8] &7- &aitemdrops")));
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + "setlogger itemdrops true"));
			msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new Text(ChatColor.translateAlternateColorCodes('&', "&7Click to &aenable &7this feature!"))));
			player.spigot().sendMessage(msg);
		}

	}

	/**
	 * Used to capitalize the first charactor of each word in a lowercase string.
	 * @param str
	 * @return "sample string" -> "Sample String"
	 */
	public static String capitalizeWord(String str){
		String words[]=str.split("\\s");
		String capitalizeWord="";
		for(String w:words){
			String first=w.substring(0,1);
			String afterfirst=w.substring(1);
			capitalizeWord+=first.toUpperCase()+afterfirst+" ";
		}
		return capitalizeWord.trim();
	}


}
