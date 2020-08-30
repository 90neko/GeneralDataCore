package com.ksptooi.generaldatacore.entity.data;

import java.util.ArrayList;
import com.ksptooi.generaldatacore.common.Type;
import com.ksptooi.generaldatacore.dataInteface.DataConnection;
import com.ksptooi.generaldatacore.parser.KVParser;
import com.ksptooi.generaldatacore.parser.ListParser;

public class DataSet {

	//���ݻ���
	private ArrayList<String> dataStringCache = null;

	//���ݽӿ�
	private DataConnection dataConnection = null;

	//�Զ���ȡ�ļ�
	private boolean automaticRead = false;

	//�Զ�д���ļ�
	private boolean automaticWrite = false;


	public DataSet(DataConnection data, ArrayList<String> al){
		this.dataConnection = data;
		this.dataStringCache = al;
	}
	
	public DataSet(DataConnection data, ArrayList<String> al, boolean isAutoReload, boolean isAutoUpdate){
		this.dataConnection = data;
		this.dataStringCache = al;
		this.automaticRead = isAutoReload;
		this.automaticWrite = isAutoUpdate;
	}


	/**
	 * ���´��ļ���ȡ����
	 */
	public void read() {

		this.dataStringCache = dataConnection.getStringList();
	}
	
	/**
	 * д���ݵ��ļ�
	 */
	public void write() {
		this.dataConnection.setDataMap(this);
	}
	
	
	
	/**
	 * ��ȡ�������ݵ��ַ���
	 */
	public String string() {
		return ListParser.string(this.dataStringCache);
	}
	
	
	/**
	 * �ҵ�key��������
	 */
	public int indexOf(String inputKey) {
		return ListParser.indexOf(inputKey, this.dataStringCache);
	}
	
	
	/**
	 * ����key��ȡvalue
	 * @return 
	 * @return 
	 */
	public String getVal(String inputKey){

		//�Զ���ȡ
		if(automaticRead) {
			this.read();
		}
		
		int line = this.indexOf(inputKey);
		
		if(line == -1) {
			return null;
		}
		
		return KVParser.value(dataStringCache.get(line-1));
	}

	public String val(String inputKey){
		return this.getVal(inputKey);
	}
	
	/**
	 * ����key����value
	 */
	public boolean setVal(String key,String value) {

		//��������
		update("read");

		int line = this.indexOf(key)-1;

		//���û�����������һ��key
		if(line < 1) {
			this.dataStringCache.add(KVParser.format(key,value));
			update("write");
			return false;
		}
		
		String setValue = KVParser.setValue(dataStringCache.get(line), value);
		dataStringCache.set(line, setValue);
		
		
		//�Զ�д��
		update("write");
		
		return true;
	}
	
	
	public boolean setVal(String key,Object object) {
		return this.setVal(key, object.toString());
	}
	public boolean setVal(String key,ArrayList<String> object) {
		return this.setVal(key,ListParser.string(object));
	}

	public boolean val(String key,Object object){
		return this.setVal(key,object);
	}

	
	public Integer getInt(String k) {
		return Type.toInt(this.getVal(k));
	}
	
	public Double getDouble(String k) {
		return Type.toDouble(this.getVal(k));
	}
	
	public Float getFloat(String k) {
		return Type.toFloat(this.getVal(k));
	}
	
	public Boolean getBoolean(String k) {
		return Type.toBoolean(this.getVal(k));
	}


	public DataSet setAutomaticRead(boolean automaticRead) {
		this.automaticRead = automaticRead;
		return this;
	}

	public DataSet setAutomaticWrite(boolean automaticWrite) {
		this.automaticWrite = automaticWrite;
		return this;
	}

	public DataSet setAutomatic(boolean b){
		this.automaticRead = b;
		this.automaticWrite = b;
		return this;
	}

	//��������
	private void update(String type){

		//�ж�����Ϊ�� ���ҿ����Զ���ȡ
		if(type.equalsIgnoreCase("read") && this.automaticRead == true){
			this.read();
			return;
		}

		if(type.equalsIgnoreCase("write") && this.automaticWrite == true){
			this.write();
			return;
		}

		throw new RuntimeException("the type does not match ,correct type is 'read' and 'write' ");
	}

}
