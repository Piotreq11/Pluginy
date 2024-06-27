package me.piotreq.snowstats.mysql;

import java.sql.SQLException;

import me.piotreq.snowstats.objects.User;

public class ManagerMysql {

	public static void Load() {
		MysqlConnect.getConnection();
		try {
			MysqlConnect.stm.executeUpdate("CREATE TABLE IF NOT EXISTS SnowStats(nick VARCHAR(50) NOT NULL, UUID VARCHAR(100) NOT NULL,dystans bigint NOT NULL,wykopane_bloki bigint NOT NULL, exp_stone float NOT NULL, exp_drewno float NOT NULL, wykopany_stone bigint NOT NULL, wykopany_obs bigint NOT NULL, wykopane_drewno bigint NOT NULL,jedzenie bigint NOT NULL,smierci bigint NOT NULL, kille bigint NOT NULL, zabite_moby bigint NOT NULL,monety bigint NOT NULL, lvl_stone bigint NOT NULL, lvl_drewno bigint NOT NULL, postawione_bloki bigint NOT NULL,wymagany_stone int NOT NULL,wymagany_drewno int NOT NULL, do_stone int NOT NULL, do_drewna int NOT NULL, PRIMARY KEY(nick));");
		}catch(SQLException e) {}
	}
	public static void save(User u) {
		try {
			MysqlConnect.stm.execute("INSERT INTO SnowStats(nick, UUID, dystans,wykopane_bloki,exp_stone,exp_drewno,wykopany_stone,wykopany_obs,wykopane_drewno,jedzenie,smierci,kille,zabite_moby,monety,lvl_stone,lvl_drewno, postawione_bloki, wymagany_stone, wymagany_drewno, do_stone, do_drewna) VALUES('"+u.getName()+"','"+u.getUUID()+"','"+u.getDystans()+"','"+u.getWykopane_bloki()+"','"+u.getExp_stone()+"','"+u.getExp_drewno()+"','"+u.getWykopany_stone()+"','"+u.getWykopany_obs()+"','"+u.getWykopane_drewno()+"','"+u.getJedzenie()+"','"+u.getSmierci()+"','"+u.getKille()+"','"+u.getZabite_moby()+"','"+u.getMonety()+"','"+u.getLvl_stone()+"','"+u.getLvl_drewno()+"','"+u.getPostawione_bloki()+"','"+u.getWymagany_stone()+"','"+u.getWymagany_drewno()+"','"+u.getDostone()+"','"+u.getDodrewna()+"') ON DUPLICATE KEY UPDATE nick ='"+u.getName()+"', UUID = '"+u.getUUID()+"', dystans = "+u.getDystans()+", wykopane_bloki = "+u.getWykopane_bloki()+", exp_stone = "+u.getExp_stone()+", exp_drewno = "+u.getExp_drewno()+", wykopany_stone = "+u.getWykopany_stone()+", wykopany_obs = "+u.getWykopany_obs()+", wykopane_drewno = "+u.getWykopane_drewno()+", jedzenie = "+u.getJedzenie()+", smierci = "+u.getSmierci()+", kille = "+u.getKille()+", zabite_moby = "+u.getZabite_moby()+", monety = "+u.getMonety()+", lvl_stone = "+u.getLvl_stone()+", lvl_drewno = "+u.getLvl_drewno()+", postawione_bloki = "+u.getPostawione_bloki()+",wymagany_stone="+u.getWymagany_stone()+",wymagany_drewno="+u.getWymagany_drewno()+", do_stone ="+u.getDostone()+", do_drewna="+u.getDodrewna()+";");																																	
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("[SnowStats] Zapisano graczy do bazy danych!");
		}
	}
}
