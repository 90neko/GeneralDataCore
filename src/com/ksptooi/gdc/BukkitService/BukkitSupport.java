package com.ksptooi.gdc.BukkitService;


import org.bukkit.plugin.java.JavaPlugin;

import com.ksptooi.gdc.Main.DataCore;

public class BukkitSupport extends JavaPlugin{

	
	public void onEnable(){
		
		DataCore.LogManager.logInfo("ͨ�����ݺ��� �汾:"+DataCore.gdc_Version);
		
	}
	
	
	
}

