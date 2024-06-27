package me.piotreq.snowauth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;
import me.piotreq.snowauth.utils.HashUtil;

public class LoginCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		if(!(cs instanceof Player)) {
			cs.sendMessage("Nie mozna tego wykonac z konsoli!");
			return true;
		}
		final Player p = (Player)cs;
		final User u = UserManager.getUser(p.getName());
		if(!u.isHasAccount()) {
			p.sendMessage(ChatUtil.fixColor("&7Nie masz jeszcze konta, zarejestruj sie!"));
			return true;
		}
		if(args.length ==1) {
			final String haslo = HashUtil.md5(args[0]);
			if(u.getPassword().equals(haslo)) {
				u.setLogged(true);
				u.setJoins(u.getJoins()+1);
				p.sendMessage(ChatUtil.fixColor("&aZostales zalogowany!"));
			}else {
				p.sendMessage(ChatUtil.fixColor("&cPodales nieprawidlowe haslo!"));
			}
		}else {
			p.sendMessage(ChatUtil.fixColor("&7Poprawne uzycie: /login <haslo>"));
		}
		return false;
	}

	
}
