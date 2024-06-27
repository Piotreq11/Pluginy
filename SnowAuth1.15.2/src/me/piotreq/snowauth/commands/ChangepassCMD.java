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

public class ChangepassCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		if(!(cs instanceof Player)) {
			cs.sendMessage("Nie mozna tego wykonac z konsoli!");
			return false;
		}
		final Player p = (Player)cs;
		final User u = UserManager.getUser(p.getName());
		if(args.length !=2) {
			p.sendMessage(ChatUtil.fixColor("&7Poprawne uzycie: &6/changepasword <stare_haslo> <nowe_haslo>"));
			return false;
		}
        String starehaslo = HashUtil.md5(args[0]);
        String nowehaslo = HashUtil.md5(args[1]);
		if(!u.getPassword().equals(starehaslo)) {
			p.sendMessage(ChatUtil.fixColor("&cStare haslo nie zgadza sie"));
			return true;
		}
		u.setPassword(nowehaslo);
		p.sendMessage(ChatUtil.fixColor("&aTwoje haslo zostalo zmienione!"));
		try {
			MySQLConnect.stm.execute("INSERT INTO SnowAuTH(nick, pass, hasaccount, joins, ip, uuid) VALUES('"+ u.getName() +"','"+ u.getPassword() +"','"+ u.isHasAccount()+"','" + u.getJoins()+"','"+ u.getIP() +"','"+u.getUUID() +"') ON DUPLICATE KEY UPDATE pass='"+u.getPassword()+"', joins = "+ u.getJoins() +";");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
