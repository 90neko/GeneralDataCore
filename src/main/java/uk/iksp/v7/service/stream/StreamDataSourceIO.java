package uk.iksp.v7.service.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.ksptooi.v3.Entity.GeneralDataEntity;

import uk.iksp.v7.main.DataCore_Leagacy;

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
			DataCore_Leagacy.LogManager.logError("��ȡ������ʱ����! at readAsGeneralDataEntity");
		}
		
		return new GeneralDataEntity(List);
	}
	
	//������ȡGDCʵ�� Ԥ����� UTF-8
	public GeneralDataEntity readAsGeneralDataEntity(InputStream is){		
		return this.readAsGeneralDataEntity(is, "UTF-8");		
	}
	
	
	
	//����д��GDCʵ��
	public void writeGeneralDataEntity(File f,GeneralDataEntity entity){
		

		
		//���ù��
		entity.reset();
		
		try{
			
			//��ȡ��ӡ��
			PrintWriter pw=new PrintWriter(f,"UTF-8");
			
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
			DataCore_Leagacy.LogManager.logError("д�������ʱ����! at writeGeneralDataEntity");
		}	
		
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
			DataCore_Leagacy.LogManager.logError("д�������ʱ����! at writeGeneralDataEntity");
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
			DataCore_Leagacy.LogManager.logError("д�������ʱ����! at writeFile");
		}	
		
		
	}
	
	//����д���ļ�
	public void writeFile(File os,String str){
		
		
		try{
			
			PrintWriter pw=new PrintWriter(os,"UTF-8");	
			
			pw.println(str);		
			
			pw.flush();
			pw.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore_Leagacy.LogManager.logError("д�������ʱ����! at writeFile");
		}	
		
		
	}
	
	
	
	
	
	
	

}
