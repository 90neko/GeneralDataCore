package com.ksptooi.v1.BukkitService;


import org.bukkit.plugin.java.JavaPlugin;

import uk.iksp.v7.main.DataCore;

public class BukkitSupport extends JavaPlugin{

	
	public void onEnable(){
		
		DataCore.LogManager.logInfo("ͨ�����ݺ��� �汾:"+DataCore.gdc_Version);
		
	}
	
	
	
}

