package uk.iksp.v7.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import com.ksptooi.v3.Entity.GeneralDataEntity;
import com.ksptooi.v3.Entity.GeneralDataListEntity;

import uk.iksp.v7.DataSourcesServices.DataSourcesService;
import uk.iksp.v7.DataSourcesServices.FileDataSourceIO;
import uk.iksp.v7.Factory.DataSessionFactory;

public class DataSession implements AutoCloseable {

	//IO
	FileDataSourceIO io= new FileDataSourceIO();
	
	private boolean isChange=false;
	
	//���ݻ���
	GeneralDataEntity data=null;
	
	//����Դ - �ļ�
	private File dataSources=null;
	
	
	//����Դ - ��
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	
	
	//Factory
	private DataSessionFactory fromFactory=null;
	
	
	private boolean isRelease=true;
	
	//Process
	DataSourcesService process=new DataSourcesService();
	
	
	//����
	public DataSession(DataSessionFactory fromFactory){
		
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
		

		
		try {
			
			//��������Դ
			this.dataSources=dataSources;
			
			this.inputStream = new FileInputStream(this.dataSources);
			
			this.outputStream = new FileOutputStream(this.dataSources);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		data = io.getGDCEntity(this.dataSources);
		
	}
	
	
	
	
	
	
	//�ͷ�
	public synchronized void release() {
		
		
		if(isChange) {
			io.writeGDCEntity(dataSources, data);
		}	
		
		data = null;
		dataSources = null;
		
		this.isRelease=true;
		this.isChange=false;
		System.out.println(fromFactory.getListDataSession().size());
		fromFactory.getListDataSession().add(this);
		
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
		
		GeneralDataListEntity list=new GeneralDataListEntity(value);
		
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
	
	public void put(String key,boolean value) {
		if(isRelease()) {
			return;
		}
		
		this.put(key, String.valueOf(value));			
	}
	
	public void put(String key,float value) {
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
		
		GeneralDataListEntity list = new GeneralDataListEntity(value);
		
		this.set(key, list.toString());
		
	}
	
	public void set(String key,boolean value) {
		
		if(isRelease()) {
			return;
		}
		
		this.set(key, String.valueOf(value));
		
	}
	
	public void set(String key,float value) {
		
		if(isRelease()) {
			return;
		}
		
		this.set(key, String.valueOf(value));
		
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
		
		GeneralDataListEntity list=new GeneralDataListEntity(str);
		
		return list.toArrayList();
	}
	
	
	public Boolean getBoolean(String key) {
		
		if(isRelease()) {
			return false;
		}
		
		String str = this.get(key);
		
		return Boolean.valueOf(str);
	}
	
	public Float getFloat(String key) {
		
		if(isRelease()) {
			return -1F;
		}
		
		String str = this.get(key);
		
		return Float.valueOf(str);
	}
	
	public int getRepeat(String Match) {
		
		if(isRelease()) {
			return -1;
		}
		
		return process.getRepeat(data, Match);
		
	}
	
	public ArrayList<String> getLine(){
		
		if(isRelease()) {
			return null;
		}
		
		ArrayList<String> al=new ArrayList<String>();
		
		
		//ѭ��ȡ��data�е�����
		

		while(this.data.next()){
			
			al.add(this.data.get());
			
		}
		
		
		return al;
		
	}
	
	
	//ֱ�ӻ�ȡ����������
	public ArrayList<String> getAll(){
		
		ArrayList<String> als=new ArrayList<String>();
		
		
		while(data.next()) {
			
			als.add(data.get());
			
		}
		
		
		return als;
		
	}

	@Override
	public void close() {
		
		if(isChange) {
			io.writeGDCEntity(dataSources, data);
		}	
		
		data = null;
		dataSources = null;
		
		this.isRelease=true;
		this.isChange=false;
		
		fromFactory.getListDataSession().add(this);
		
	}
	
	
	
}
