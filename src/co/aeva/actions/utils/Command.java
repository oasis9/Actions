package co.aeva.actions.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.aeva.actions.Actions;
import co.aeva.actions.permissions.PermissionList;

public abstract class Command implements CommandExecutor {
	
	protected static Map<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private int cooldownTime;
	
	public Command(int cooldown) {
		this.cooldownTime = cooldown;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		String name = getName();
		if (!(sender instanceof Player))
			sender.sendMessage(Utils.color(PermissionList.INVALID_COMMAND_SENDER));
		Player player = (Player) sender;
		if (!player.hasPermission(Actions.NODE + name)) {
			Actions.getInstance().sendMessage(PermissionList.INVALID_PERMISSION, player);
			return false;
		}
		UUID uuid = player.getUniqueId();
		if (cooldown.containsKey(uuid))
			if ((System.currentTimeMillis() - cooldown.get(uuid)) / 1000 >= cooldownTime) {
				cooldown.remove(uuid);
				return true;
			} else {
				int time = (int) (cooldownTime - ((System.currentTimeMillis() - cooldown.get(uuid)) / 1000));
				Actions.getInstance().sendMessage("&cYou can't " + name + " for another &b" + time + " second" + (time != 1 ? "s" : "") + "&c!", player);
			}
		else
			return true;
		return false;
	}
	
	public String getName() {
		return getClass().getSimpleName().toLowerCase();
	}
	
	public void addCooldown(UUID uuid) {
		cooldown.put(uuid, System.currentTimeMillis());
	}
}