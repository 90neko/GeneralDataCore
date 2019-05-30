package com.ksptooi.gdc.FileAPI;

import java.io.File;
import java.io.InputStream;

import com.ksptooi.gdc.FileBLL.FileControllerBLL;
import com.ksptooi.gdc.Util.Var;


public class IOController_V5 {

	private File Target=null;
	
	private String SeparationSymbol="=";
	
	private FileControllerBLL fileControllerBLL=null;
	
	
	//���췽��
	public IOController_V5(){
		
		fileControllerBLL=new FileControllerBLL();
		
	}
	
	
	public File getTarget() {
		return Target;
	}

	public void setTarget(File target) {
		Target = target;
	}

	
	public String test(){
		Var.LogManager.writeLogOfINFO("�汾:"+Var.gdc_Version);
		return Var.gdc_Version;
	}
	
	
	
	//��ȡ�ļ���Keyֵ������
	public String getKeyValue(String Key){
		
		return fileControllerBLL.getKeyValue_FileBLL(Target, Key,SeparationSymbol);
		
	}
	
	
	
	//�����ļ���Keyֵ������
	public void setKeyValue(String Key,String Value){
		
		fileControllerBLL.setKeyValue_FileBLL(Target, Key, Value,SeparationSymbol);
		
	}
	
	
	//����д��
	public void setFileContent(String Content){
		
		fileControllerBLL.setFileContent_FileBLL(Target, Content);
		
	}
	
	
	//�����ļ����ж�������Match��ͬ���ַ���
	public int getRepeatLineCount(String Match){
		
		return fileControllerBLL.getRepeatLineCount_FileBLL(Target, Match);
		
	}
	
	//��ȡ�ļ��е���������
	public String getFileContent(){
		
		return fileControllerBLL.getFileContent_FileBLL(Target);
		
	}
	
	//���ļ������һ������
	public void addLine(String Content){
		
		fileControllerBLL.addLine_FileBLL(Target, Content);
	}
	
	//����һ���µ�gdc�ļ�
	public boolean createNewGdcFile(File File){
		
		return fileControllerBLL.createNewGdcFile_BLL(File);
		
	}
	
	
	
	//��ȡ�ļ���Keyֵ������ ͨ��InputStream
	public String getKeyValueOfInputStream(InputStream is,String Key){
		
		return fileControllerBLL.getKeyValueOfInputStream_FileBLL(is, Key,SeparationSymbol);
		
	}
	
	
	
}
