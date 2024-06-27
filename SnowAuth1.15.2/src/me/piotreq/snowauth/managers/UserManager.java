package me.piotreq.snowauth.managers;

import java.util.Map;
import java.util.HashMap;

import me.piotreq.snowauth.objects.User;

public class UserManager {

	public static final Map<String, User> users = new HashMap<>();
	public static void addUser(String pName, String password, boolean logged, boolean account, String IP, int joins, String UUID) {
		users.put(pName.toLowerCase(), new User(pName, password, logged, account, IP, joins, UUID));
	}
	public static User getUser(String pName) {
		if(!users.containsKey(pName.toLowerCase())) {
			users.put(pName.toLowerCase(), new User(pName, "", false, false, "", 0, ""));
		}
		return users.get(pName.toLowerCase());
	}
}
