package com.ksptooi.gdc.BukkitService;

import org.bukkit.plugin.java.JavaPlugin;
import com.ksptooi.gdc.Util.Var;

public class BukkitSupport extends JavaPlugin{

	
	public void onEnable(){
		
		Var.LogManager.writeLogOfINFO("ͨ�����ݺ��� �汾:"+Var.gdc_Version);
		
	}
	
	
	
}
