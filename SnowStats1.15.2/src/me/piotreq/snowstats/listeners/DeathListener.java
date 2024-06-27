package me.piotreq.snowstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.objects.User;

public class DeathListener implements Listener{

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		final Player p = e.getEntity();
		Player k = p.getKiller();
		User up = UserManager.getUser(p.getName());
		User uk = UserManager.getUser(k.getName());
		up.setSmierci(up.getSmierci()+1);
		uk.setKille(uk.getKille()+1);
		
	}
	@EventHandler
	public void onDeathEntity(EntityDeathEvent e) {
		if((e.getEntity() instanceof org.bukkit.entity.Animals && e.getEntity().getKiller() instanceof Player) || ((e.getEntity() instanceof org.bukkit.entity.Monster) && e.getEntity().getKiller() instanceof Player)) {
			Player p = e.getEntity().getKiller();
			User
			 u = UserManager.getUser(p.getName());
			u.setZabite_moby(u.getZabite_moby()+1);
		}
	}
}
