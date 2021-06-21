package live.mufin.skyblock.commands;

import com.google.common.base.Joiner;
import live.mufin.skyblock.Main;
import live.mufin.skyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepeatCommand implements CommandExecutor, Listener {

  private String cmd;

  private final HashMap<String, Integer> delays = new HashMap<String, Integer>();
  private final HashMap<String, Integer> stopTimes = new HashMap<String, Integer>();

  NamespacedKey k = new NamespacedKey(Main.plugin, "isRepeating");

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!label.equalsIgnoreCase("repeat")) return true;
    if (!(sender instanceof Player)) return true;
    Player p = (Player) sender;
    int delay = Integer.parseInt(args[0]);
    int time =Integer.parseInt(args[1]);

    String cmd = "summon minecraft:pig";
    this.repeat(cmd, delay, time, sender);

    p.getPersistentDataContainer().set(k, PersistentDataType.INTEGER, 1);
    Utils.sendFormattedMessage(p, "&7Send the command in a &achat message&7.");
    return true;
  }

  public void repeat(String command, int delay, int time, CommandSender s) {
    BukkitTask t = new BukkitRunnable() {
      @Override
      public void run() {
        Bukkit.dispatchCommand(s, command);
      }
    }.runTaskTimer(Main.plugin, 1, delay);

    new BukkitRunnable() {
      @Override
      public void run() {
        t.cancel();
      }
    }.runTaskLater(Main.plugin, time);
  }
}
