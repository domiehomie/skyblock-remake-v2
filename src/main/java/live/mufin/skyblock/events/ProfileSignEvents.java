package live.mufin.skyblock.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProfileSignEvents implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if(e.getLine(0).equals("[PROFILESIGN]")) {
            e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&8[&aSKYBLOCK&8]"));
            e.setLine(2, "Create new");
            e.setLine(3, "Profile.");
        }
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();
        Block block = e.getClickedBlock();
        if(!block.getType().toString().contains("SIGN")) return;

        Sign sign = (Sign) block.getState();
        if(!sign.getLine(0).equals(ChatColor.translateAlternateColorCodes('&', "&8[&aSKYBLOCK&8]"))) return;

        Bukkit.dispatchCommand(p, "createprofile");


    }
}
