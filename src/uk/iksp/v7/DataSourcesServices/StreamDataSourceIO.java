package uk.iksp.v7.DataSourcesServices;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.ksptooi.v3.Entity.GeneralDataEntity;
import uk.isp.v7.main.DataCore;

public class StreamDataSourceIO {
	
	
	//������ȡGDCʵ��
	public GeneralDataEntity readAsGeneralDataEntity(InputStream is,String charSet){
		
		ArrayList<String> List=new ArrayList<String>();
		
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(is,charSet));
			
			String line=null;
			
			while((line=br.readLine()) != null){
				
				List.add(line);
				
			}
			br.close();
			
			return new GeneralDataEntity(List);
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("��ȡ������ʱ����! at readAsGeneralDataEntity");
		}
		
		return new GeneralDataEntity(List);
	}
	
	//������ȡGDCʵ�� Ԥ����� UTF-8
	public GeneralDataEntity readAsGeneralDataEntity(InputStream is){		
		return this.readAsGeneralDataEntity(is, "UTF-8");		
	}
	
	
	
	//����д��GDCʵ��
	public void writeGeneralDataEntity(OutputStream os,GeneralDataEntity entity){
		
		//��ȡ��ӡ��
		PrintWriter pw=new PrintWriter(os);
		
		//���ù��
		entity.reset();
		
		try{
			
			String content="";
			
			content = entity.getFirst();
			
			while(entity.next()){
				
				content=content+"\r\n"+entity.get();
				
			}
			
			pw.println(content);				
			pw.flush();
			pw.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("д�������ʱ����! at writeGeneralDataEntity");
		}	
		
	}
	
	
	
	
	//����д���ļ�
	public void writeFile(OutputStream os,String str){
		
		
		try{
			
			PrintWriter pw=new PrintWriter(os);	
			
			pw.println(str);		
			
			pw.flush();
			pw.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("д�������ʱ����! at writeFile");
		}	
		
		
	}
	
	
	
	
	
	
	

}