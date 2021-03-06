package co.aeva.actions.commands.actions;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.utils.PlayerAction;
import co.aeva.actions.utils.Utils;

public class School extends PlayerAction {

	public School (int cooldown) {
		super(cooldown);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args)) {
			for (Player target : affect(sender, args))
				target.playSound(target.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
		}
		return true;
	}

	@Override
	public void broadcast(CommandSender pl, Player target) {
		Utils.broadcast("&b" + pl.getName() + "&e would like to remind &b" + target.getName() + "&e that mitochondria is the powerhouse of the cell.");
	}
}
