package uk.iksp.v7.session.common;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.ksptooi.v3.Entity.GeneralDataEntity;
import com.ksptooi.v3.Entity.GeneralDataListEntity;
import com.ksptooi.v3.Entity.KeyList;

import uk.iksp.v7.service.gdata.GeneralDataSourceIO;
import uk.iksp.v7.session.factory.DataSessionFactory;

public abstract class AbstractGDataSession implements AutoCloseable{
	
	
	
	//���ݻ���
	public GeneralDataEntity dataCache=null;
	
	//IO
	public GeneralDataSourceIO io= new GeneralDataSourceIO();
	
	
	//FromFactory
	protected DataSessionFactory fromFactory=null;
	
	
	//�Ƿ��ѱ��ͷ�
	protected boolean isRelease=true;
	
	
	
	
	//����
	public abstract void assign(DataSessionFactory df,File dataSources);
	
	
	//����
	public void assign(DataSessionFactory df,InputStream dataSources){
		
	}
	
	//�ͷ�
	public abstract void release();
	
	
	//�ж��Ƿ��ѷ���
	protected boolean isRelease() {
		
		if(this.isRelease) {
			throw new RuntimeException("��Session�ѱ��ͷ�.");	
		}
		
		return false;
	}
	
	
	
	
	
	/**
	 * ���ݲ�������
	 */
	
	
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
