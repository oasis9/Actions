package co.aeva.actions.commands.actions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.aeva.actions.Actions;
import co.aeva.actions.utils.Action;
import co.aeva.actions.utils.PlayerAction;
import co.aeva.actions.utils.Utils;

public class About implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TAKE THE W
		if (!cmd.getName().equalsIgnoreCase("actions"))
			return true;
		
		Utils.sendMessage(sender,
			"&bActions " + Actions.getInstance().getDescription().getVersion() + "&e by &boasisBooster &eand &bNetworkBooster&e: &bhttps://twitter.com/AevaNetwork");
		
		String actions = "";
		for (Action action : Actions.getInstance().actions)
			actions += (actions != "" ? "&e, &b" : "&b") + action.getName() + (action instanceof PlayerAction ? " [player]" : "");
		Utils.sendMessage(sender, "&eAvailable actions: " + actions);
		
		return true;
	}
	

}
