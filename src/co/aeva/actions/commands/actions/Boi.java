package co.aeva.actions.commands.actions;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.utils.SingleAction;
import co.aeva.actions.utils.Utils;

public class Boi extends SingleAction {

	public Boi(int cooldown) {
		super(cooldown);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args)) {
			Player target = (Player) sender;
			target.playSound(target.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
		}
		return true;
	}

	@Override
	public void broadcast(CommandSender pl) {
		Utils.broadcast("&eHere comes that &b" + pl.getName() + "&e fellow! My goodness, how do you do?");
	}
}