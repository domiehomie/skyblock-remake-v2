# Hypixel Skyblock Remake v2

A project where I, mufin, am trying to recrate Hypixel Skyblock in Spigot. There is currently no download available, however you can import the project into your IDE of choice, along with adding the spigot jar as a dependency to export it.

[TOC]



## Current Features

### Utils

This plugin boasts a help menu using the Bukkit `/help` command. To use it, use `/help skyblock` or replace skyblock with any skyblock commands, for example, `/help setcoins`. This will show you a description, the usage and aliases if applicable. The plugin also follows a messaging guideline starting with [SB]. This means that any chat message that doesnt start with that prefix, is from Bukkit, Minecraft or another plugin.

### Playerdata

Playerdata is stored in 2 places. General playerdata, that needs to be accessed often, is put in the players NBT container. Collection data, however, is stored in a MySQL Database. Connecting to a database is pretty simple, just put the information in the config.yml file, and viola!

```yaml
sql:
  host: "localhost"
  port: 3306
  database: "minecraft"
  username: "skyblock"
  password: "plugin"
  useSSL: false
```

### Profiles

Profile data is stored in the MySQL Database. When a player creates a profile, the plugin makes a copy of the `islandtemplate` world. This new world will be named `island_<id>`, where id is the profile id. The profile id is stored in the MySQL database. When a player creates a profile, they will be automatically switched to said profile, and can run `/is` to warp to their island. If you wish to switch to an already created profile, use the `/selectprofile <profile>` command. You can also delete your profile using the `/deleteprofile` command,

### Custom Items

You can create custom items using the `items.yml` file. simply format your item like this: (Material list can be found [here](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html), stat value options can be found [here](#stat) and rarity options can be found [here](#rarity))

```yaml
TEST_ITEM:
  name: "&cTest Item"
  material: "MINECART"
  rarity: "SPECIAL"
  enchanted: true
  lore:
    - "&8Used for testing."
  stats:
    DAMAGE: 100
    STRENGTH: 50
```

These items will glow when dropped, and the glow color will change depending on their rarity. (Rainbow for rainbow glow)

### Skyblock menu

#### Current features:

- Stat viewer

### Other

* Hunger is disabled
* Nether portal and end portal teleport to hub and island respectively
* Void death is not possible, and is instead replaced by a Skyblock death message.

## Commands

```
* debugging
```

### Build mode

`/build` - toggles build mode. When enabled, allows you to break and place blocks. When disabled, prevents you from doing so. Useful for running around mindlessly.

### Warp commands

`/goto <world>` - Teleports you to the specified world.

`/is` - Teleports you to Your Island.

###  Items

`/item <item> <amount> [player]` - Gives you or another player the specified item.

`/createitems` - Turns all vanilla items into Skyblock items.

### * Scoreboard

`/scoreboardreload <player>` - Reloads the scoreboard for a specific player. 

------

### General playerdata

Coins are stored in the playerdata, but there are two commands you can use to access this data.

`/coins [player]` - Returns your, or someone else's balance.

`/setcoins <player> <amound>` - Lets you set a player's balance.

`/setbits <player> <amount>` - Same as setcoins, but for bits.

`/setflight <player> <seconds>` - Sets the flight duration for a player.

### Stats and collections

`/stats [player]` - View your, or someone else's stats.

`/setstat <player> <stat> <value>` - Accurately set a certain stat of a player.

`/collection <player> <collection>` - View the amount of items a player has in their collections.

`/setcollection <player> <collection> <amount>` - Accurately set the amount of items in a player's collection.

### Profiles

`/createprofile` - Creates a profile.

`/deleteprofile <profile>` - Deletes a specific profile.

`/selectprofile` - Selects a profile.

------

### * Logging

`/logger` - Gives you clickable options for enabling/disabling certain logging features.

`/setlogger <feature> <true|false>` - Lets you set the logging features with a command.



## Customization (enums)

### Rarity

`COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC, SUPREME, SPECIAL, RAINBOW`

### Stat

`DAMAGE, HEALTH, DEFENSE, TRUE_DEFENSE, STRENGTH, SPEED, CRIT_CHANCE, CRIT_DMG, INTELLIGENCE, MINING_SPEED,
SEACREATURESPAWNRATE, MAGICFIND,`

`PETLUCK, ABILITYDAMAGE, FEROCITY, MINING_FORTUNE, FARMING_FORTUNE,
FORAGING_FORTUNE` 

