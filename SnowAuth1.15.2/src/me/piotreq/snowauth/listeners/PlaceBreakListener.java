package me.piotreq.snowauth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.objects.User;

public class PlaceBreakListener implements Listener{

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		final Player p = (Player)e.getPlayer();
		final User u = UserManager.getUser(p.getName());
		if(!u.isLogged()) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		final Player p = (Player)e.getPlayer();
		final User u = UserManager.getUser(p.getName());
		if(!u.isLogged()) {
			e.setCancelled(true);
		}
	}
}