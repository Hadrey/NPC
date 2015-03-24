package net.mineforfun.net.npc.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.mineforfun.net.npc.Main;
import net.mineforfun.net.npc.file.FileManager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiManager {

	private static GuiManager gm = new GuiManager();

	public static GuiManager getManager() {
		return gm;
	}

	public Gui getGui(String name) {
		for (Gui a : Gui.guiObject) {
			if (a.getNazwaPliku().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}

	public Inventory getInv(String name) {
		for (Inventory a : Gui.invOb) {
			if (a.getName().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}

	// String keys :
	// FileManager.getAreny().getConfigurationSection("Areny").getKeys(false))
	public void loadGui() {
		File folder = new File(Main.getInst().getDataFolder() + "/menu");
		File[] listOfFiles = folder.listFiles();

		for (File pliki : listOfFiles) {
			if (pliki.isFile()) {
				System.out.println(pliki.getName());
				YamlConfiguration mYaml = FileManager.getMenuYamlFile(pliki.getName());
				String name = mYaml.getString("menu.name");
				System.out.println(name);
				int row = mYaml.getInt("menu.rows");
				System.out.println(row);

				@SuppressWarnings("unused")
				Gui gui = new Gui(pliki.getName(), name, row);

				System.out.println("Zaladowano gui: " + Gui.invOb.size());

				for (String keys : mYaml.getConfigurationSection("icon").getKeys(false)) {
					/*
					 * inv = Bukkit.createInventory(null, 9, name); for(int i =
					 * 0; i == sloty; i++){ ItemStack is = new
					 * ItemStack(Material.WOOD, 1); ItemMeta im =
					 * is.getItemMeta();
					 * 
					 * is.setItemMeta(im); inv.setItem(i, is); }
					 */
					Inventory inv = GuiManager.getManager().getInv(name);

					String iconName = FileManager.getMenuYamlFile(pliki.getName()).getString("icon." + keys + ".NAME");
					List<String> lore = new ArrayList<String>();
					lore = FileManager.getMenuYamlFile(pliki.getName()).getStringList("icon." + keys + ".LORE");
					int iconSlot = FileManager.getMenuYamlFile(pliki.getName()).getInt("icon." + keys + ".POSITION");
					int iconMaterial = FileManager.getMenuYamlFile(pliki.getName()).getInt("icon." + keys + ".ID");
					iconName = "§r" + iconName;
					// System.out.println("test: " + iconName);

					@SuppressWarnings("deprecation")
					ItemStack is = new ItemStack(iconMaterial, 1);
					System.out.println(iconMaterial);
					ItemMeta im = is.getItemMeta();

					im.setDisplayName(iconName);
					if (FileManager.getMenuYamlFile(pliki.getName()).contains("icon." + keys + ".LORE")) {
						im.setLore(lore);
					}

					is.setItemMeta(im);
					inv.setItem(iconSlot, is);
					// Gui.itemStackOb.add(is);
					Gui.itemStackOb.put(is, inv);
					Gui.isname.put(is, keys);
				}
			}
		}

		/*
		 * File pFile = FileManager; YamlConfiguration pYaml = FileManager;
		 * 
		 * if(!pFile.exists()){ try { pFile.createNewFile();
		 * 
		 * String pn = p.getName(); String uuid = p.getUniqueId().toString();
		 * 
		 * pYaml.set("Nick", pn); pYaml.set("UUID", uuid); pYaml.set("Kills",
		 * 0); pYaml.set("Dead", 0); pYaml.set("Money", 0);
		 * 
		 * FileManager.savePlayerFile(pFile, pYaml);
		 * p.sendMessage("Utworzono plik"); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */
	}

	public void createGui(String nazwaPliku, String name, Integer sloty) {
		@SuppressWarnings("unused")
		Gui gui = new Gui(nazwaPliku, name, sloty);
	}
}
