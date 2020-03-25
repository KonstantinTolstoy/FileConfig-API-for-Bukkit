package org.yourcompany.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileConfig {
	private File yml;
	public YamlConfiguration config;
	
	public FileConfig(JavaPlugin plugin, String fileName) {
		yml = new File(plugin.getDataFolder() + "/" + fileName);
		config = YamlConfiguration.loadConfiguration(yml);
	}
	
	public void setDefault(String path, Object value) {
		config.set(path, value);
	}
	
	public void addToList(String path, Object value) {
		if(!config.contains(path))
		{
			List<Object> list = new ArrayList<Object>();
			list.add(value);
			config.set(path, list);
		}
		else
		{
			List<Object> list = (List<Object>)config.getList(path);
			list.add(value);
			config.set(path, list);
		}
	}
	
	public void removeFromList(String path, Object value) {
		if(!config.contains(path))
			return;
		else
		{
			List<Object> list = (List<Object>)config.getList(path);
			list.remove(value);
			config.set(path, list);
		}
	}
	
	public String getString(String path) {
		return config.getString(path);
	}
	
	public int getInt(String path) {
		return config.getInt(path);
	}
	
	public float getFloat(String path) {
		return (float)config.getDouble(path);
	}
	
	public boolean getBool(String path) {
		return config.getBoolean(path);
	}
	
	public List<String> getStringList(String path) {
		return config.getStringList(path);
	}
	
	public List<Integer> getIntegerList(String path) {
		return config.getIntegerList(path);
	}
	
	public void save() {
		try {
			config.save(yml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			config.load(yml);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
