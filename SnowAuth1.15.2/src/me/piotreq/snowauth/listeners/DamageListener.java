package me.piotreq.snowauth.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;


public class DamageListener implements Listener{

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Entity en = e.getEntity();
		if(!(en instanceof Player)) {
			return;
		}
		final Player p = (Player)e.getEntity();
		final User u = UserManager.getUser(p.getName());
    	if(!u.isLogged()) {
    		e.setCancelled(true);
    	}
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
    	final Player p = e.getPlayer();
    	final User u = UserManager.getUser(p.getName());
    	if(!u.isLogged()) {
    		e.setCancelled(true);
    	}
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
    	final Player p = e.getPlayer();
    	final User u = UserManager.getUser(p.getName());
    	if(!u.isLogged()) {
    		e.setCancelled(true);
    		p.sendMessage(ChatUtil.fixColor("&7Zaloguj sie za pomoca /login <haslo>"));
    	}
	}
}