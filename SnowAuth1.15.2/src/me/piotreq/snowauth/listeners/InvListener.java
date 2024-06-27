package me.piotreq.snowauth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;


public class InvListener implements Listener{

	@EventHandler
	public void ItemDrop(PlayerDropItemEvent e) {
		final Player p = e.getPlayer();
		final User u = UserManager.getUser(p.getName());
		if(!u.isLogged()) {
			e.setCancelled(true);
			p.sendMessage(ChatUtil.fixColor("&7Zaloguj sie za pomoca /login <haslo>"));
		}
	}
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		final Player p = (Player)e.getWhoClicked();
		final User u = UserManager.getUser(p.getName());
		if(!u.isLogged()) {
			e.setCancelled(true);
			p.closeInventory();
			p.sendMessage(ChatUtil.fixColor("&7Zaloguj sie za pomoca /login <haslo>"));
		}
	}
	@EventHandler
	public void onPlayerInventoryOpen(InventoryOpenEvent e) {
		final Player p = (Player)e.getPlayer();
		final User u = UserManager.getUser(p.getName());
		if(!u.isLogged()) {
			p.closeInventory();
			e.setCancelled(true);
		}
	}
}