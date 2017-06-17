package co.aeva.actions.commands.actions;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.utils.ParticleEffect;
import co.aeva.actions.utils.PlayerAction;
import co.aeva.actions.utils.Utils;

public class Meme extends PlayerAction {
	
	public Meme(int cooldown) {
		super(cooldown);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args))
			for (Player target : affect(sender, args)) {
				ParticleEffect.CRIT.display(0.0F, 0.0F, 0.0F, 1F, 4, target.getLocation(), 10.0D);
				target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 1);
			}
		return true;
	}
	
	@Override
	public void broadcast(CommandSender pl, Player target) {
		Utils.broadcast("&b" + pl.getName() + "&e memed &b" + target.getName() + "&e! (͡° ͜ʖ ͡°)");
	}
}