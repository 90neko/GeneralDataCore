package com.ksptooi.gdc.v5.Manager;

import java.io.File;
import java.io.InputStream;

import com.ksptooi.gdc.File.Process.FileProcess;
import com.ksptooi.gdc.Main.DataCore;


public class IOController_V5 {

	private File Target=null;
	
	private String SeparationSymbol="=";
	
	private FileProcess fileProcess=null;
	
	
	//���췽��
	public IOController_V5(){
		
		fileProcess=new FileProcess();
		
	}
	
	
	public File getTarget() {
		return Target;
	}

	public void setTarget(File target) {
		Target = target;
	}

	
	public String test(){
		DataCore.LogManager.logInfo("�汾:"+DataCore.gdc_Version);
		return DataCore.gdc_Version;
	}
	
	
	
	//��ȡ�ļ���Keyֵ������
	public String getKeyValue(String Key){
		
		return fileProcess.getKeyValueProcess(Target, Key,SeparationSymbol);
		
	}
	
	//�����ļ���Keyֵ������
	public void setKeyValue(String Key,String Value){
		
		fileProcess.setKeyValueProcess(Target, Key, Value,SeparationSymbol);
		
	}
	
	
	//����д��
	public void setFileContent(String Content){
		
		fileProcess.setFileContentProcess(Target, Content);
		
	}
	
	
	//�����ļ����ж�������Match��ͬ���ַ���
	public int getRepeatLineCount(String Match){
		
		return fileProcess.getRepeatLineCountProcess(Target, Match);
		
	}
	
	//��ȡ�ļ��е���������
	public String getFileContent(){
		
		return fileProcess.getFileContentProcess(Target);
		
	}
	
	//���ļ������һ������
	public void addLine(String Content){
		
		fileProcess.addLineProcess(Target, Content);
	}
	
	//����һ���µ�gdc�ļ�
	public boolean createNewGdcFile(File File){
		
		return fileProcess.createNewGdcFileProcess(File);
		
	}
	
	
	//��ȡ�ļ���Keyֵ������ ͨ��InputStream
	public String getKeyValueOfInputStream(InputStream is,String Key){
		
		return fileProcess.getKeyValueOfInputStreamProcess(is, Key,SeparationSymbol);
		
	}
	
	
	
}
