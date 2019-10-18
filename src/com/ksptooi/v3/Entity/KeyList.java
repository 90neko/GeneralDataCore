package com.ksptooi.v3.Entity;

import java.util.ArrayList;

public class KeyList {

	
	private ArrayList<String> keyList=null;
	
	private String key=null;
	
	public KeyList(ArrayList<String> al,String key){
		this.keyList=al;
		this.key=key;
	}
	
	
	//��ȡԪ�ص�ֵ
	public synchronized String index(int index){	
		return keyList.get(index);		
	}
	
	//�޸�Ԫ��
	public synchronized void set(int index,String str){	
		keyList.set(index, str);
	}
	
	
	
	//��ȡ����
	public int getSize(){
		return keyList.size();
	}
	
	public String getKey(){
		return this.key;
	}
	
	
	
	
	
	
	
}
