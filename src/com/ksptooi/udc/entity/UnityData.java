package com.ksptooi.udc.entity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;

import com.ksptooi.udc.io.UdcWriter;
import com.ksptooi.udc.parser.Parser;

public class UnityData {
	
	private Charset charset = null;
	
	private ArrayList<String> content = null;
	
	private Path path = null;
	
	public UnityData(ArrayList<String> content,Path path,Charset cs) {
		this.content = content;
		this.path = path;
		this.charset = cs;
	}
	

	/**
	 * ��ȡָ����key������λ��
	 * @param key
	 * @return ����λ��,û���ҵ�keyʱ����-1
	 */
	public int getIndex(String key) {
		
		String currentKey = null;
		
		
		for(int i=0;i<content.size();i++) {
			
			//�������ָ�����ֱ������
			if( ! content.get(i).contains("=")) {
				continue;
			}
			
			currentKey = Parser.toKey(content.get(i));
			
			if(currentKey.equals(key)){
				return i;
			}
			
		}
		
		return -1;
	}
	
	
	/**
	 * ��ȡָ��key��value
	 * @param key
	 * @return
	 */
	public String get(String key) {
		
		int index = this.getIndex(key);
		
		if(index == -1) {
			return null;
		}
		
		return Parser.toValue(content.get(index));
		
	}
	
	/**
	 * ��ȡָ��int���͵�value
	 * @param key
	 * @return 
	 * ��keyδ�ҵ�ʱ����-1
	 * ��key�Ѵ��ڵ��޷�ת��Ϊintʱ����-2
	 */
	public int getInt(String key) {
		
		String result = this.get(key);
		
		if(result == null) {
			return -1;
		}
		
		try {
			return Integer.valueOf(result);
		}catch(Exception e) {
			return -2;
		}
		
	}
	
	/**
	 * ��ȡָ��Double���͵�value
	 * @param key
	 * @return 
	 * ��keyδ�ҵ�ʱ����-1
	 * ��key�Ѵ��ڵ��޷�ת��ΪDoubleʱ����-2
	 */
	public double getDouble(String key) {
		
		String result = this.get(key);
		
		if(result == null) {
			return -1;
		}
		
		try {
			return Double.valueOf(result);
		}catch(Exception e) {
			return -2;
		}
		
	}
	
	/**
	 * ��ȡָ��Boolean���͵�value
	 * @param key
	 * @return 
	 * ��keyδ�ҵ�ʱ����false
	 * ��key�Ѵ��ڵ��޷�ת��ΪBooleanʱ����false
	 */
	public boolean getBoolean(String key) {
		
		String result = this.get(key);
		
		if(result == null) {
			return false;
		}
		
		try {
			return Boolean.valueOf(result);
		}catch(Exception e) {
			return false;
		}
		
	}
	
	/**
	 * ����ָ��key��value
	 * @param key
	 * @param value
	 */
	public void set(String key,String value) {
		
		String newLine = key +"="+ value;
		
		int index = this.getIndex(key);
		
		if(index == -1) {
			return;
		}
		
		content.set(index, newLine);
		
	}
	
	public ArrayList<String> getContent(){
		return this.content;
	}
	
	
	/**
	 * ����ͬ���������ļ�
	 * @throws IOException 
	 */
	public void flush() throws IOException {
		UdcWriter.writeUnityData(this);
	}




	public Path getPath() {
		return path;
	}


	public Charset getCharset() {
		return charset;
	}
	
	
	
	
	
	
	
	
	
}
