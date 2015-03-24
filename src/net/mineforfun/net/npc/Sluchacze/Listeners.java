package net.mineforfun.net.npc.Sluchacze;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.mineforfun.net.npc.file.FileManager;
import net.mineforfun.net.npc.gui.Gui;
import net.mineforfun.net.npc.gui.GuiManager;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class Listeners implements Listener{

	@EventHandler
	public void npcInteract(NPCRightClickEvent e){
		if(FileManager.getCfg().contains(e.getNPC().getName())){
			Gui file = GuiManager.getManager().getGui(FileManager.getCfg().getString(e.getNPC().getName().toString()));
			Inventory inv = GuiManager.getManager().getInv(file.getName());
			Bukkit.getPlayer(e.getClicker().getName()).openInventory(inv);
		}
	}
}
