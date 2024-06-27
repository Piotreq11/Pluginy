package me.piotreq.snowstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.objects.User;

public class PlaceListener implements Listener{

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());
		u.setPostawione_bloki(u.getPostawione_bloki()+1);
	}
}
