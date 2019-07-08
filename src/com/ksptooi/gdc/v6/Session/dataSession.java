package com.ksptooi.gdc.v6.Session;

import java.io.File;
import java.util.ArrayList;
import com.ksptooi.gdc.Entity.GDCEntity;
import com.ksptooi.gdc.Main.gdcList;
import com.ksptooi.gdc.v6.Factory.DataSessionFactory;
import com.ksptooi.gdc.v6.FileIO.GeneralFileIO;
import com.ksptooi.gdc.v6.FileProcess.dataProcess;

public class dataSession{

	//IO
	GeneralFileIO io= new GeneralFileIO();
	
	private boolean isChange=false;
	
	//���ݻ���
	GDCEntity data=null;
	
	//����Դ
	private File dataSources=null;
	
	//Factory
	private DataSessionFactory fromFactory=null;
	
	private boolean isRelease=true;
	
	//Process
	dataProcess process=new dataProcess();
	
	
	//����
	public dataSession(DataSessionFactory fromFactory){
		
		this.fromFactory=fromFactory;
		
		//�ж�Factory�Ƿ�Ϊ��
		if(this.fromFactory == null) {
			throw new RuntimeException("DataFactory is null");
		}
		
		//�ж�Factory�Ƿ���really
		if(!(this.fromFactory instanceof DataSessionFactory)) {
			throw new RuntimeException("DataFactory is not really");
		}
		
	}
	
	//����
	public void assign(DataSessionFactory df,File dataSources) {
		
		if(this.fromFactory == null) {
			throw new RuntimeException("DataFactory is null");
		}
		
		if(!(this.fromFactory instanceof DataSessionFactory)) {
			throw new RuntimeException("DataFactory is not really");
		}
		
		this.isRelease=false;
		
		//����ļ����������ջظ�Session
		if(!dataSources.exists()) {
			this.release();
			throw new RuntimeException("û���ҵ����ļ�.");
		}
		
		//��������Դ
		this.dataSources=dataSources;
		
		data = io.getGDCEntity(this.dataSources);
		
	}
	
	
	//�ͷ�
	public void release() {
		
		
		if(isChange) {
			io.writeGDCEntity(dataSources, data);
			System.out.println("����д��");
		}	
		
		data = null;
		dataSources = null;
		
		this.isRelease=true;
		this.isChange=false;
		
		fromFactory.getListDataSession().add(this);
		
		System.out.println("�ͷ�Session:"+this);
		
		System.out.println("Session�ش�С:"+fromFactory.getListDataSession().size());
		
	}
	
	//�ж��Ƿ���ִ��
	private boolean isRelease() {
		
		if(this.isRelease) {
			throw new RuntimeException("��Session�ѱ��ͷ�.");	
		}
		
		return false;
	}
	
	
	//����µ�Key ��Value
	public void put(String key,String value) {	
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.data=process.put(data, key, value);
	
	}
	
	public void put(String key,ArrayList<String> value) {
		
		if(isRelease()) {
			return;
		}
		
		gdcList list=new gdcList(value);
		
		this.put(key, list.toString());
	}
		
	public void put(String key,double value) {
		
		if(isRelease()) {
			return;
		}
		
		this.put(key, String.valueOf(value));
		
	}
	
	public void put(String key,int value) {
		if(isRelease()) {
			return;
		}
		
		this.put(key, String.valueOf(value));
	}
	
	
	public void addline(String value) {
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		process.addline(data, value);
		
	}
			
	
	
	//�޸�key��value
	public void set(String key,String value) {
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.data=process.set(this.data, key, value);
		
	}
	
	public void set(String key,int value) {
		
		if(isRelease()) {
			return;
		}
		
		this.set(key, String.valueOf(value));
		
	}
	
	public void set(String key,double value) {
		
		if(isRelease()) {
			return;
		}
		
		this.set(key, String.valueOf(value));
		
	}
	
	public void set(String key,ArrayList<String> value) {
		
		if(isRelease()) {
			return;
		}
		
		gdcList list = new gdcList(value);
		
		this.set(key, list.toString());
		
	}
	
	
	//ɾ��
	public void remove(String key) {
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.data=process.remove(this.data, key);
	}
	
	
	//��ѯ
	public String get(String key) {
		
		if(isRelease()) {
			return null;
		}
		
		return process.get(data, key);
		
	}
	
	public int getInt(String key) {
		
		if(isRelease()) {
			return -1;
		}
		
		String str = this.get(key);
			
		return new Integer(str);
	}
	
	public double getDouble(String key) {
		
		if(isRelease()) {
			return -1;
		}
		
		String str = this.get(key);
		
		return Double.valueOf(str);
	}
	
	public ArrayList<String> getList(String key) {
		
		if(isRelease()) {
			return null;
		}
		
		String str = this.get(key);
		
		gdcList list=new gdcList(str);
		
		return list.toArrayList();
	}
	
	
	
	public int getRepeat(String Match) {
		
		if(isRelease()) {
			return -1;
		}
		
		return process.getRepeat(data, Match);
		
	}
	
	
	
}
