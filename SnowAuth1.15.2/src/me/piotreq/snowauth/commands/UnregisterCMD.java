package me.piotreq.snowauth.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.mysql.MySQLConnect;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;
import me.piotreq.snowauth.utils.HashUtil;

public class UnregisterCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		if(!(cs instanceof Player)) {
			cs.sendMessage("Nie mozna tego wykonac z konsoli!");
			return false;
		}
		final Player p = (Player)cs;
		final User u = UserManager.getUser(p.getName());
		if(args.length == 1) {
			if(u.isLogged()) {
				final String pass = HashUtil.md5(args[0]);
				if(pass.equals(u.getPassword())) {
					try {
						MySQLConnect.stm.executeQuery("SELECT * FROM SnowAuth WHERE 'nick' ='"+ u.getName() +"';");
						u.setLogged(false);
						MySQLConnect.stm.executeUpdate("DELETE FROM SnowAuth WHERE 'nick' ='"+ u.getName() +"';");
						p.sendMessage(ChatUtil.fixColor("&cZostales pomyslnie &nodrejestrowany&r&c!"));
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}else {
					p.sendMessage(ChatUtil.fixColor("&cPodales bledne haslo!"));
				}
			}else {
				p.sendMessage(ChatUtil.fixColor("&cMusisz byc zalogowany!"));
			}
		}else {
			p.sendMessage(ChatUtil.fixColor("&cMusisz podac swoje haslo!"));
		}
		return false;
	}

	
}
