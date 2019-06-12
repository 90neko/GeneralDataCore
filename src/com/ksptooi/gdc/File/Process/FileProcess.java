package com.ksptooi.gdc.File.Process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import com.ksptooi.gdc.FileDAL.FileDAL_Input;
import com.ksptooi.gdc.FileDAL.FileDAL_OutPut;
import com.ksptooi.gdc.Main.DataCore;

public class FileProcess{

	FileDAL_Input fileDAL_Input=null;
	FileDAL_OutPut fileDAL_OutPut=null;
	
	public FileProcess(){
		fileDAL_Input=new FileDAL_Input();
		fileDAL_OutPut=new FileDAL_OutPut();
	}
	
	
	//��ȡ�ļ���Keyֵ������
	public String getKeyValueProcess(File File,String Key,String SeparationSymbol){
	
		String result=null;
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			return "File Is Null";
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			return "File not found";
		}
		
		
		result=fileDAL_Input.getFileKeyLine(File, Key,SeparationSymbol);
		
		
		if(result == null){
			return null;
		}
		
		return result.replace(Key+SeparationSymbol, "");
		
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
		
		
		fileDAL_OutPut.modifyKeyValue(File, Key, Value,SeparationSymbol);
		
		
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
			DataCore.LogManager.sendError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
			return;
		}
		
		fileDAL_OutPut.writeToFile(File, Content);
		
	}
	
	
	//�����ļ����ж�������Match��ͬ���ַ���
	public int getRepeatLineCountProcess(File File,String Match){
		
		ArrayList<String> List=null;
		
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
		
		
		List = fileDAL_Input.getFileContentList(File);
		
		int count=0;
		
		
		for(String str:List){
			
			if(str.equals(Match)){
				count++;
			}
			
		}
		
	
		return count;

	}
	
	//��ȡ�ļ��е���������
	public String getFileContentProcess(File File){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			return "File Is Null";
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			return "File not found";
		}
		
		
		return fileDAL_Input.getFileContent(File);
		
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
		
		
		fileDAL_OutPut.addToFile(File,  "\r\n" +Content + "\r\n");
		
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
		
		
		result=fileDAL_Input.getFileKeyLineOfInputStream(is, key,separationSymbol);
		
		
		if(result == null){
			return null;
		}
		
		return result.replace(key+separationSymbol, "");
		
		
	}
	
	

	
	
	
	
	
}
