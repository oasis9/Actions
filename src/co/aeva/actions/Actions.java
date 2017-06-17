package co.aeva.actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import co.aeva.actions.commands.actions.About;
import co.aeva.actions.commands.actions.Boi;
import co.aeva.actions.commands.actions.Hug;
import co.aeva.actions.commands.actions.Launch;
import co.aeva.actions.commands.actions.Meme;
import co.aeva.actions.commands.actions.Rekt;
import co.aeva.actions.commands.actions.Roundhouse;
import co.aeva.actions.commands.actions.School;
import co.aeva.actions.commands.actions.Shock;
import co.aeva.actions.commands.actions.Shrek;
import co.aeva.actions.commands.actions.SingleCustomAction;
import co.aeva.actions.commands.actions.Slap;
import co.aeva.actions.commands.actions.Spook;
import co.aeva.actions.commands.actions.SudoCustomAction;
import co.aeva.actions.commands.actions.SudoOtherCustomAction;
import co.aeva.actions.commands.actions.TargetCustomAction;
import co.aeva.actions.commands.actions.Troll;
import co.aeva.actions.commands.info.TheGame;
import co.aeva.actions.utils.Action;
import co.aeva.actions.utils.EssentialsUtils;
import co.aeva.actions.utils.Utils;

public class Actions extends JavaPlugin {
	
	public static String NODE = "actions.";
	private static Actions plugin;
	
	public List<Action> actions = new ArrayList<>();

	public static Actions getInstance() {
		return plugin;
	}

	public EssentialsUtils essentials;
	
	public void onEnable() {
		plugin = this;
		
		if (Bukkit.getPluginManager().isPluginEnabled("Essentials"))
			essentials = new EssentialsUtils();
		
		getCommand("actions").setExecutor(new About());
		
		registerActions(new Hug(15),
				new Launch(60),
				new Meme(15),
				new Roundhouse(30),
				new Slap(15),
				new Rekt(15),
				new Shrek(15),
				new Shock(30),
				new Spook(15),
				new Troll(15),
				new TheGame(15),
				new Boi(15),
				new School(15));
		
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		
		FileConfiguration conf = getConfig();
		for (String key : conf.getKeys(false))
			if (conf.isConfigurationSection(key)) {
				if (key.contains(" ") || key.equals("")) {
					getLogger().warning("Action " + key + " not registered - Spaces are not allowed.");
					continue;
				}
				ConfigurationSection cs = conf.getConfigurationSection(key);
				if (cs.isString("type") && cs.isString("pattern") && cs.isInt("cooldown")) {
					
					String type = cs.getString("type");
					int cooldown = cs.getInt("cooldown");
					String response = cs.getString("pattern");
					
					List<String> aliases = cs.getStringList("aliases");
					if (aliases != null)
						for (int i = 0; i < aliases.size(); i++) {
							String alias = aliases.get(i);
							if (alias.contains(" ") && alias.equals("")) {
								getLogger().warning("Action " + key + " alias " + alias + " not registered - Spaces are not allowed.");
								aliases.remove(i);
							}
						}
					
					Action action = null;
					switch (type.toLowerCase()) {
					case "single":
						action = new SingleCustomAction(key, response, cooldown);
						break;
					case "targeted":
						action = new TargetCustomAction(key, response, cooldown);
						break;
					case "sudo":
						action = new SudoCustomAction(key, response, cooldown);
						break;
					case "sudoother":
						action = new SudoOtherCustomAction(key, response, cooldown);
						break;
					}
					registerAction(action, key, aliases);
				}
			}
	}
		
	public void registerActions(Action... actions) {
		for (Action action : actions) {
			this.actions.add(action);
			getCommand(action.getName()).setExecutor(action);
		}
	}

	public void log(String messageToLog) {
		Server server = Bukkit.getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(Utils.color(messageToLog));
	}

	public void sendMessage(String message, CommandSender... players) {
		for (CommandSender player : players)
			player.sendMessage(color(message));
	}
	
	public String color(String in) {
		return ChatColor.translateAlternateColorCodes('&', in);
	}
	
	public void registerAction(CommandExecutor ce, String name, List<String> aliases) {
		PluginCommand command = getCommand(name, this);
		command.setAliases(aliases);
		
		getCommandMap().register(name, command);
		Bukkit.getPluginCommand(name).setExecutor(ce);
		
		for (String alias : aliases)
			Bukkit.getPluginCommand(alias).setExecutor(ce);
	}
	
	private static PluginCommand getCommand(String name, Plugin actions) {
		PluginCommand command = null;
	 
		try {
			Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			c.setAccessible(true);
	 
			command = c.newInstance(name, actions);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	 
		return command;
	}
	 
	private static CommandMap getCommandMap() {
		CommandMap commandMap = null;
	 
		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);
	 
				commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	 
		return commandMap;
	}
}
