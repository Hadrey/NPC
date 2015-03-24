package net.mineforfun.net.npc.gui;

import org.bukkit.inventory.Inventory;

public class GuiManager {
	
	private static GuiManager gm = new GuiManager();
	
	public static GuiManager getManager(){
		return gm;
	}
	
	public Gui getGui(String name){
		for(Gui a : Gui.guiObject){
			if(a.getName().equals(name)){
				return a;
			}
		}
		return null;
	}
	public Inventory getInv(String name){
		for(Inventory a : Gui.invOb){
			if(a.getName().equals(name)){
				return a;
			}
		}
		return null;
	}
	
	
	public void createGui(String name, Integer sloty){
		@SuppressWarnings("unused")
		Gui gui = new Gui(name, sloty);
	}
}
