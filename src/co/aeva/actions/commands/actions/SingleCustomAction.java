package co.aeva.actions.commands.actions;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.utils.SingleAction;
import co.aeva.actions.utils.Utils;

public class SingleCustomAction extends SingleAction {
	
	final String name;
	final String response;
	
	public SingleCustomAction(String name, String response, int cooldown) {
		super(cooldown);
		this.name = name;
		this.response = response;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args)) {
			Player target = (Player) sender;
			broadcast(target);
			target.playSound(target.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
		}
		return true;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void broadcast(CommandSender pl) {
		Utils.broadcast(response.replace("%{sender}", pl.getName()));
	}
}
