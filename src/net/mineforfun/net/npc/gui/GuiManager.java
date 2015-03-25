package net.mineforfun.net.npc.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.mineforfun.net.npc.Main;
import net.mineforfun.net.npc.file.FileManager;

import org.bukkit.Material;
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
	@SuppressWarnings("deprecation")
	public void loadGui() {
		File folder = new File(Main.getInst().getDataFolder() + "/menu");
		File[] listOfFiles = folder.listFiles();

		for (File pliki : listOfFiles) {
			if (pliki.isFile()) {
				YamlConfiguration mYaml = FileManager.getMenuYamlFile(pliki.getName());
				String name = mYaml.getString("menu.name");
				int row = mYaml.getInt("menu.rows");
				int sloty = 9 * row;

				@SuppressWarnings("unused")
				Gui gui = new Gui(pliki.getName(), name, sloty);

				for (String keys : mYaml.getConfigurationSection("icon").getKeys(false)) {
					Inventory inv = GuiManager.getManager().getInv(name);

					String iconName = FileManager.getMenuYamlFile(pliki.getName()).getString("icon." + keys + ".NAME");
					List<String> lore = new ArrayList<String>();
					lore = FileManager.getMenuYamlFile(pliki.getName()).getStringList("icon." + keys + ".LORE");
					int iconSlot = FileManager.getMenuYamlFile(pliki.getName()).getInt("icon." + keys + ".POSITION");
					int iconMaterial = FileManager.getMenuYamlFile(pliki.getName()).getInt("icon." + keys + ".ID");

					iconName = "§r" + iconName;

					ItemStack is = new ItemStack(Material.WOOD, 1);
					is.setTypeId(iconMaterial);
					if (FileManager.getMenuYamlFile(pliki.getName()).contains("icon." + keys + ".DATA")) {
						//int iconData = FileManager.getMenuYamlFile(pliki.getName()).getInt("icon." + keys + ".DATA");
						//MaterialData materialData = is.getData();
						is.getData().setData((byte) 1);
						//materialData.setData((byte) 1);
						//System.out.println(materialData);
						//is.setData(materialData);
						
					}
					ItemMeta im = is.getItemMeta();

					im.setDisplayName(iconName);
					if (FileManager.getMenuYamlFile(pliki.getName()).contains("icon." + keys + ".LORE")) {
						im.setLore(lore);
					}
					is.setItemMeta(im);
					if (FileManager.getMenuYamlFile(pliki.getName()).contains("icon." + keys + ".AMOUNT")) {
						is.setAmount(FileManager.getMenuYamlFile(pliki.getName()).getInt("icon." + keys + ".AMOUNT"));
					}
					
					if (is.getData().getData() == 1) {
						System.out.println(is);
					}

					inv.setItem(iconSlot, is);

					Gui.itemStackOb.put(is, inv);
					Gui.isname.put(is, keys);
				}
			}
		}
	}

	public void createGui(String nazwaPliku, String name, Integer row) {
		@SuppressWarnings("unused")
		Gui gui = new Gui(nazwaPliku, name, row);
	}
}
