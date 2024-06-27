package me.piotreq.snowauth.mysql;

import java.sql.SQLException;

import me.piotreq.snowauth.objects.User;

public class ManagerMySQL {

	public static void Load() {
		MySQLConnect.getConnection();
		try {
			MySQLConnect.stm.executeUpdate("CREATE TABLE IF NOT EXISTS SnowAuth(nick VARCHAR(50) NOT NULL, pass VARCHAR(100) NOT NULL, hasaccount VARCHAR(150) NOT NULL, joins int(100) NOT NULL,IP VARCHAR(100) NOT NULL, UUID VARCHAR(200) NOT NULL, PRIMARY KEY(nick));");
		}catch(SQLException e) {}
	}
	public static void save(User u) {
		try {
			MySQLConnect.stm.execute("INSERT INTO SnowAuth(nick, pass, hasaccount, OP) VALUES('" + u.getName() + "','"+ u.getPassword() +"','" + u.isHasAccount() +"') ON DUPLICATE KEY UPDATE pass = '"+ u.getPassword()+ "';");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("[SnowAuth] Zapisano graczy do bazy danych!");
		}
	}
}
