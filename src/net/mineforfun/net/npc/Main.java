package net.mineforfun.net.npc;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.mineforfun.net.npc.Sluchacze.Listeners;
import net.mineforfun.net.npc.file.FileManager;
import net.mineforfun.net.npc.gui.Gui;
import net.mineforfun.net.npc.gui.GuiManager;
import net.mineforfun.net.npc.komendy.Komendy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Main instance;

	public Economy econ = null;
	public Permission perms = null;
	public Chat chat = null;

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}/*
	private boolean setupChat(){
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null;
	}
	private boolean setupPermissions(){
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}*/

	public void onEnable() {
		instance = this;

		// Vault
		if (!setupEconomy()) {
			getServer().getPluginManager().disablePlugin(this);
			return;
		}/*
		setupChat();
		setupPermissions();*/

		getCommand("npcg").setExecutor(new Komendy());

		Bukkit.getPluginManager().registerEvents(new Listeners(), this);

		FileManager.checkDataFolder();
		FileManager.checkMenuFolder();
		FileManager.checkCfg();

		GuiManager.getManager().loadGui();
	}

	public void onDisable() {
		Gui.isname.clear();
		Gui.in.clear();
		Gui.invOb.clear();
		Gui.itemStackOb.clear();
	}

	public static Main getInst() {
		return instance;
	}
}
