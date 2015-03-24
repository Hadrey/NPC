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

	public static Gui file;
	public static Inventory inv;

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
				} else {
					p.sendMessage("nie ma");
				}
			}
		}
	}
}
