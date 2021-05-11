package live.mufin.skyblock;

import live.mufin.skyblock.commands.tabcompleters.*;
import live.mufin.skyblock.playerdata.MySQL;
import live.mufin.skyblock.commands.*;
import live.mufin.skyblock.events.*;
import live.mufin.skyblock.playerdata.SQLCollectionGetter;
import live.mufin.skyblock.playerdata.SQLProfileGetter;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import live.mufin.skyblock.gui.SkyblockMenu;
import live.mufin.skyblock.items.ItemDataManager;
import live.mufin.skyblock.items.ItemManager;
import live.mufin.skyblock.items.abilities.ItemStats;
import live.mufin.skyblock.items.abilities.MushroomSoup;
import live.mufin.skyblock.playerdata.Stats;
import live.mufin.skyblock.scoreboards.RegularScoreBoard;

import java.sql.SQLException;

public class Main extends JavaPlugin {

	// Public and private variables
	public SQLCollectionGetter collections;
	public Utils utils;
	public ItemDataManager items;
	public RegularScoreBoard board;
	public ItemManager item;
	public Stats stats;
	public FileConfiguration config = this.getConfig();
	public MySQL database;
	public SQLProfileGetter profiles;
	private MushroomSoup soup;
	private ItemStats itemStats;
	
	public void onEnable() {
		this.saveDefaultConfig(); // Initialize config

		// Variables that need to be used in this or other classes.
		this.collections = new SQLCollectionGetter(this);
		this.database = new MySQL(this);
		this.utils = new Utils(this);
		this.board = new RegularScoreBoard(this);
		this.items = new ItemDataManager(this);
		this.item = new ItemManager(this);
		this.soup = new MushroomSoup(this);
		this.stats = new Stats(this);
		this.itemStats = new ItemStats(this);
		this.profiles = new SQLProfileGetter(this);

		// Connect to MySQL
		try{
			database.connect();
		} catch(ClassNotFoundException | SQLException e) {
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aSB&8] &cFAILED &7Database connection."));
		}

		if(database.isConnected()) {
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aSB&8] &aSUCESS &7Database connection."));
			collections.createTable();
			profiles.createTable();
		}

		// Creating worlds
		this.getServer().createWorld(WorldCreator.name("hub"));
		this.getServer().createWorld(WorldCreator.name("spawn"));
		this.getServer().createWorld(WorldCreator.name("islandtemplate"));


		// Initializing CommandExecutors
		this.getCommand("skyblock").setExecutor(new SkyblockCommand(this));
		this.getCommand("stats").setExecutor(new StatsCommand(this));
		this.getCommand("coins").setExecutor(new CoinsCommand(this));
		this.getCommand("setcoins").setExecutor(new SetCoinsCommand(this));
		this.getCommand("setstat").setExecutor(new SetStatCommand(this));
		this.getCommand("scoreboardreload").setExecutor(new ScoreboardReloadCommand(this));
		this.getCommand("setlogger").setExecutor(new SetLoggerCommand(this));
		this.getCommand("item").setExecutor(new ItemCommand(this));
		this.getCommand("logger").setExecutor(new LoggerCommand(this));
		this.getCommand("setflight").setExecutor(new SetFlightCommand(this));
		this.getCommand("setbits").setExecutor(new SetBitsCommand(this));
		this.getCommand("build").setExecutor(new BuildCommand(this));
		this.getCommand("goto").setExecutor(new GotoCommand(this));
		this.getCommand("collection").setExecutor(new CollectionCommand(this));
		this.getCommand("setcollection").setExecutor(new SetCollectionCommand(this));
		this.getCommand("createitems").setExecutor(new CreateItemsCommand(this));
		this.getCommand("createprofile").setExecutor(new CreateProfileCommand(this));
		this.getCommand("deleteprofile").setExecutor(new DeleteProfileCommand(this));
		this.getCommand("selectprofile").setExecutor(new SelectProfileCommand(this));
		this.getCommand("island").setExecutor(new IslandCommand(this));

		// Initializing TabCompleters
		this.getCommand("setlogger").setTabCompleter(new SetLoggerTabComplete());
		this.getCommand("setstat").setTabCompleter(new SetStatTabComplete());
		this.getCommand("item").setTabCompleter(new ItemTabComplete(this));
		this.getCommand("goto").setTabCompleter(new GotoTabComplete());
		this.getCommand("collection").setTabCompleter(new CollectionTabComplete());
		this.getCommand("setcollection").setTabCompleter(new SetCollectionTabComplete());
		this.getCommand("deleteprofile").setTabCompleter(new SeleteProfileTabComplete());
		this.getCommand("selectprofile").setTabCompleter(new SeleteProfileTabComplete());

		// Initializing events
		this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new RegularScoreBoard(this), this);
		this.getServer().getPluginManager().registerEvents(new LoggingEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new FoodEvent(), this);
		this.getServer().getPluginManager().registerEvents(new SkyblockDeathEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new MushroomSoup(this), this);
		this.getServer().getPluginManager().registerEvents(new SkyblockMenu(this), this);
		this.getServer().getPluginManager().registerEvents(new BuildModeEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new EnterPortalEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new ItemPickupEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ItemColourEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new ProfileSignEvents(), this);


		// Initializing BukkitRunnables
		soup.runnable();
		itemStats.runnable();
		stats.runnable();
	}

	public void onDisable() {
		// Disconnect from SQL server
		database.disconnect();
	}
}
