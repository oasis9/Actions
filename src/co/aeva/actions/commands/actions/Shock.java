package co.aeva.actions.commands.actions;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.utils.PlayerAction;
import co.aeva.actions.utils.Utils;

public class Shock extends PlayerAction {
	
	public Shock(int cooldown) {
		super(cooldown);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args))
			for (Player target : affect(sender, args)) {
				target.getWorld().strikeLightningEffect(target.getLocation());
				target.playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
			}
		return true;
	}
	
	@Override
	public void broadcast(CommandSender pl, Player target) {
		Utils.broadcast("&b" + pl.getName() + "&c shocked &b" + target.getName() + "&c! ♫ I feel it in my bones ♫");
	}
}