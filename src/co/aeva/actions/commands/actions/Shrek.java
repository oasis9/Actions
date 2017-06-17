package co.aeva.actions.commands.actions;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.utils.ParticleEffect;
import co.aeva.actions.utils.ParticleEffect.BlockData;
import co.aeva.actions.utils.PlayerAction;
import co.aeva.actions.utils.Utils;

public class Shrek extends PlayerAction {
	
	public Shrek(int cooldown) {
		super(cooldown);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args))
			for (Player target : affect(sender, args)) {
				ParticleEffect.BLOCK_DUST.display(new BlockData(Material.SLIME_BLOCK, (byte) 0), 0.0F, 0.0F, 0.0F, 1F, 4, target.getLocation(), 10.0D);
				target.playSound(target.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 1, 1);
			}
		return true;
	}
	
	@Override
	public void broadcast(CommandSender pl, Player target) {
		Utils.broadcast("&b" + pl.getName() + "&a shrekt &b" + target.getName() + "&a! It's all ogre now.");
	}
}