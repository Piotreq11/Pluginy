package me.piotreq.snowauth.listeners;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.piotreq.snowauth.managers.CaptchManager;
import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.mysql.MySQLConnect;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;

public class JoinQuitListener implements Listener{
	@EventHandler
	public void joinEvet(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		final User u = UserManager.getUser(p.getName());
		try {
			ResultSet rs = MySQLConnect.stm.executeQuery("SELECT * FROM SnowAuth WHERE nick='" +p.getName()+"'");
			if(rs.last()) {
				int wynik = rs.getRow();
			    for (int i = 0; i < 100; i++) {
			    	if (i != 100) {
			    		p.sendMessage(" ");
			          }
				}
					if(wynik < 0) {
						if(!CaptchManager.captchaMap.containsKey(p.getUniqueId())) {
							CaptchManager.generateCaptcha(p.getUniqueId());
						}
						p.sendMessage(ChatUtil.fixColor("&6Poprawne uzycie: /register <kod> <haslo> <haslo>"));
						p.sendMessage(ChatUtil.fixColor("&c&lJezeli masz konto, wejdz ponownie na serwer!"));
						p.sendMessage(ChatUtil.fixColor("&6Twoj kod: &a" + CaptchManager.captchaMap.get(p.getUniqueId())));
						u.setName(p.getName());
						u.setIP(p.getAddress().toString());
						u.setUUID(p.getUniqueId().toString());
						u.setJoins(1);
						try {
							MySQLConnect.stm.execute("INSERT INTO SnowAuth(nick, pass, hasaccount, joins, ip, uuid) VALUES('"+ u.getName() +"','"+ u.getPassword() +"','"+ u.isHasAccount()+"','" + u.getJoins()+"','"+ u.getIP() +"','"+u.getUUID() +"') ON DUPLICATE KEY UPDATE pass='"+u.getPassword()+"', joins = "+ u.getJoins() +", hasaccount ='"+u.isHasAccount()+"', IP ='"+u.getIP()+"', UUID= '"+u.getUUID()+"';");																											
						}catch(SQLException ep){
							ep.printStackTrace();
						}
					}else {
						u.setName(rs.getString("nick"));
						u.setPassword(rs.getString("pass"));
						u.setHasAccount(rs.getBoolean("hasaccount"));
						u.setJoins(rs.getInt("joins"));
						u.setIP(rs.getString("IP"));
						u.setUUID(rs.getString("UUID"));
						p.sendMessage(ChatUtil.fixColor("&7Zaloguj sie za pomoca /login <haslo>"));
					}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.setJoinMessage("§7[§a+§7] §a" +p.getName());
	}
	@EventHandler
	public void quitEvent(PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		final User u = UserManager.getUser(p.getName());
			u.setLogged(false);
			try {
				MySQLConnect.stm.execute("INSERT INTO SnowAuth(nick, pass, hasaccount, joins, ip, uuid) VALUES('"+ u.getName() +"','"+ u.getPassword() +"','"+ u.isHasAccount()+"','" + u.getJoins()+"','"+ u.getIP() +"','"+u.getUUID() +"') ON DUPLICATE KEY UPDATE pass='"+u.getPassword()+"', joins = "+ u.getJoins() +", hasaccount ='"+u.isHasAccount()+"', IP ='"+u.getIP()+"', UUID= '"+u.getUUID()+"';");																											
			}catch(SQLException ep){
				ep.printStackTrace();
			}
			e.setQuitMessage("§7[§c-§7] §c" + p.getName());
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreLogin(AsyncPlayerPreLoginEvent e) {
		Player p = Bukkit.getServer().getPlayerExact(e.getName().toLowerCase());
		if(p == null) {
			return;
		}
		if(p.isOnline()) {
			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "§7Gracz §c"+p.getName() + " §7jest na serwerze!");
		}
	}
	}