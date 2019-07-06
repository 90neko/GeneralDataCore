package com.ksptooi.gdc.v5.FileDAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.ksptooi.gdc.Main.DataCore;


public class FileDAL_Input {

	
	
	//��Key��ȡ�ļ��е�Value
	/**
	 * @param File �ļ�ʵ��
	 * @param Key Key
	 * @param SeparationSymbol �ָ��
	 * @return ����Key��Ӧ��Value
	 */
	public String getFileKeyLine(File File,String Key,String SeparationSymbol){
		
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(File),"UTF-8"));
			String line=null;
			
			
			while((line=br.readLine()) != null){
				
				if(line.contains(Key+SeparationSymbol)){
					br.close();
					return line;
				}
				
			}
			
			br.close();
			return null;
					
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("�ļ�ϵͳ���� at getFileKey");
		}
		
		
		return null;
			
	}
	
	


	//��ȡ�ļ��������ַ�
	public String getFileContent(File File){
			
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(File),"UTF-8"));
			
			String[] line={null," "};
			
			while((line[0]=br.readLine()) != null){
				line[1]=line[1]+"\r\n"+line[0];
				
			}
			br.close();
			
			return line[1].trim();
				
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("�ļ�ϵͳ���� at getFileContent");
			
		}
			
		
		return null;
		
	}
	
	//��ȡ�ļ��������ַ�(����һ������)
	public ArrayList<String> getFileContentList(File File){
			
		ArrayList<String> List=new ArrayList<String>();
		
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(File),"UTF-8"));
			
			String line=null;
			
			while((line=br.readLine()) != null){
				
				List.add(line);
				
			}
			br.close();
			
			return List;
				
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("�ļ�ϵͳ���� at getFileContent");
		}
			
		
		return null;
		
	}




	//��ȡ�ļ���ָ���� ͨ�� InputStream
	public String getFileKeyLineOfInputStream(InputStream is, String key, String separationSymbol) {
		
		
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String line=null;
			
			
			while((line=br.readLine()) != null){
				
				if(line.contains(key+separationSymbol)){
					br.close();
					return line;
				}
				
			}
			
			br.close();
			return null;
					
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("�ļ�ϵͳ���� at getFileKey");
		}
		
		
		return null;
		
	}
	
	
	
	
	
}
