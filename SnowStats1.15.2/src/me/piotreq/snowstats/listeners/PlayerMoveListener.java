package me.piotreq.snowstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.objects.User;

public class PlayerMoveListener implements Listener{

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());

		int xfrom = e.getFrom().getBlockX();
	    int zfrom = e.getFrom().getBlockZ();
	    int yfrom = e.getFrom().getBlockY();
	    int xto = e.getTo().getBlockX();
	    int yto = e.getTo().getBlockY();
	    int zto = e.getTo().getBlockZ();
	    if((xfrom != xto) || (zfrom != zto) || (yfrom != yto)) {
			u.setDystans(u.getDystans()+1);
	    }
	}
}
