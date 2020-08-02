package com.ksptooi.generaldatacore;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.ksptooi.generaldatacore.common.Project;
import com.ksptooi.generaldatacore.dataInteface.FileDataInteface;
import com.ksptooi.generaldatacore.entity.data.DataMap;


/**
 * ����ģʽ GDC
 */
public class DataCore{

	
	
	

	public static void main(String[] args) {
		System.out.println("GeneralDataCore - Version:"+Project.version);
	}
	
	protected DataCore(){}
	
	
	
	/**
	 * ��ȡ���ݶ���
	 */
	public static DataMap getDataMap(Path path,boolean autoMatic) {
		
		FileDataInteface dataInterface = FileDataInteface.getFileDataInteface(path);
		
		if(dataInterface == null) {
			return null;
		}
		
		DataMap dm = dataInterface.getDataMap();
		
		dm.setAutoMaticRead(autoMatic);
		dm.setAutoMaticWrite(autoMatic);
		
		return dm;
		
	}
	
	public static DataMap getDataMap(Path path) {
		return getDataMap(path,false);
	}
	
	public static DataMap getDataMap(String filePath) {
		return getDataMap(Paths.get(filePath),false);		
	}
	
	public static DataMap getDataMap(File file) {
		return getDataMap(file.toPath(),false);
	}
	
	
	public static DataMap getDataMap(String filePath,boolean automatic) {
		return getDataMap(Paths.get(filePath),automatic);		
	}
	
	public static DataMap getDataMap(File file,boolean automatic) {
		return getDataMap(file.toPath(),automatic);
	}


	
	
	
	
	
	
	

}
