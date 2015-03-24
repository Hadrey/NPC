package net.mineforfun.net.npc.gui;

import java.io.File;

import net.mineforfun.net.npc.Main;
import net.mineforfun.net.npc.file.FileManager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;

public class GuiManager {
	
	private static GuiManager gm = new GuiManager();
	
	public static GuiManager getManager(){
		return gm;
	}
	
	public Gui getGui(String name){
		for(Gui a : Gui.guiObject){
			if(a.getNazwaPliku().equalsIgnoreCase(name)){
				return a;
			}
		}
		return null;
	}
	public Inventory getInv(String name){
		for(Inventory a : Gui.invOb){
			if(a.getName().equalsIgnoreCase(name)){
				return a;
			}
		}
		return null;
	}
	// String keys : FileManager.getAreny().getConfigurationSection("Areny").getKeys(false))
	public void loadGui(){
		File folder = new File(Main.getInst().getDataFolder() + "/menu");
		File[] listOfFiles = folder.listFiles();
		
		for(File pliki : listOfFiles){
			if(pliki.isFile()){
				System.out.println(pliki.getName());
				YamlConfiguration mYaml = FileManager.getMenuYamlFile(pliki.getName());
				String name = mYaml.getString("menu.name");
				System.out.println(name);
				int row = mYaml.getInt("menu.rows");
				System.out.println(row);
				
				@SuppressWarnings("unused")
				Gui gui = new Gui(pliki.getName(), name, row);
				
				System.out.println("Zaladowano gui: " + Gui.invOb.size());
			}
		}
		
		/*
		File pFile = FileManager;
		YamlConfiguration pYaml = FileManager;
		
		if(!pFile.exists()){
			try {
				pFile.createNewFile();
				
				String pn = p.getName();
				String uuid = p.getUniqueId().toString();
				
				pYaml.set("Nick", pn);
				pYaml.set("UUID", uuid);
				pYaml.set("Kills", 0);
				pYaml.set("Dead", 0);
				pYaml.set("Money", 0);
				
				FileManager.savePlayerFile(pFile, pYaml);
				p.sendMessage("Utworzono plik");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public void createGui(String nazwaPliku, String name, Integer sloty){
		@SuppressWarnings("unused")
		Gui gui = new Gui(nazwaPliku, name, sloty);
	}
}
