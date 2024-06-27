package me.piotreq.snowauth.configs;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;

import me.piotreq.snowauth.Main;
import me.piotreq.snowauth.utils.ChatUtil;

public class Messages {

	private static Messages instance;
	private static File messages = new File(Main.getInst().getDataFolder(), "messages.yml");
	
	private HashMap<String, String> single = new HashMap<>();
	private HashMap<String, List<String>> multiple = new HashMap<>();
	
	public static void loadDefaultFiles(String[] files) {
		for(String file: files) {
			File cfg = new File(Main.getInst().getDataFolder() + File.separator + file);
			if(!cfg.exists()) {
				Main.getInst().saveResource(file, true);
			}
		}
	}
	public Messages() {
		instance = this;
		loadDefaultFiles(new String[] {"messages.yml"});
		Yamler pc = loadConfiguration();
		if(pc == null) {
			return;
		}
		for(String key : pc.getKeys(true)) {
			if(key.toLowerCase().contains("list")) {
				List<String> list = pc.getStringList(key);
				if(list == null) {
					continue;
				}
				for(int i = 0; i < list.size(); i++) {
					list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
				}
				multiple.put(key, list);
				continue;
			}
			String message = ChatColor.translateAlternateColorCodes('&', pc.getString(key));
			single.put(key, message);
		}
	}
	private Yamler loadConfiguration() {
		Yamler pc = new Yamler(messages);
		return pc;
	}
	public String getMessage(String key) {
		String s = single.get(key);
		if(s == null) {
			return ChatUtil.fixColor("&cMessage '" + key + "' not found");
		}else {
			return s;
		}
	}
	public List<String> getList(String key){
		Yamler pc = loadConfiguration();
		return pc.getStringList(key);
	}
	public static Messages getInst() {
		if(instance != null) {
			return instance;
		}
		return new Messages();
	}
}
