package me.piotreq.snowstats.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.mysql.MysqlConnect;
import me.piotreq.snowstats.objects.User;

public class SprawdzCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String l, String[] args) {
		if(!(cs instanceof Player)) {
			if(args.length != 1) {
				cs.sendMessage("Poprawne uzycie: /sprawdz <nick>");
			}else {
				Player po = Bukkit.getPlayer(args[0]);
				User poo = UserManager.getUser(po.getName());
				try {
					ResultSet rs = MysqlConnect.stm.executeQuery("SELECT * FROM SnowStats WHERE nick='" +po.getName()+"'");
					if(rs.isLast()) {
						int wynik = rs.getRow();
						if(wynik < 0) {
							cs.sendMessage("Nie znaleziono takiego gracza w bazie danych!");
						}else {
							poo.setName(rs.getString("nick"));
							poo.setUUID(rs.getString("UUID"));
							poo.setDystans(rs.getInt("dystans"));
							poo.setWykopane_bloki(rs.getInt("wykopane_bloki"));
							poo.setExp_stone(rs.getFloat("exp_stone"));
							poo.setExp_drewno(rs.getFloat("exp_drewno"));
							poo.setWykopany_stone(rs.getInt("wykopany_stone"));
							poo.setWykopany_obs(rs.getInt("wykopany_obs"));
							poo.setWykopane_drewno(rs.getInt("wykopane_drewno"));
							poo.setJedzenie(rs.getInt("jedzenie"));
							poo.setSmierci(rs.getInt("smierci"));
							poo.setKille(rs.getInt("kille"));
							poo.setZabite_moby(rs.getInt("zabite_moby"));
							poo.setMonety(rs.getInt("monety"));
							poo.setLvl_stone(rs.getInt("lvl_stone"));
							poo.setLvl_drewno(rs.getInt("lvl_drewno"));
							poo.setPostawione_bloki(rs.getInt("postawione_bloki"));
							poo.setWymagany_stone(rs.getInt("wymagany_stone"));
							poo.setWymagany_drewno(rs.getInt("wymagany_drewno"));
							poo.setDostone(rs.getInt("do_stone"));
							poo.setDodrewna(rs.getInt("do_drewna"));
							cs.sendMessage("Nick :" + poo.getName());
							cs.sendMessage("UUID: "+poo.getUUID() );
							cs.sendMessage("Dystans: " + poo.getDystans());
						}
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			Player p = (Player)cs;
			if(p.isOp()) {
				if(args.length != 1) {
					p.sendMessage("Poprawne uzycie: /sprawdz <nick>");
				}else {
					Player po = Bukkit.getPlayer(args[0]);
					User poo = UserManager.getUser(po.getName());
					try {
						ResultSet rs = MysqlConnect.stm.executeQuery("SELECT * FROM SnowStats WHERE nick='" +po.getName()+"'");
						if(rs.isLast()) {
							int wynik = rs.getRow();
							if(wynik < 0) {
								p.sendMessage("§cNie znaleziono takiego gracza w bazie danych");
							}else {
								poo.setName(rs.getString("nick"));
								poo.setUUID(rs.getString("UUID"));
								poo.setDystans(rs.getInt("dystans"));
								poo.setWykopane_bloki(rs.getInt("wykopane_bloki"));
								poo.setExp_stone(rs.getFloat("exp_stone"));
								poo.setExp_drewno(rs.getFloat("exp_drewno"));
								poo.setWykopany_stone(rs.getInt("wykopany_stone"));
								poo.setWykopany_obs(rs.getInt("wykopany_obs"));
								poo.setWykopane_drewno(rs.getInt("wykopane_drewno"));
								poo.setJedzenie(rs.getInt("jedzenie"));
								poo.setSmierci(rs.getInt("smierci"));
								poo.setKille(rs.getInt("kille"));
								poo.setZabite_moby(rs.getInt("zabite_moby"));
								poo.setMonety(rs.getInt("monety"));
								poo.setLvl_stone(rs.getInt("lvl_stone"));
								poo.setLvl_drewno(rs.getInt("lvl_drewno"));
								poo.setPostawione_bloki(rs.getInt("postawione_bloki"));
								poo.setWymagany_stone(rs.getInt("wymagany_stone"));
								poo.setWymagany_drewno(rs.getInt("wymagany_drewno"));
								poo.setDostone(rs.getInt("do_stone"));
								poo.setDodrewna(rs.getInt("do_drewna"));
								cs.sendMessage("Nick :" + poo.getName());
								cs.sendMessage("UUID: "+poo.getUUID() );
								cs.sendMessage("Dystans: " + poo.getDystans());
							}
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}else {
				p.sendMessage("§cNie masz uprawnienia do tej komedy!");
			}
		}
		return false;
	}

	
}
