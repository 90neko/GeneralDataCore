package com.ksptooi.v3.Entity;

import java.util.ArrayList;

import uk.iksp.v7.main.DataCore;

public class GeneralDataEntity {

	
	int index=0;
	
	ArrayList<String> Content = null;
	
	//���췽��
	public GeneralDataEntity(ArrayList<String> al){
		
		this.Content=al;
		
	}
	
	//��һ�����
	public boolean next(){

		
		if(index < Content.size()){
			index++;
			return true;
		}
		
		return false;
		
	}
	
	//ɾ����ǰ
	public void remove() {
		
		Content.remove(index -1);
		index = index -1;
		
	}
	
	
	//��ȡ��ǰ(����)
	public String get(){
			
		return Content.get(index-1);
		
	}
	
	//���õ�ǰ(����)
	public void set(String str){
		Content.set(index-1, str);
	}
	
	
	
	//ȡ��һ������
	public String getFirst(){
		index++;
		return Content.get(0);
	}
	
	
	//��ӵ�ĩβ
	public void addLast(String str){
		Content.add(str);
	}
	
	public void reset(){
		index = 0;
	}
	
	//�ж������Ƿ�Ϊ��
	public boolean isNull(){
		
		if(Content.size()<1){
			return true;
		}
		
		return false;
		
	}
	
	
	//���ݲ���
	
	
	//����µ�������
	public void addline(String value) {	
		this.addLast(value);
	}
	
	
	//����µ�key
	public void put(String key,String value) {			
		this.addLast(key+"="+value);		
	}
	
	
	//�޸�value
	public void set(String key,String value) {
			
		while(this.next()){
			
			if(this.get().contains(key+"=")){
				
				
				this.set(key+"="+value);
				
			}
				
		}
		
		this.reset();	
		
	}
	
	
	//ɾ��
	public void remove(String key) {
		
		while(this.next()){
			
			if(this.get().contains(key+"=")){
				
				this.remove();
				
			}
				
		}
		
		this.reset();
	}
	
	//��ѯ
	public String get(String key) {
		
		while(this.next()){
			
			if(this.get().contains(key+"=")){
				
				String str = this.get().replace(key+"=", "");
				
				this.reset();
				
				return str;
				
			}
				
		}
		
		DataCore.logManager.logWarning("�ļ�ϵͳ����! δ�ҵ�Key - " + key);
		
		return null;
		
	}
	
	//����ʵ�����ж�������Match��ͬ���ַ���
	public int getRepeat(String Match){
		
		int count=0;
				
		while(this.next()){
					
			if(this.get().equals(Match)){
				count++;
			}	
			
		}
	
		return count;

	}
	
	
	
	//��ѯKeyList
	public KeyList getKeyList(String key) {
		
		ArrayList<String> al=new ArrayList<String>();
		
		
		while(this.next()){
			
			if(this.get().contains(key+"=")){
				
				String str = this.get().replace(key+"=", "");
						
				al.add(str);	
			}
				
		}
		
		this.reset();
		
		if(al.size()==0){
			DataCore.logManager.logWarning("�ļ�ϵͳ����! δ�ҵ�Key - " + key);
			return null;
		}
		
		
		//����KeyList
		
		KeyList kl=new KeyList(al,key);
		return kl;	
	}
	
	
	//����KeyList
	public void setKeyList(KeyList keyList){
		
		
		String key=keyList.getKey();
		
		int index = 0;
		
		while((this.next() && (index<keyList.getSize()))){
			
			if(this.get().contains(key+"=")){
				
				this.set(key+"="+keyList.index(index));
				index++;
				
			}
				
		}
		
		this.reset();	
		
	}
	
	
}
