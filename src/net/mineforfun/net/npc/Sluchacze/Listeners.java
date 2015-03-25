package net.mineforfun.net.npc.Sluchacze;

import java.util.ArrayList;
import java.util.List;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.mineforfun.net.npc.Main;
import net.mineforfun.net.npc.file.FileManager;
import net.mineforfun.net.npc.gui.Gui;
import net.mineforfun.net.npc.gui.GuiManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {

	public static Gui file;
	public static Inventory inv;

	@EventHandler
	public void npcInteract(NPCRightClickEvent e) {
		/* if (e.getClicker().hasPermission("npcg.click")) { */
		if (FileManager.getCfg().contains("NPC." + e.getNPC().getName())) {
			file = GuiManager.getManager().getGui(
					FileManager.getCfg().getString("NPC." + e.getNPC().getName().toString()));
			inv = GuiManager.getManager().getInv(file.getName());
			Bukkit.getPlayer(e.getClicker().getName()).openInventory(inv);
		}
		/* } */
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Inventory i = e.getPlayer().getInventory();
		if (i == null) {
			return;
		}
		if (i != null) {
			if (e.getPlayer().getItemInHand() == null) {
				return;
			} else {
				if (e.getPlayer().getItemInHand().getItemMeta() == null) {
					return;
				}
				List<String> lore = new ArrayList<String>();
				lore = FileManager.getCfg().getStringList("ITEM." + "przedmiot1" + ".LORE");
				if ((e.getPlayer().getItemInHand().getItemMeta().hasDisplayName())
						&& (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(FileManager.getCfg().getString("ITEM." + "przedmiot1" + ".NAME"))) 
								&& (e.getPlayer().getItemInHand().getItemMeta().hasLore()) 
								&& (e.getPlayer().getItemInHand().getItemMeta().getLore().equals(lore))) {
					if ((e.getAction() == Action.RIGHT_CLICK_AIR)) {
						file = GuiManager.getManager().getGui(
								FileManager.getCfg().getString("ITEM." + "przedmiot1" + ".GUI"));
						inv = GuiManager.getManager().getInv(file.getName());
						Bukkit.getPlayer(e.getPlayer().getName()).openInventory(inv);
					}
				}else{
					return;
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		if (i == null) {
			return;
		}
		if (i != null) {
			if (inv == null) {
				return;
			}
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
			e.setCancelled(true);
			if (GuiManager.getManager().getInv(inv.getName()).contains(e.getCurrentItem())) {
				Player p = (Player) e.getWhoClicked();
				ItemStack ia = e.getCurrentItem();
				if (Gui.itemStackOb.containsKey(ia)) {
					String key = Gui.isname.get(ia);
					// PERMISSION
					if (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".PERMISSION")) {
						if (!(p.hasPermission(FileManager.getMenuYamlFile(file.getNazwaPliku()).getString(
								"icon." + key + ".PERMISSION")))) {
							e.setCancelled(true);
							if (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains(
									"icon." + key + ".PERMISSION-MESSAGE")) {
								String noPerm = FileManager.getMenuYamlFile(file.getNazwaPliku()).getString(
										"icon." + key + ".PERMISSION-MESSAGE");
								p.sendMessage(noPerm);
							} else {
								String noPerm = "Nie masz uprawnien do tej ikony!";
								p.sendMessage(noPerm);
							}

							return;
						}
					}
					// VAULT

					if (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".PRICE")) {
						Double price = FileManager.getMenuYamlFile(file.getNazwaPliku()).getDouble(
								"icon." + key + ".PRICE");
						if (Main.getInst().econ.has(p, price)) {
							Main.getInst().econ.withdrawPlayer(p, price);
						} else {
							p.sendMessage("Nie masz kasy!");
							return;
						}
						if ((FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".PRICE-ITEM"))
								&& (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key
										+ ".PRICE-ITEM-AMOUNT"))) {
							int type = FileManager.getMenuYamlFile(file.getNazwaPliku()).getInt(
									"icon." + key + ".PRICE-ITEM");
							int amount = FileManager.getMenuYamlFile(file.getNazwaPliku()).getInt(
									"icon." + key + ".PRICE-ITEM-AMOUNT");
							ItemStack is = new ItemStack(Material.STONE, 1);
							is.setTypeId(type);
							is.setAmount(amount);
							p.getInventory().addItem(is);
						}

					}
					// REQUIRED ITEM
					if ((FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".R-ITEM"))
							&& (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key
									+ ".R-ITEM-AMOUNT"))) {
						int type = FileManager.getMenuYamlFile(file.getNazwaPliku()).getInt(
								"icon." + key + ".R-ITEM");
						int amount = FileManager.getMenuYamlFile(file.getNazwaPliku()).getInt(
								"icon." + key + ".R-ITEM-AMOUNT");

						if (p.getInventory().contains(type, amount)) {
							ItemStack is = new ItemStack(Material.STONE, 1);
							is.setTypeId(type);
							is.setAmount(amount);
							p.getInventory().removeItem(is);
						} else {
							p.sendMessage("Brakuje ci przedmiotu!");
							return;
						}
						if (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".R-PRICE")) {
							Double rPrice = FileManager.getMenuYamlFile(file.getNazwaPliku()).getDouble(
									"icon." + key + ".R-PRICE");
							Main.getInst().econ.depositPlayer(p, rPrice);
						}
					}
					// COMMAND
					if (FileManager.getMenuYamlFile(file.getNazwaPliku()).contains("icon." + key + ".COMMAND")) {
						String info = FileManager.getMenuYamlFile(file.getNazwaPliku()).getString(
								"icon." + key + ".COMMAND");
						p.performCommand(info);
					}
					// KEEP-OPEN
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
