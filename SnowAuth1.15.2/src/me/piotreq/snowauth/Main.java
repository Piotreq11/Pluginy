package me.piotreq.snowauth;


import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.piotreq.snowauth.commands.ChangepassCMD;
import me.piotreq.snowauth.commands.LoginCMD;
import me.piotreq.snowauth.commands.RegisterCMD;
import me.piotreq.snowauth.commands.UnregisterCMD;
import me.piotreq.snowauth.listeners.CMDListener;
import me.piotreq.snowauth.listeners.ChatListener;
import me.piotreq.snowauth.listeners.DamageListener;
import me.piotreq.snowauth.listeners.InvListener;
import me.piotreq.snowauth.listeners.JoinQuitListener;
import me.piotreq.snowauth.listeners.MoveListener;
import me.piotreq.snowauth.listeners.PlaceBreakListener;
import me.piotreq.snowauth.managers.UserManager;
import me.piotreq.snowauth.mysql.ManagerMySQL;
import me.piotreq.snowauth.mysql.MySQLConnect;
import me.piotreq.snowauth.objects.User;

public class Main extends JavaPlugin{

	public static Main inst;
	
	@Override
	public void onEnable() {
    	System.out.println("");
    	System.out.println("[SnowAuth] Uruchamianie pluginu...");
    	System.out.println("");
    	inst = this;
    	saveCommands();
    	saveListeners();
        saveDefaultConfig();
        System.out.println("[SnowAuth] Typ zapisu: "+inst.getConfig().getString("type"));
        if(inst.getConfig().getString("type").equals("mysql")) {
        	MySQLConnect.connect();
        	if(MySQLConnect.isConnected()) {
        		System.out.println("[SnowAuth] Polaczono z baza danych!");
        	}
        	ManagerMySQL.Load();
        	return;
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInst(), new Runnable() {
			
			@Override
			public void run() {
				MySQLConnect.connect();
			}
		}, 20*60*60);
	}
	public void saveCommands() {
		getCommand("login").setExecutor(new LoginCMD());
		getCommand("changepassword").setExecutor(new ChangepassCMD());
		getCommand("register").setExecutor(new RegisterCMD());
		getCommand("unregister").setExecutor(new UnregisterCMD());
	}
	public void saveListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new CMDListener(), this);
		pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new InvListener(), this);
		pm.registerEvents(new JoinQuitListener(), this);
		pm.registerEvents(new MoveListener(), this);
		pm.registerEvents(new PlaceBreakListener(), this);
	}
    public void onDisable() {
    	for(User user : UserManager.users.values()) {
    		if(user.isLogged()) {
    			user.setLogged(false);
    		}    	
    		ManagerMySQL.save(user);
        	return;
    	}
    }
    public static Main getInst() {
    	if(inst == null) {
    		return new Main();
    	}
    	return inst;
    }
}
