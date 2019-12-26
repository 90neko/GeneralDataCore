package uk.iksp.v7.session.gdata;

import java.io.File;
import java.util.ArrayList;
import com.ksptooi.v3.Entity.GeneralDataListEntity;
import com.ksptooi.v3.Entity.KeyList;

import uk.iksp.v7.session.common.AbstractGDataSession;
import uk.iksp.v7.session.factory.DataSessionFactory;

public class GDataSession extends AbstractGDataSession{

	
	private boolean isChange=false;
	
	
	//����Դ - �ļ�
	private File dataSources=null;
	
	

	/**���÷���*/	
	 
	
	//����
	public GDataSession(DataSessionFactory fromFactory){
		
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
	public synchronized void assign(DataSessionFactory df,File dataSources) {
		
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
		
		dataCache = io.getGeneralDataEntity(this.dataSources);
		
	}
	
	
	//�ͷ�
	public synchronized void release(){
		
		
		if(isChange) {
			io.updateGeneralDataEntity(dataSources, dataCache);
		}	
		
		dataCache = null;
		dataSources = null;
		
		this.isRelease=true;
		this.isChange=false;
//		System.out.println(fromFactory.getListDataSession().size());
		fromFactory.getListDataSession().add(this);
		
	}
	
	
	
	@Override
	public void close() {
		this.release();	
	}
	
	
	/**���÷��� -����*/	
	
	
	
	

			

	
	/**
	 * 
	 * ����
	 *
	 **/
	
	//����µ�Key ��Value
	public void put(String key,String value) {	
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.dataCache.put(key, value);
	
	}
	
	public void put(String key,ArrayList<String> value) {	
		GeneralDataListEntity list=new GeneralDataListEntity(value);	
		this.put(key, list.toString());
	}
		
	public void put(String key,double value) {	
		this.put(key, String.valueOf(value));	
	}
	
	public void put(String key,int value) {
		this.put(key, String.valueOf(value));
	}
	
	public void put(String key,boolean value) {
		this.put(key, String.valueOf(value));			
	}
	
	public void put(String key,float value) {
		this.put(key, String.valueOf(value));			
	}
	
	
	
	public void addline(String value) {
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.dataCache.addline(value);	
	}
	
	
	
	
	/**
	 * 
	 * �޸�
	 *
	 **/
	
	public void set(String key,String value) {
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.dataCache.set(key, value);
		
	}
	
	public void set(String key,int value) {		
		this.set(key, String.valueOf(value));	
	}
	
	public void set(String key,double value) {
		this.set(key, String.valueOf(value));	
	}
	
	public void set(String key,ArrayList<String> value) {
		GeneralDataListEntity list = new GeneralDataListEntity(value);
		this.set(key, list.toString());	
	}
	
	public void set(String key,boolean value) {
		this.set(key, String.valueOf(value));	
	}
	
	public void set(String key,float value) {
		this.set(key, String.valueOf(value));	
	}
	
	//�޸����б�
	public void set(KeyList keyList){
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.dataCache.setKeyList(keyList);
	}
	
	
	
	/**
	 * 
	 * ɾ��
	 *
	 **/
	public void remove(String key) {
		
		if(isRelease()) {
			return;
		}
		
		isChange=true;
		
		this.dataCache.remove(key);
	}
	

	
	
	
	
	/**
	 * 
	 * ��ѯ
	 *
	 **/
	
	//��ѯ
	public String get(String key) {
		
		
		if(isRelease()) {
			return null;
		}
		
		return this.dataCache.get(key);	
		
	}
	
	public int getInt(String key) {
		
		String str = this.get(key);			
		return new Integer(str);
	}
	
	public double getDouble(String key) {
		String str = this.get(key);
		return Double.valueOf(str);
	}
	
	public ArrayList<String> getList(String key) {
				
		String str = this.get(key);		
		GeneralDataListEntity list=new GeneralDataListEntity(str);		
		return list.toArrayList();
	}
	
	
	public Boolean getBoolean(String key) {
		
		String str = this.get(key);		
		return Boolean.valueOf(str);
	}
	
	public Float getFloat(String key) {
		
		String str = this.get(key);		
		return Float.valueOf(str);
	}
	
	public int getRepeat(String Match) {
		
		if(isRelease()) {
			return -1;
		}
		return this.dataCache.getRepeat(Match);	
	}
	
	
	public ArrayList<String> getLine(){
		
		if(isRelease()) {
			return null;
		}
		
		ArrayList<String> al=new ArrayList<String>();
		
		
		//ѭ��ȡ��data�е�����
		

		while(this.dataCache.next()){
			
			al.add(this.dataCache.get());
			
		}
		
		
		return al;
		
	}
	
	
	//ֱ�ӻ�ȡ����������
	public ArrayList<String> getAll(){
		
		ArrayList<String> als=new ArrayList<String>();
		
		
		while(dataCache.next()) {
			
			als.add(dataCache.get());
			
		}
		
		
		return als;
		
	}
	
	//��ȡ���б�
	public KeyList getKeyList(String key){
		
		return this.dataCache.getKeyList(key);	
		
	}

	
	
	
	
}
