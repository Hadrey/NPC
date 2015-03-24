package net.mineforfun.net.npc.komendy;

import net.mineforfun.net.npc.gui.Gui;
import net.mineforfun.net.npc.gui.GuiManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

public class Komendy implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("npcd")) {
			if (args.length == 0) {
				sender.sendMessage("§c/npcd create <name> <int>");
				return true;
			} else {
				if (args[0].equalsIgnoreCase("create")) {
					GuiManager.getManager().createGui(args[1], "test", Integer.parseInt(args[2]));
					sender.sendMessage("Utworzono gui o nazwie: " + args[1] + ", i slotach: " + args[2]);
				} else if (args[0].equalsIgnoreCase("open")) {
					Gui file = GuiManager.getManager().getGui(args[1]);
					sender.sendMessage("file: " + file.getNazwaPliku().toString());
					Inventory inv = GuiManager.getManager().getInv(file.getName());
					Bukkit.getPlayer(sender.getName().toString()).openInventory(inv);
					sender.sendMessage("Otworzyles menu o nazwie: " + args[1]);
				}
			}
		}
		return false;
	}

}
