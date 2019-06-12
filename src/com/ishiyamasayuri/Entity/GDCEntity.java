package com.ishiyamasayuri.Entity;

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
	
	
	//��ȡ��ǰ
	public String get(){
			
		return Content.get(index-1);
		
	}
	
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
	
	
}
