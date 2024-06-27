package me.piotreq.snowauth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.piotreq.snowauth.managers.CaptchManager;
import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;

public class MoveListener implements Listener{

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		final User u = UserManager.getUser(p.getName());
		if(!u.isLogged()) {
			e.getPlayer().teleport(e.getFrom());
			if(!u.isHasAccount()) {
				if(!CaptchManager.captchaMap.containsKey(p.getUniqueId())) {
					CaptchManager.generateCaptcha(p.getUniqueId());
				}
				p.sendMessage(ChatUtil.fixColor("&6Poprawne uzycie: /register <kod> <haslo> <haslo>"));
				p.sendMessage(ChatUtil.fixColor("&c&lJezeli masz konto, wejdz ponownie na serwer!"));
				p.sendMessage(ChatUtil.fixColor("&6Twoj kod: &a" + CaptchManager.captchaMap.get(p.getUniqueId())));
			}else {
				p.sendMessage(ChatUtil.fixColor("&7Zaloguj sie za pomoca /login <haslo>"));
			}
		}
	}
}