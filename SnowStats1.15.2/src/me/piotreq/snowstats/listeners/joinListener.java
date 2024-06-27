package me.piotreq.snowstats.listeners;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.mysql.MysqlConnect;
import me.piotreq.snowstats.objects.User;

public class joinListener implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());
		try {
			ResultSet rs = MysqlConnect.stm.executeQuery("SELECT * FROM SnowStats WHERE nick='" +p.getName()+"'");
			if(rs.last()) {
				int wynik = rs.getRow();
				if(wynik < 0) {
					u.setName(p.getName());
					u.setUUID(p.getUniqueId().toString());
					u.setDystans(0);
					u.setWykopane_bloki(0);
					u.setExp_stone(0);
					u.setExp_drewno(0);
					u.setWykopany_stone(0);
					u.setWykopany_obs(0);
					u.setWykopane_drewno(0);
					u.setJedzenie(0);
					u.setSmierci(0);
					u.setKille(0);
					u.setZabite_moby(0);
					u.setMonety(0);
					u.setLvl_stone(0);
					u.setLvl_drewno(0);
					u.setPostawione_bloki(0);
					u.setWymagany_stone(1000);
					u.setWymagany_drewno(1000);
					u.setDostone(0);
					u.setDodrewna(0);
				}else {
					u.setName(rs.getString("nick"));
					u.setUUID(rs.getString("UUID"));
					u.setDystans(rs.getInt("dystans"));
					u.setWykopane_bloki(rs.getInt("wykopane_bloki"));
					u.setExp_stone(rs.getFloat("exp_stone"));
					u.setExp_drewno(rs.getFloat("exp_drewno"));
					u.setWykopany_stone(rs.getInt("wykopany_stone"));
					u.setWykopany_obs(rs.getInt("wykopany_obs"));
					u.setWykopane_drewno(rs.getInt("wykopane_drewno"));
					u.setJedzenie(rs.getInt("jedzenie"));
					u.setSmierci(rs.getInt("smierci"));
					u.setKille(rs.getInt("kille"));
					u.setZabite_moby(rs.getInt("zabite_moby"));
					u.setMonety(rs.getInt("monety"));
					u.setLvl_stone(rs.getInt("lvl_stone"));
					u.setLvl_drewno(rs.getInt("lvl_drewno"));
					u.setPostawione_bloki(rs.getInt("postawione_bloki"));
					u.setWymagany_stone(rs.getInt("wymagany_stone"));
					u.setWymagany_drewno(rs.getInt("wymagany_drewno"));
					u.setDostone(rs.getInt("do_stone"));
					u.setDodrewna(rs.getInt("do_drewna"));
				}
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());
		u.setUUID(p.getUniqueId().toString());
		try {
			MysqlConnect.stm.execute("INSERT INTO SnowStats(nick, UUID, dystans,wykopane_bloki,exp_stone,exp_drewno,wykopany_stone,wykopany_obs,wykopane_drewno,jedzenie,smierci,kille,zabite_moby,monety,lvl_stone,lvl_drewno, postawione_bloki, wymagany_stone, wymagany_drewno, do_stone, do_drewna) VALUES('"+u.getName()+"','"+u.getUUID()+"','"+u.getDystans()+"','"+u.getWykopane_bloki()+"','"+u.getExp_stone()+"','"+u.getExp_drewno()+"','"+u.getWykopany_stone()+"','"+u.getWykopany_obs()+"','"+u.getWykopane_drewno()+"','"+u.getJedzenie()+"','"+u.getSmierci()+"','"+u.getKille()+"','"+u.getZabite_moby()+"','"+u.getMonety()+"','"+u.getLvl_stone()+"','"+u.getLvl_drewno()+"','"+u.getPostawione_bloki()+"','"+u.getWymagany_stone()+"','"+u.getWymagany_drewno()+"','"+u.getDostone()+"','"+u.getDodrewna()+"') ON DUPLICATE KEY UPDATE nick ='"+u.getName()+"', UUID = '"+u.getUUID()+"', dystans = "+u.getDystans()+", wykopane_bloki = "+u.getWykopane_bloki()+", exp_stone = "+u.getExp_stone()+", exp_drewno = "+u.getExp_drewno()+", wykopany_stone = "+u.getWykopany_stone()+", wykopany_obs = "+u.getWykopany_obs()+", wykopane_drewno = "+u.getWykopane_drewno()+", jedzenie = "+u.getJedzenie()+", smierci = "+u.getSmierci()+", kille = "+u.getKille()+", zabite_moby = "+u.getZabite_moby()+", monety = "+u.getMonety()+", lvl_stone = "+u.getLvl_stone()+", lvl_drewno = "+u.getLvl_drewno()+", postawione_bloki = "+u.getPostawione_bloki()+",wymagany_stone="+u.getWymagany_stone()+",wymagany_drewno="+u.getWymagany_drewno()+", do_stone ="+u.getDostone()+", do_drewna="+u.getDodrewna()+";");																																	
		}catch(SQLException en) {
			en.printStackTrace();
		}
	}
}
