package me.piotreq.snowstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.objects.User;

public class eatListener implements Listener{

	@EventHandler
	public void onEat(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());
		u.setJedzenie(u.getJedzenie()+1);
	}
}
