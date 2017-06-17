package co.aeva.actions.commands.actions;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import co.aeva.actions.Actions;
import co.aeva.actions.utils.PlayerAction;
import co.aeva.actions.utils.Utils;

public class Spook extends PlayerAction {
	
	public Spook(int cooldown) {
		super(cooldown);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (super.onCommand(sender, cmd, label, args))
			for (Player target : affect(sender, args)) {
				target.playSound(target.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 1);
				new BukkitRunnable() {
					int runtime = 0;
					@Override
					public void run() {
						runtime++;
						if (runtime > 20) {
							target.getInventory().setHelmet(null);
							cancel();
							return;
						}
						target.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
					}
				}.runTaskTimer(Actions.getInstance(), 0, 1);
			}
		return true;
	}
	
	@Override
	public void broadcast(CommandSender pl, Player target) {
		Utils.broadcast("&b" + pl.getName() + "&c spooked &b" + target.getName() + "&c! ♫ Spooky Scary Skeletons ♫");
	}
}