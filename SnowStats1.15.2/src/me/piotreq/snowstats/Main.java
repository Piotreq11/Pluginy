package me.piotreq.snowstats;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.piotreq.snowstats.commands.SprawdzCMD;
import me.piotreq.snowstats.listeners.BreakListener;
import me.piotreq.snowstats.listeners.ChatListener;
import me.piotreq.snowstats.listeners.DeathListener;
import me.piotreq.snowstats.listeners.PlaceListener;
import me.piotreq.snowstats.listeners.PlayerMoveListener;
import me.piotreq.snowstats.listeners.eatListener;
import me.piotreq.snowstats.listeners.joinListener;
import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.mysql.ManagerMysql;
import me.piotreq.snowstats.mysql.MysqlConnect;
import me.piotreq.snowstats.objects.User;

public class Main extends JavaPlugin{

	public static Main inst;
	
	@Override
	public void onEnable() {
		System.out.println("");
		System.out.println("[SnowStats] Uruchamianie pluginu...");
		System.out.println("");
		inst = this;
		saveCommands();
		saveListeners();
		saveDefaultConfig();
		MysqlConnect.connect();
		if(MysqlConnect.isConnected()) {
			System.out.println("[SnowStats] Polaczono z baza danych!");
		}
		ManagerMysql.Load();
	}

	private void saveListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new eatListener(), this);
		pm.registerEvents(new joinListener(), this);
		pm.registerEvents(new BreakListener(), this);
		pm.registerEvents(new PlaceListener(), this);
		pm.registerEvents(new PlayerMoveListener(), this);
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new ChatListener(), this);
	}

	private void saveCommands() {
		getCommand("sprawdz").setExecutor(new SprawdzCMD());
	}
	
	public void onDisable() {
		for(User user : UserManager.users.values()) {
			ManagerMysql.save(user);
		}
	}
	public static Main getInst() {
    	if(inst == null) {
    		return new Main();
    	}
    	return inst;
	}
}
