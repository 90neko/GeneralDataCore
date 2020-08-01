package com.ksptooi.generaldatacore.dataInteface;

import java.util.ArrayList;
import com.ksptooi.generaldatacore.entity.data.DataMap;

public interface FileDataInterface {

	/**
	 * �ļ����ݽӿ�
	 */
	
	
	/**
	 * ͨ��ָ����key��ȡƽ���ļ��е�Value
	 * @param key
	 * @return ����value ���û���򷵻ؿ�
	 */
	public String getString(String key);
	
	public Integer getnt(String key);
	
	public boolean getBoolean(String key);
	
	public Float getFloat(String key);
	
	public Double getDouble(String key);
	
	
	
	/**
	 * ͨ��ָ����key��ȡƽ���ļ��еļ���
	 * @param key
	 * @return
	 */
	public ArrayList<String> getStringList(String key);
	
	public ArrayList<Integer> getIntegerList(String key);
	
	public ArrayList<Boolean> getBooleanList(String key);
	
	public ArrayList<Float> getFloatList(String key);
	
	public ArrayList<Double> getDoubleList(String key);
	
	
	/**
	 * ��ƽ���ļ���Ϊmap
	 */
	public DataMap getDataMap();
	
}
