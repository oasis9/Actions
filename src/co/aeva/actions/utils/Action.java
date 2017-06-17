 package co.aeva.actions.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.Actions;
import co.aeva.actions.permissions.PermissionList;

public abstract class Action extends Command {
	
	protected Action(int cooldown) {
		super(cooldown);
	}
	
	protected List<Player> affect(CommandSender sender, String[] args) {
		List<Player> affect = new ArrayList<>();
		Player supposedlyExists = Bukkit.getPlayer(args[0]);
		if (supposedlyExists != null)
			affect.add(supposedlyExists);
		else if (args[0].equals("*") && sender.hasPermission(PermissionList.TO_ALL))
			for (Player pl : Bukkit.getOnlinePlayers())
				if (Actions.getInstance().essentials != null && !Actions.getInstance().essentials.isVanished(pl))
					affect.add(pl);
		if (affect.size() <= 0)
			Actions.getInstance().sendMessage(PermissionList.INVALID_PLAYER, sender);
		return affect;
	}
}