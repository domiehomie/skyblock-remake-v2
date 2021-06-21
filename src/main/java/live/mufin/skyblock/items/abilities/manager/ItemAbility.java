package live.mufin.skyblock.items.abilities.manager;

import org.bukkit.event.block.Action;

public interface ItemAbility {
  void ItemAbility(Ability ability);
  String AbilityName();
  int ManaCost();
  Action[] Actions();
  String targetItemName();
}
