package co.aeva.actions.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.Actions;
import co.aeva.actions.permissions.PermissionList;

public abstract class PlayerAction extends Action {

	public PlayerAction(int cooldown) {
		super(cooldown);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args)) {
			String name = getName();
			Player player = (Player) sender;
			if (args.length == 0)
				Actions.getInstance().sendMessage("&c/" + name + " [name]", player);
			else if (args[0].equalsIgnoreCase(player.getName()))
				Actions.getInstance().sendMessage("&cYou can't " + name + " yourself!", player);
			else if (!((args[0].equals("*") && sender.hasPermission(PermissionList.TO_ALL)) || Bukkit.getPlayer(args[0]) != null))
				Actions.getInstance().sendMessage(PermissionList.INVALID_PLAYER, player);
			else {
				addCooldown(player.getUniqueId());
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected List<Player> affect(CommandSender pl, String[] args) {
		List<Player> affect = super.affect(pl, args);
		for (Player player : affect)
			broadcast(pl, player);
		return affect;
	}
	
	abstract public void broadcast(CommandSender pl, Player target);
}