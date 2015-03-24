package net.mineforfun.net.npc.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class Gui implements Listener {
	
	private Inventory inv;
	
	private String name;
	private String nazwaPliku;
	
	private int sloty;
	
/*	public static Inventory getInventory(Integer sloty, String name){
		inv = Bukkit.createInventory(null, 9, name);
		return inv;
	}*/
	
	public static ArrayList<Gui> guiObject = new ArrayList<Gui>();
	public static ArrayList<Inventory> invOb = new ArrayList<Inventory>();
	
	public Gui(String nazwaPliku, String name, Integer sloty){
		this.nazwaPliku = nazwaPliku;
		this.name = name;
		this.sloty = sloty;
		this.inv = Bukkit.createInventory(null, sloty, name);
		invOb.add(inv);
		
		
/*		inv = Bukkit.createInventory(null, 9, name);
		for(int i = 0; i == sloty; i++){
			ItemStack is = new ItemStack(Material.WOOD, 1);
			ItemMeta im = is.getItemMeta();
			
			is.setItemMeta(im);
			inv.setItem(i, is);
		}*/
		guiObject.add(this);
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getSloty(){
		return sloty;
	}
	
	public void setSloty(Integer sloty){
		this.sloty = sloty;
	}
	
	public String getNazwaPliku(){
		return nazwaPliku;
	}
	
	public void setNazwaPliku(String nazwaPliku){
		this.nazwaPliku = nazwaPliku;
	}
	
}
