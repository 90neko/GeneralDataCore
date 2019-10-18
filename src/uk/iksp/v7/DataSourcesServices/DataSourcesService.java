package uk.iksp.v7.DataSourcesServices;

import com.ksptooi.v3.Entity.GeneralDataEntity;

import uk.isp.v7.main.DataCore;

public class DataSourcesService {

	
	//����µ�������
	public GeneralDataEntity addline(GeneralDataEntity data,String value) {
				
		data.addLast(value);
		
		return data;		
	}
	
	
	//����µ�key
	public GeneralDataEntity put(GeneralDataEntity data,String key,String value) {
				
		data.addLast(key+"="+value);
		
		return data;		
	}
	
	//�޸�value
	public GeneralDataEntity set(GeneralDataEntity data,String key,String value) {
		
		
		while(data.next()){
			
			if(data.get().contains(key+"=")){
				
				
				data.set(key+"="+value);
				
			}
				
		}
		
		data.reset();
		
		return data;
		
	}
	
	
	//ɾ��
	public GeneralDataEntity remove(GeneralDataEntity data,String key) {
		
		
		while(data.next()){
			
			if(data.get().contains(key+"=")){
				
				data.remove();
				
			}
				
		}
		
		data.reset();
		
		return data;
		
	}
	
	//��ѯ
	public String get(GeneralDataEntity data,String key) {
		
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
	public int getRepeat(GeneralDataEntity data,String Match){
		
		int count=0;
				
		while(data.next()){
					
			if(data.get().equals(Match)){
				count++;
			}	
			
		}
	
		return count;

	}
	
	
}
