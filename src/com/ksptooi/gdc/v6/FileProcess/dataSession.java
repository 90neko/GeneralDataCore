package com.ksptooi.gdc.v6.FileProcess;

import java.io.File;
import java.util.ArrayList;
import com.ksptooi.gdc.v6.FileIO.DataIO_v2;
import com.ksptooi.gdc.v6.FileIO.GeneralDataIO;
import com.ksptooi.gdc.v6.Manager.DataManager;

public class dataSession{

	
	DataIO_v2 GFI= new GeneralDataIO();
	
	private File dataSources=null;
	
	private DataManager fromFactory=null;
	
	private String Symbol="=";
	
	
	
	//����
	public dataSession(DataManager fromFactory){
		
		this.fromFactory=fromFactory;
		
		//�ж�Factory�Ƿ�Ϊ��
		if(this.fromFactory == null) {
			throw new RuntimeException("DataFactory is null");
		}
		
		//�ж�Factory�Ƿ���really
		if(!(this.fromFactory instanceof DataManager)) {
			throw new RuntimeException("DataFactory is not really");
		}
		
	}
	
	
	//����µ�Key ��Value
	public void put(String key,String value) {
			
	}
	
	public void put(String key,ArrayList<String> value) {
		
	}
	
	public void put(String key,double value) {
		
	}
	
	public void put(String key,int value) {
		
	}
	
	
	
	
	
}
