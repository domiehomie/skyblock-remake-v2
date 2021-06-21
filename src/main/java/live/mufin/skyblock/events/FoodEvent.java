package live.mufin.skyblock.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener {

	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
}
