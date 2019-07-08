package com.ksptooi.gdc.v6.Factory;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.ksptooi.gdc.v6.Session.dataSession;

public class DataSessionFactory{
	
	
	public LinkedList<dataSession> listDataSession = new LinkedList<dataSession>();
	
	public int poolInitSize=64;
	
	
	//���췽��
	public DataSessionFactory(int size) {

		
		this.poolInitSize = size;

		for (int i = 0; i < this.poolInitSize; i++) {

			dataSession ds = new dataSession(this);

			listDataSession.add(ds);

		}

	}
	
	
	//��һ������Session
	public dataSession openSession(File file) {
		
		if(listDataSession.size() > 0) {
			
			dataSession dataSession = listDataSession.removeFirst();
			
			dataSession.assign(this,file);
			
			return dataSession;
		}
		
		throw new RuntimeException("û�пɷ����DataSession!");		
	}
	
	
	public boolean createdata(File file) {
		
		if(file.exists()){
			return false;
		}
		
		//�����ļ�
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		dataSession dataSession = this.openSession(file);

		
		dataSession.put("@Type", "GeneralDataCore");
		dataSession.put("@Version", "6");
		dataSession.addline(" ");

		dataSession.release();
		
		return true;
		
	}
	
	
	//getseter
	public LinkedList<dataSession> getListDataSession() {
		return listDataSession;
	}

	public void setListDataSession(LinkedList<dataSession> listDataSession) {
		this.listDataSession = listDataSession;
	}


	public void destoryFactory() {

		listDataSession = null;
		poolInitSize=0;
		
	}
	
	
}
