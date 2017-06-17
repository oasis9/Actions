package co.aeva.actions.utils;

import org.bukkit.entity.Player;

import com.earth2me.essentials.Essentials;

public class EssentialsUtils {
	
	public boolean isVanished(Player pl) {
		return ((Essentials) Essentials.getPlugin(Essentials.class)).getVanishedPlayers().contains(pl.getName());
	}
	
}
