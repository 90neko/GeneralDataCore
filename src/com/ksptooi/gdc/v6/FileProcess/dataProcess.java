package com.ksptooi.gdc.v6.FileProcess;

import com.ksptooi.gdc.Entity.GDCEntity;
import com.ksptooi.gdc.Main.DataCore;

public class dataProcess {

	
	//����µ�������
	public GDCEntity addline(GDCEntity data,String value) {
				
		data.addLast(value);
		
		return data;		
	}
	
	
	//����µ�key
	public GDCEntity put(GDCEntity data,String key,String value) {
				
		data.addLast(key+"="+value);
		
		return data;		
	}
	
	//�޸�value
	public GDCEntity set(GDCEntity data,String key,String value) {
		
		
		while(data.next()){
			
			if(data.get().contains(key+"=")){
				
				
				data.set(key+"="+value);
				
			}
				
		}
		
		data.reset();
		
		return data;
		
	}
	
	
	//ɾ��
	public GDCEntity remove(GDCEntity data,String key) {
		
		
		while(data.next()){
			
			if(data.get().contains(key+"=")){
				
				data.remove();
				
			}
				
		}
		
		data.reset();
		
		return data;
		
	}
	
	//��ѯ
	public String get(GDCEntity data,String key) {
		
		while(data.next()){
			
			if(data.get().contains(key+"=")){
				
				String str = data.get().replace(key+"=", "");
				
				data.reset();
				
				return str;
				
			}
				
		}
		
		DataCore.LogManager.logWarning("�ļ�ϵͳ����! δ�ҵ�Key - "+key);
		
		return null;
		
	}
	
	
	
	//�����ļ����ж�������Match��ͬ���ַ���
	public int getRepeat(GDCEntity data,String Match){
		
		int count=0;
				
		while(data.next()){
					
			if(data.get().equals(Match)){
				count++;
			}	
			
		}
	
		return count;

	}
	
	
}
