package com.ksptooi.generaldatacore.entity.data;

import java.util.ArrayList;
import com.ksptooi.generaldatacore.common.Type;
import com.ksptooi.generaldatacore.dataInteface.DataInterface;
import com.ksptooi.generaldatacore.parser.KVParser;
import com.ksptooi.generaldatacore.parser.ListParser;

public class DataMap{

	
	private ArrayList<String> datalist = null;
	
	private DataInterface dataInterface = null;
	
	private boolean autoMaticRead = false;
	
	private boolean autoMaticWrite = false;
	
	public DataMap(DataInterface data,ArrayList<String> al){
		this.dataInterface = data;
		this.datalist = al;
	}
	
	
	public DataMap(DataInterface data,ArrayList<String> al,boolean isAutoReload,boolean isAutoUpdate){
		this.dataInterface = data;
		this.datalist = al;
		this.autoMaticRead = isAutoReload;
		this.autoMaticWrite = isAutoUpdate;
	}
	
	
	
	
	/**
	 * ���´��ļ���ȡ����
	 */
	public void read() {
		this.datalist = dataInterface.getList();
	}
	
	/**
	 * д���ݵ��ļ�
	 */
	public void write() {
		this.dataInterface.setDataMap(this);
	}
	
	
	
	/**
	 * ��ȡ�������ݵ��ַ���
	 */
	public String string() {
		return ListParser.string(this.datalist);
	}
	
	
	/**
	 * �ҵ�key��������
	 */
	public int indexOf(String inputKey) {
		return ListParser.indexOf(inputKey, this.datalist);
	}
	
	
	/**
	 * ����key��ȡvalue
	 * @return 
	 * @return 
	 */
	public String getVal(String inputKey){

		//�Զ���ȡ
		if(autoMaticRead) {
			this.read();
		}
		
		int line = this.indexOf(inputKey);
		
		if(line == -1) {
			return null;
		}
		
		return KVParser.value(datalist.get(line-1));	
	}
	
	/**
	 * ����key����value
	 */
	public boolean setVal(String key,String value) {
		
		int line = this.indexOf(key)-1;
		
		if(line == -1) {
			return false;
		}
		
		String setValue = KVParser.setValue(datalist.get(line), value);
		datalist.set(line, setValue);
		
		
		//�Զ�д��
		if(autoMaticWrite) {
			this.write();
		}
		
		return true;	
	}
	
	
	public boolean setVal(String key,Object object) {
		return this.setVal(key, object.toString());
	}
	
	
	public Integer getInt(String k) {		
		String val = this.getVal(k);
		return Type.toInt(val);
	}
	
	public Double getDouble(String k) {
		String val = this.getVal(k);
		return Type.toDouble(val);
	}
	
	public Float getFloat(String k) {
		String val = this.getVal(k);
		return Type.toFloat(val);
	}
	
	public Boolean getBoolean(String k) {
		String val = this.getVal(k);
		return Type.toBoolean(val);
	}




	public void setAutoMaticRead(boolean autoMaticRead) {
		this.autoMaticRead = autoMaticRead;
	}



	public void setAutoMaticWrite(boolean autoMaticWrite) {
		this.autoMaticWrite = autoMaticWrite;
	}
	
	
	

}
