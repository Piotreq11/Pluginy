package me.piotreq.snowauth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;


public class CMDListener implements Listener{

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		final Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());
		String msg = e.getMessage();
		String cmd = msg.split(" ")[0];
		if(!u.isLogged()) {
			if((cmd.equalsIgnoreCase("/login")) || (cmd.equalsIgnoreCase("/l")) || (cmd.equalsIgnoreCase("/register")) || (cmd.equalsIgnoreCase("/reg"))) {
				return;
			}
			e.setCancelled(true);
			p.sendMessage(ChatUtil.fixColor("&7Zaloguj sie za pomoca /login <haslo>"));
		}
	}
}