package me.piotreq.snowstats.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.objects.User;
import me.piotreq.snowstats.utils.ChatUtil;

import org.bukkit.event.Listener;

public class ChatListener implements Listener{

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());
		e.setFormat(ChatUtil.fixColor("&6"+u.getName()+"&7: &6"+e.getMessage()));
	}
}
