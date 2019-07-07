package com.ksptooi.gdc.v6.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import com.ksptooi.gdc.v5.Manager.IOController_V5;
import com.ksptooi.gdc.v6.FileProcess.FileProcess;
import com.ksptooi.gdc.v6.FileProcess.dataSession;
import com.ksptooi.gdc.v6.Mysql.SqlSession;

public class DataFactory extends IOController_V5{
	
	
	FileProcess FP=null;
	File Target=null;
	
	String SeparationSymbol="=";
	
	private LinkedList<dataSession> listDataSession = new LinkedList<dataSession>();
	
	private int poolInitSize=16;
	
	public DataFactory() {
		this.FP = new FileProcess();
	}
	
	
	/**
	 * ����һ��Ŀ���ļ�,��֮�����е��ļ����ʶ�����������Ŀ���ļ�.
	 * 
	 * @param f Ŀ���ļ�ʵ��
	 */
	public void setTarget(File f) {		
		super.setTarget(f);
		this.Target = f;
	}
	
	public File getTarget() {
		return Target;
	}
	
	
	/**
	 * ���ڴ���һ���µ�GD�ļ�.
	 * 
	 * @return ���ļ�����ʧ��ʱ�᷵��False,��֮ΪTrue 
	 * @param f �ļ�ʵ��
	 */
	public boolean createGdc(File f){
		
		return FP.createNewGdcFileProcess(f);
		
	}
	
	/**
	 * ��һ��GD�ļ��м���һ���µ�����,���������õ�Target.
	 * ������ʹ�ô˷���,Ӧ��ʹ��put�����key
	 * @param Line ��Ҫ��ӵ�����
	 * @deprecated
	 */
	public void addLine(String Line){
		
		FP.addLineProcess(Target, Line);
		
	}
	
	
	/**
	 * ��ȡһ��GDC�ļ��е���������
	 * @return ����
	 */
	public String getAll(){
		return FP.getFileContentProcess(Target);
	}
	
	
	/**
	 * ��ȡһ��GDC�ļ���������֮��ƥ�����ݵ�����
	 * @return Match Ҫƥ�������
	 */
	public int getRepeatLineCount(String Match) {
		return FP.getRepeatLineCountProcess(Target, Match);
	}
	
	/**
	 * ����д����GD�ļ�
	 * @param Content Ҫ��д������
	 */
	public void setContent(String Content) {
		FP.setFileContentProcess(Target, Content);
	}
	
	/**
	 * ����GD�ļ���ָ��Key��ֵ
	 * @param key Ҫ���ҵ�key
	 * @param value Ҫ���õ�ֵ
	 */
	public void setKey(String key,String value){
		
		FP.setKeyValueProcess(Target, key, value, this.SeparationSymbol);
		
	}
	
	/**
	 * ����GD�ļ���ָ��Key��ֵ
	 * @param key Ҫ���ҵ�key
	 * @param value Ҫ���õ�ֵ
	 */
	public void setKey(String key,ArrayList<String> value){
		
		FP.setKeyValueProcess(Target, key, value, this.SeparationSymbol);
		
	}
	
	
	/**
	 * ��ȡGD�ļ���ָ��Key��ֵ
	 * @param key Ҫ���ҵ�key
	 * @return ��ǰkey��ֵ
	 */
	public String getKey(String key){
		
		return FP.getKeyValueProcess(Target, key, this.SeparationSymbol);
		
	}
	
	/**
	 * ��ȡGD�ļ���ָ��Key��ֵ int����
	 * @param key Ҫ���ҵ�key
	 * @return ��ǰkey��ֵ
	 */
	public int getKeyForInt(String key){
		
		return FP.getKeyValueForIntProcess(Target, key, this.SeparationSymbol);
		
	}
	
	/**
	 * ��ȡGD�ļ���ָ��Key��ֵ double����
	 * @param key Ҫ���ҵ�key
	 * @return ��ǰkey��ֵ
	 */
	public double getKeyForDouble(String key){
		
		return FP.getKeyValueForDoubleProcess(Target, key, this.SeparationSymbol);
		
	}
	
	/**
	 * ���һ���µ�key��ֵ��GD�ļ�
	 * @param key Ҫ��ӵ�key
	 * @param value key��ֵ
	 */
	public void put(String key,String value){
		
		FP.putProcess(Target, key, value, this.SeparationSymbol);
		
	}
	
	/**
	 * ���һ���µ�key��ֵ��GD�ļ�
	 * @param key Ҫ��ӵ�key
	 * @param value key��ֵ
	 */
	public void put(String key,ArrayList<String> value){
		
		FP.putProcess(Target, key, value, this.SeparationSymbol);
		
	}
	
	
	/**
	 * ��GD�ļ�ɾ��һ��key
	 * @param key Ҫɾ����key
	 */
	public void remove(String key){
		
		FP.removeProcess(Target, key);
		
	}
	
	/**
	 * ��ȡGD�ļ���ָ��Key�µ���
	 * @param key Ҫ���ҵ�key
	 * @return ArrayList
	 */
	public ArrayList<String> getListFromKey(String key){
		
		return FP.getListFromKeyProcess(Target, key, SeparationSymbol);
		
	}
	
	
	
}
