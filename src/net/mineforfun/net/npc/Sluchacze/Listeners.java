package net.mineforfun.net.npc.Sluchacze;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.mineforfun.net.npc.file.FileManager;
import net.mineforfun.net.npc.gui.Gui;
import net.mineforfun.net.npc.gui.GuiManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {

	Gui file;
	Inventory inv;

	@EventHandler
	public void npcInteract(NPCRightClickEvent e) {
		if (FileManager.getCfg().contains(e.getNPC().getName())) {
			file = GuiManager.getManager().getGui(FileManager.getCfg().getString(e.getNPC().getName().toString()));
			inv = GuiManager.getManager().getInv(file.getName());
			Bukkit.getPlayer(e.getClicker().getName()).openInventory(inv);
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		if (i == null) {
			return;
		}
		if (i != null) {
			if (!i.getName().equals(inv.getTitle())) {
				return;
			}
			if (e.getCurrentItem() == null) {
				return;
			} else {
				if (e.getCurrentItem().getItemMeta() == null) {
					return;
				}
			}
		}

		if (i.getName().equals(inv.getName())) {
			/*
			 * if (e.getCurrentItem().getType() == Material.WOOD) {
			 * e.setCancelled(true); Player p = (Player) e.getWhoClicked();
			 * p.sendMessage("Dziala!"); }
			 */
			if (GuiManager.getManager().getInv(inv.getName()).contains(e.getCurrentItem())) {
				Player p = (Player) e.getWhoClicked();
				ItemStack ia = e.getCurrentItem();
				if (Gui.itemStackOb.containsKey(ia)) {
					String key = Gui.isname.get(ia);
					if (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".COMMAND")) {
						String info = FileManager.getMenuYamlFile(file.getNazwaPliku()).getString(
								"icon." + key + ".COMMAND");
						p.performCommand(info);
					}
					if (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".KEEP-OPEN")) {
						if (!(FileManager.getMenuYamlFile(file.getNazwaPliku())
								.getBoolean("icon." + key + ".KEEP-OPEN"))) {
							p.closeInventory();
						}
					} else {
						p.closeInventory();
					}
					e.setCancelled(true);
					// p.sendMessage("jest");
				} else {
					p.sendMessage("nie ma");
				}
			}
		}
		/*
		 * if(e.getWhoClicked() instanceof Player){ Player p = (Player)
		 * e.getWhoClicked(); e.setCancelled(true);
		 * if(Gui.itemStackOb.contains(e.getCurrentItem())){
		 * 
		 * e.setCancelled(true); p.sendMessage("Test"); }else{
		 * p.sendMessage("nie"); }
		 */
		/*
		 * if(e.getCurrentItem().getItemMeta().getDisplayName().equals(rasa1.
		 * getItemMeta().getDisplayName())){ e.setCancelled(true);
		 * p.sendMessage(ChatColor.GOLD + "Rasa 1!"); p.closeInventory(); }else
		 * if(e.getCurrentItem().getItemMeta().getDisplayName().equals(rasa2.
		 * getItemMeta().getDisplayName())){ e.setCancelled(true);
		 * p.sendMessage(ChatColor.GOLD + "Rasa 2!"); p.closeInventory(); }else
		 * if(e.getCurrentItem().getItemMeta().getDisplayName().equals(rasa3.
		 * getItemMeta().getDisplayName())){ e.setCancelled(true);
		 * p.sendMessage(ChatColor.GOLD + "Rasa 3!"); p.closeInventory(); }else{
		 * e.setCancelled(true); // p.closeInventory(); // p.openInventory(inv);
		 * }
		 */
	}
}
