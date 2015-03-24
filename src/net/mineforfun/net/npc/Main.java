package net.mineforfun.net.npc;

import net.mineforfun.net.npc.Sluchacze.Listeners;
import net.mineforfun.net.npc.file.FileManager;
import net.mineforfun.net.npc.gui.Gui;
import net.mineforfun.net.npc.gui.GuiManager;
import net.mineforfun.net.npc.komendy.Komendy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public void onEnable(){
		instance = this;
		getCommand("npcg").setExecutor(new Komendy());
		
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		
		FileManager.checkDataFolder();
		FileManager.checkMenuFolder();
		FileManager.checkCfg();
		
		GuiManager.getManager().loadGui();
	}
	
	public void onDisable(){
		Gui.isname.clear();
		Gui.in.clear();
		Gui.invOb.clear();
		Gui.itemStackOb.clear();
	}
	
	public static Main getInst(){
		return instance;
	}
}
