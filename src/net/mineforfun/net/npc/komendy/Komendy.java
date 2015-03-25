package net.mineforfun.net.npc.komendy;

import java.util.ArrayList;
import java.util.List;

import net.mineforfun.net.npc.Sluchacze.Listeners;
import net.mineforfun.net.npc.file.FileManager;
import net.mineforfun.net.npc.gui.Gui;
import net.mineforfun.net.npc.gui.GuiManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Komendy implements CommandExecutor {

	private ItemStack itemCreate = new ItemStack(Material.STONE, 1);

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("npcg")) {
			if (sender.hasPermission("npcd.*")) {
				if (args.length == 0) {
					sender.sendMessage("§c/npcg create <name> <int>");
					sender.sendMessage("§c/npcg createitem <name>");
					sender.sendMessage("§c/npcg open <name.yml>");
					sender.sendMessage("§c/npcg remove <name>");
					sender.sendMessage("§c/npcg lista");
					sender.sendMessage("§c/npcg reload");
					return true;
				} else {
					if (args[0].equalsIgnoreCase("create")) {
						FileManager.getCfg().set(args[1], args[2]);
						sender.sendMessage("Utworzono gui o nazwie: " + args[1] + ", i slotach: " + args[2]);
						FileManager.saveCfg();
					} else if (args[0].equalsIgnoreCase("createitem")) {
						if (FileManager.getCfg().contains("ITEM." + args[1])) {
							Player p = Bukkit.getServer().getPlayer(sender.getName().toString());
							String name = FileManager.getCfg().getString("ITEM." + args[1] + ".NAME");
							List<String> lore = new ArrayList<String>();
							lore = FileManager.getCfg().getStringList("ITEM." + args[1] + ".LORE");

							int type = FileManager.getCfg().getInt("ITEM." + args[1] + ".ID");

							itemCreate.setTypeId(type);
							ItemMeta im = itemCreate.getItemMeta();
							im.setDisplayName(name);
							im.setLore(lore);
							itemCreate.setItemMeta(im);
							p.getInventory().addItem(itemCreate);
							sender.sendMessage("Stworzono przedmiot!");
						}else{
							sender.sendMessage("Nie ma takiego przedmiotu!");
						}

					} else if (args[0].equalsIgnoreCase("open")) {

						Listeners.file = GuiManager.getManager().getGui(args[1]);
						Listeners.inv = GuiManager.getManager().getInv(Listeners.file.getName());
						Bukkit.getPlayer(sender.getName().toString()).openInventory(Listeners.inv);
						sender.sendMessage("Otworzyles menu o nazwie: " + args[1]);
					} else if (args[0].equalsIgnoreCase("lista")) {
						sender.sendMessage("Test: " + Gui.itemStackOb.toString());
					} else if (args[0].equalsIgnoreCase("reload")) {
						FileManager.reloadCfg();
					} else if (args[0].equalsIgnoreCase("remove")) {
						FileManager.getCfg().set(args[1], null);
						FileManager.saveCfg();
					}
				}
			}
		}
		return false;
	}

}
