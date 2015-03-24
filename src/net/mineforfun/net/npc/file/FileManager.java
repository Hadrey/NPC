package net.mineforfun.net.npc.file;

import java.io.File;
import java.io.IOException;

import net.mineforfun.net.npc.Main;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {

	private static File df = Main.getInst().getDataFolder();
	private static File cfg = new File(df, "config.yml");
	private static File menu = new File(df, "menu");

	private static YamlConfiguration cfgYml;

	// czy istnieje folder
	public static void checkDataFolder() {
		if (!df.exists()) {
			df.mkdir();
		}
	}

	public static void checkMenuFolder() {
		if (!menu.exists()) {
			menu.mkdir();
		}
	}

	// czy istnieje plik
	public static void checkCfg() {
		if (!cfg.exists()) {
			try {
				cfg.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cfgYml = YamlConfiguration.loadConfiguration(cfg);
		
	}

	// save
	public static void saveCfg() {
		try {
			cfgYml.save(cfg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static YamlConfiguration getCfg() {
		return cfgYml;
	}

	public static void reloadCfg() {
		cfgYml = YamlConfiguration.loadConfiguration(cfg);
	}
	
	public static File getMenuFile(String file) {
		return new File(Main.getInst().getDataFolder() + File.separator + "menu" + File.separator
				+ file);
	}

	public static YamlConfiguration getMenuYamlFile(String file) {
		File pFile = FileManager.getMenuFile(file);

		return YamlConfiguration.loadConfiguration(pFile);
	}

	public static void saveMenuFile(File pFile, YamlConfiguration pYaml) {
		try {
			pYaml.save(pFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
