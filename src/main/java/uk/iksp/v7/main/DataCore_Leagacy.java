package uk.iksp.v7.main;

import com.ksptooi.v5.Log.LogManager;
import com.ksptooi.v5.Log.gdcLog;

public class DataCore_Leagacy {

	
	public static void main(String[] args) {
		
		System.out.println("GeneralDataCore�汾:"+DataCore_Leagacy.gdc_Version);
		
		System.out.println("GeneralDataCore - A");
		
	}
	
	
	public static final String gdc_Version="V7.21-A";
	
	public static LogManager LogManager=new gdcLog();
	
	public static String getVersion() {
		return gdc_Version;
	}
	
	
}
