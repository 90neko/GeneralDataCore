package com.ksptooi.gdc.File.Process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.ishiyamasayuri.gdc.Entity.GDCEntity;
import com.ksptooi.gdc.FileDAL.GeneralFileIO;
import com.ksptooi.gdc.Main.DataCore;

public class FileProcess{

	GeneralFileIO GFI=new GeneralFileIO();
	
	public FileProcess(){
		GFI=new GeneralFileIO();
	}
	
	
	//��ȡ�ļ���Keyֵ������
	public String getKeyValueProcess(File File,String Key,String SeparationSymbol){
	
		GDCEntity GDCE= null;
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			return "File Is Null";
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			return "File not found";
		}
		
		//��ȡGDCʵ��
		GDCE = GFI.getGDCEntity(File);
		
		
		while(GDCE.next()){
						
			if(GDCE.get().contains(Key+SeparationSymbol)){
				return GDCE.get().replace(Key+SeparationSymbol, "");
			}		
			
		}
		
		return null;
		
	}
	
	
	//�����ļ���Keyֵ������
	public void setKeyValueProcess(File File,String Key,String Value,String SeparationSymbol){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - TargetΪnull");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
			return;
		}
		
		GDCEntity GDCE= null;
		
		
		//��ȡGDCʵ��
		GDCE = GFI.getGDCEntity(File);
		
		
		while(GDCE.next()){
			
			if(GDCE.get().contains(Key+SeparationSymbol)){
				
				GDCE.set(Key+SeparationSymbol+Value);
				
			}
			
			
		}
		
		GFI.writeFile(File, GDCE);
		
		
	}
	
	
	//����д��
	public void setFileContentProcess(File File,String Content){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - TargetΪnull");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setFileContentProcess");
			return;
		}
		
		
		GFI.writeFile(File, Content);
		
	}
	
	
	//�����ļ����ж�������Match��ͬ���ַ���
	public int getRepeatLineCountProcess(File File,String Match){
		
		GDCEntity GDCE = null;
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - TargetΪnull");
			return -1;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
			return -1;
		}
		
		
		GDCE = GFI.getGDCEntity(File);
		
		int count=0;
		
		
		
		while(GDCE.next()){
					
			if(GDCE.get().equals(Match)){
				count++;
			}	
			
		}
		
	
		return count;

	}
	
	//��ȡ�ļ��е���������
	public String getFileContentProcess(File File){
		
		GDCEntity GDCE = null;
		String Result="";
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			return "File Is Null";
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			return "File not found";
		}
		
		GDCE=GFI.getGDCEntity(File);
		
		
		Result = GDCE.getFirst();
		
		while(GDCE.next()){
			Result=Result + "\r\n" +GDCE.get();
		}
		
		
		return Result;
		
	}
	
	//���ļ������һ������
	public void addLineProcess(File File,String Content){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - TargetΪnull");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
			return;
		}
		
		GDCEntity GDCE = null;
		
		GDCE=GFI.getGDCEntity(File);
		
		GDCE.addLast(Content);
		
		GDCE.reset();
		
		while(GDCE.next()){
			System.out.println("||"+GDCE.get());
		}
		
		
		GFI.writeFile(File, GDCE);
		
	}
	
	
	
	//����һ���µ�gdc�ļ�
	public boolean createNewGdcFileProcess(File File){
		
		if(File.exists()){
			return false;
		}
		
		
		try {
				
			File.getParentFile().mkdirs();
			File.createNewFile();
			this.addLineProcess(File, "@LineType=GeneralDataCore");
			this.addLineProcess(File, "@LineVersion=V5");
			this.addLineProcess(File, "@KeySeparationSymbol==");
			this.addLineProcess(File, "#	");
			
		} catch (IOException e) {
			e.printStackTrace();
			DataCore.LogManager.sendError("���ء�����δ֪���ļ�ϵͳ����- createNewGdcFile_BLL");
			return false;
		}
		
		return true;
		
	}


	public String getKeyValueOfInputStreamProcess(InputStream is, String key, String separationSymbol) {
		
		
		String result=null;		
		
		
		result=GFI.getFileKeyLineOfInputStream(is, key,separationSymbol);
		
		
		if(result == null){
			return null;
		}
		
		return result.replace(key+separationSymbol, "");
		
		
	}
	
	

	
	
	
	
	
}
