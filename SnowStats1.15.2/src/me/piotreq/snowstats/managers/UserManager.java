package me.piotreq.snowstats.managers;

import java.util.HashMap;
import java.util.Map;

import me.piotreq.snowstats.objects.User;

public class UserManager {

	public static final Map<String, User> users = new HashMap<>();
	public static void addUser(String name, String UUID, int dystans, int wykopane_bloki, float exp_stone,float exp_drewno,int wykopany_stone,int wykopany_obs,int wykopane_drewno,int jedzenie,int smierci,int kille,int zabite_moby,int monety,int lvl_stone, int lvl_drewno, int postawione_bloki, int wymagany_stone, int wymagany_drewno, int do_stone, int do_drewna) {
		users.put(name.toLowerCase(), new User(dystans, wykopane_bloki, exp_stone, exp_drewno, wykopany_stone, wykopany_obs, wykopane_drewno, jedzenie, smierci, kille, zabite_moby, name, UUID, monety, lvl_stone, lvl_drewno, postawione_bloki, wymagany_stone, wymagany_drewno, do_stone, do_drewna));
	}
	public static User getUser(String name) {
		if(!users.containsKey(name.toLowerCase())) {
			users.put(name.toLowerCase(), new User(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, name, "", 0, 0, 0,0, 1000, 1000, 0,0));
		}
		return users.get(name.toLowerCase());
	}
}
