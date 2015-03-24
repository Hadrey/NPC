package net.mineforfun.net.npc;

import net.mineforfun.net.npc.komendy.Komendy;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public void onEnable(){
		instance = this;
		getCommand("npcd").setExecutor(new Komendy());
	}
	
	public static Main getInst(){
		return instance;
	}
}
