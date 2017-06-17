package co.aeva.actions.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SingleAction extends Action {

	public SingleAction(int cooldown) {
		super(cooldown);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args)) {
			addCooldown(((Player) sender).getUniqueId());
			broadcast(sender);
			return true;
		}
		return false;
	}
	
	abstract public void broadcast(CommandSender pl);
}