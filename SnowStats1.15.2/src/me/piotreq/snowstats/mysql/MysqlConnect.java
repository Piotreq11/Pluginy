package me.piotreq.snowstats.mysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import me.piotreq.snowstats.Main;


public class MysqlConnect {

	public static Connection con = null;
	private static boolean connected = false;
	public static Statement stm;
	private static String url = "jdbc:mysql://" + Main.getInst().getConfig().getString("host-mysql") + ":"+ Main.getInst().getConfig().getString("port-mysql") + "/" + Main.getInst().getConfig().getString("database-mysql");
	
	public static void connect() {
		con = null;
			try {
				con = (Connection)DriverManager.getConnection(url, Main.getInst().getConfig().getString("user-mysql"), Main.getInst().getConfig().getString("pass-mysql"));
			} catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				stm = con.createStatement();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			setConnected(true);
	}
	public static Connection getConnection() {
		if(con == null) connect();
		return con;
	}

	public static void executeQuery(String query) {
		if(con == null) connect();
		try {
			stm.executeUpdate(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet getQueryResult(String query) {
		ResultSet rs = null;
		try {
			Statement st = getConnection().createStatement();
			rs = st.executeQuery(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return (ResultSet)rs;
	}
	public static boolean isConnected() {
		return connected;
	}
	public static void setConnected(boolean connected) {
		MysqlConnect.connected = connected;
	}
}
