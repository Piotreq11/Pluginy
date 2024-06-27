package me.piotreq.snowstats.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.piotreq.snowstats.Main;
import me.piotreq.snowstats.managers.UserManager;
import me.piotreq.snowstats.objects.User;

public class BreakListener implements Listener{

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		User u = UserManager.getUser(p.getName());
		u.setWykopane_bloki(u.getWykopane_bloki()+1);
		if(b.getType().equals(Material.STONE)) {
			u.setWykopany_stone(u.getWykopany_stone()+1);
			u.setExp_stone(u.getExp_stone()+ 0.01* Main.getInst().getConfig().getInt("exp_stone"));
		}
		if((b.getType().equals(Material.ACACIA_LOG) || b.getType().equals(Material.BIRCH_LOG) || b.getType().equals(Material.DARK_OAK_LOG) || b.getType().equals(Material.JUNGLE_LOG) || b.getType().equals(Material.OAK_LOG) || b.getType().equals(Material.SPRUCE_LOG))){
			u.setWykopane_drewno(u.getWykopane_drewno()+1);
			u.setExp_drewno(u.getExp_drewno() +0.01* Main.getInst().getConfig().getInt("exp_drewno"));
		}
		if(b.getType().equals(Material.OBSIDIAN)) {
			u.setWykopany_obs(u.getWykopany_obs()+1);
		}
		if(u.getExp_stone() >= u.getWymagany_stone()) {
			u.setExp_stone(0);
			u.setLvl_stone(u.getLvl_stone()+1);
			u.setMonety(u.getMonety()+100);
			u.setWymagany_stone(u.getWymagany_stone()+150);
			p.sendMessage("§6GRATULACJE! awansowales na §a" + u.getLvl_stone() + " §6poziom stone!");
		}
		if(u.getExp_drewno() >= u.getWymagany_drewno()) {
			u.setExp_drewno(0);
			u.setLvl_drewno(u.getLvl_drewno()+1);
			u.setMonety(u.getMonety()+100);
			u.setWymagany_drewno(u.getWymagany_drewno()+150);
			p.sendMessage("§6GRATULACJE! awansowales na §a" + u.getLvl_drewno() + " §6poziom drewna!");
		}
	}
}
