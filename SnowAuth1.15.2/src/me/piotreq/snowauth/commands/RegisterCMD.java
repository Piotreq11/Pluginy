package me.piotreq.snowauth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.piotreq.snowauth.managers.CaptchManager;
import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.objects.User;
import me.piotreq.snowauth.utils.ChatUtil;
import me.piotreq.snowauth.utils.HashUtil;




public class RegisterCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		if(!(cs instanceof Player)) {
			cs.sendMessage("Nie mozna tego wykonac z konsoli");
		}
		final Player p = (Player)cs;
		final User u = UserManager.getUser(p.getName());
		if(u.isHasAccount()) {
			p.sendMessage(ChatUtil.fixColor("&7Masz juz konto, zaloguje sie za pomoca /login <haslo>"));
			return true;
		}	
		if(u.isLogged()) {
			p.sendMessage(ChatUtil.fixColor("&7Jestes juz zalogowany!"));
			return true;
		}
		if(args.length !=3) {
			p.sendMessage(ChatUtil.fixColor("&6Poprawne uzycie: /register <kod> <haslo> <haslo>"));
			p.sendMessage(ChatUtil.fixColor("&6Twoj kod: &a" + CaptchManager.captchaMap.get(p.getUniqueId())));
			return true;
		}
		final String captcha = args[0];
		if(!captcha.equals(CaptchManager.captchaMap.get(p.getUniqueId()))) {
			p.sendMessage(ChatUtil.fixColor("&6Poprawne uzycie: /register <kod> <haslo> <haslo>"));
			p.sendMessage(ChatUtil.fixColor("&6Twoj kod: &a" + CaptchManager.captchaMap.get(p.getUniqueId())));
			return true;
		}
		if (!args[1].equals(args[2])){
            p.sendMessage(ChatUtil.fixColor("&7Hasla nie zgadzaja sie!"));
            return true;
        }
		
		u.setPassword(HashUtil.md5(args[1]));
		u.setHasAccount(true);
		u.setLogged(true);
		u.setIP(p.getAddress().toString());
		u.setJoins(1);
		p.sendMessage(ChatUtil.fixColor("&aZostales pomyslnie zarejestrowany, milej gry!"));
		return false;
	}

	
}
