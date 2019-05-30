package com.ksptooi.gdc.FileBLL;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import com.ksptooi.gdc.FileDAL.FileDAL_Input;
import com.ksptooi.gdc.FileDAL.FileDAL_OutPut;
import com.ksptooi.gdc.Util.Var;

public class FileControllerBLL{

	FileDAL_Input fileDAL_Input=null;
	FileDAL_OutPut fileDAL_OutPut=null;
	
	public FileControllerBLL(){
		fileDAL_Input=new FileDAL_Input();
		fileDAL_OutPut=new FileDAL_OutPut();
	}
	
	
	//��ȡ�ļ���Keyֵ������
	public String getKeyValue_FileBLL(File File,String Key,String SeparationSymbol){
	
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
	public void setKeyValue_FileBLL(File File,String Key,String Value,String SeparationSymbol){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - TargetΪnull");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
			return;
		}
		
		
		fileDAL_OutPut.modifyKeyValue(File, Key, Value,SeparationSymbol);
		
		
	}
	
	
	//����д��
	public void setFileContent_FileBLL(File File,String Content){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - TargetΪnull");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
			return;
		}
		
		fileDAL_OutPut.writeToFile(File, Content);
		
	}
	
	
	//�����ļ����ж�������Match��ͬ���ַ���
	public int getRepeatLineCount_FileBLL(File File,String Match){
		
		ArrayList<String> List=null;
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - TargetΪnull");
			return -1;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
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
	public String getFileContent_FileBLL(File File){
		
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
	public void addLine_FileBLL(File File,String Content){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - TargetΪnull");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			Var.LogManager.writeLogOfError("�ļ�ϵͳ���� - �ļ�δ�ҵ�  - setKeyValue_FileBLL");
			return;
		}
		
		
		fileDAL_OutPut.addToFile(File,  "\r\n" +Content + "\r\n");
		
	}
	
	
	
	//����һ���µ�gdc�ļ�
	public boolean createNewGdcFile_BLL(File File){
		
		if(File.exists()){
			return false;
		}
		
		
		try {
				
			File.getParentFile().mkdirs();
			File.createNewFile();
			this.addLine_FileBLL(File, "@LineType=GeneralDataCore");
			this.addLine_FileBLL(File, "@LineVersion=V5");
			this.addLine_FileBLL(File, "@KeySeparationSymbol==");
			this.addLine_FileBLL(File, "#	");
			
		} catch (IOException e) {
			e.printStackTrace();
			Var.LogManager.writeLogOfError("���ء�����δ֪���ļ�ϵͳ����- createNewGdcFile_BLL");
			return false;
		}
		
		return true;
		
	}


	public String getKeyValueOfInputStream_FileBLL(InputStream is, String key, String separationSymbol) {
		
		
		String result=null;		
		
		
		result=fileDAL_Input.getFileKeyLineOfInputStream(is, key,separationSymbol);
		
		
		if(result == null){
			return null;
		}
		
		return result.replace(key+separationSymbol, "");
		
		
	}
	
	

	
	
	
	
	
}
