package net.mineforfun.net.npc;

import net.mineforfun.net.npc.gui.GuiManager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public void onEnable(){
		instance = this;
		
		Inventory i = GuiManager.getManager().getInv("test");
		Player p = null;
		p.openInventory(i);
		
	}
	
	public static Main getInst(){
		return instance;
	}
}
