package com.ksptooi.gdc.FileDAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.ishiyamasayuri.Entity.GDCEntity;
import com.ksptooi.gdc.Main.DataCore;

public class GeneralFileIO {

	
	
	//���ļ���ȡGDCʵ��
	public GDCEntity getGDCEntity(File File){
		
		ArrayList<String> List=new ArrayList<String>();
		
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(File),"UTF-8"));
			
			String line=null;
			
			while((line=br.readLine()) != null){
				
				List.add(line);
				
			}
			br.close();
			
			return new GDCEntity(List);
				
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.sendError("�ļ�ϵͳ���� at getFileContent");
		}
			
		
		return new GDCEntity(List);
		
	}
	
	
	//дGDCʵ�����ļ�
	public void writeFile(File file,GDCEntity entity){
		
		entity.reset();
		
		try{
			
			PrintWriter pw=new PrintWriter(file,"UTF-8");
						
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
			DataCore.LogManager.sendError("�ļ�ϵͳ���� at writeToFile");
		}	
		
		
	}
	
	
	
	
	
	
}
