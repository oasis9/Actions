package co.aeva.actions.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;

public class Utils {
	
	public static void log(String messageToLog) {
		Server server = Bukkit.getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(color(messageToLog));
	}
	
	public static String sentenceCase(String string) {
		if (string.length() > 0)
			return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		return string;
	}

	public static int countStringOccurancnes(String toFind, String findFrom) {
		int lastIndex = 0;
		int count = 0;
		while (lastIndex != -1) {
			lastIndex = findFrom.indexOf(toFind, lastIndex);
			if (lastIndex != -1)
				count++;
		}
		return count;
	}

	public static String stripPunctuation(String msg) {
		return msg.replaceAll("[^A-Za-z0-9]", "");
	}
	
	public static void broadcast(String message, String... moarMessages) {
		Bukkit.broadcastMessage(color(message));
		for (String msg : moarMessages)
			Bukkit.broadcastMessage(color(msg));
	}

	public static void sendMessage(CommandSender sender, String... msgs) {
		for (String msg : msgs)
			sender.sendMessage(color(msg));
	}
	
	public static void sendMessage(Player pl, BaseComponent[]... msgs) {
		for (BaseComponent[] msg : msgs)
			pl.spigot().sendMessage(msg);
	}

	public static void sendMessage(String message, CommandSender... receivers) {
		for (CommandSender player : receivers)
			player.sendMessage(color(message));
	}

	public static void log(String... msgs) {
		Server server = Bukkit.getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		for (String msg : msgs)
			console.sendMessage(color(msg));
	}

	public static String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static <T extends Enum<T>> T find(Class<T> e, String value) {
		for (T item : e.getEnumConstants())
			if (item.name().equalsIgnoreCase(value))
				return item;
		return null;
	}
}