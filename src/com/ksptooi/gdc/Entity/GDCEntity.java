package com.ksptooi.gdc.Entity;

import java.util.ArrayList;

public class GDCEntity {

	
	int index=0;
	
	ArrayList<String> Content = null;
	
	//���췽��
	public GDCEntity(ArrayList<String> al){
		
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
	
}
