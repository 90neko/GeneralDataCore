package com.ksptooi.gdc.v6.FileProcess;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import com.ksptooi.gdc.Entity.GDCEntity;
import com.ksptooi.gdc.Main.DataCore;
import com.ksptooi.gdc.Main.gdcList;
import com.ksptooi.gdc.v6.FileIO.GeneralFileIO;

public class FileProcess{

	GeneralFileIO GFI=new GeneralFileIO();
	
	public FileProcess(){
		GFI=new GeneralFileIO();
	}
	
	
	
	//����µ�List Key
	public void putProcess(File Target,String key,ArrayList<String> value,String SeparationSymbol) {
		
		
		gdcList list=new gdcList(value);
		
		this.putProcess(Target, key, list.toString(), SeparationSymbol);
		
	}
	
	
	//��ȡָ��key�µ�list
	public ArrayList<String> getListFromKeyProcess(File file,String key,String SeparationSymbol) {
		
		String keyValue = this.getKeyValueProcess(file, key, SeparationSymbol);
		
		
		try {
			
		
			gdcList list=new gdcList(keyValue);
			
			return list.toArrayList();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			
		
	}
	
	
	
	//ɾ��һ��key
	public void removeProcess(File Target,String key) {
		
		//�ж��ļ��Ƿ�Ϊ��
		if(Target == null){
			DataCore.LogManager.logError("�ļ�ϵͳ����:û������Target");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! Target.exists()){
			DataCore.LogManager.logError("�ļ�ϵͳ����:�ļ�δ�ҵ�:putProcess");
			return;
		}
		
		GDCEntity GDCE=GFI.getGDCEntity(Target);
		
		while(GDCE.next()){
			
			
			if(GDCE.get().split("=")[0].equals(key)){
				GDCE.remove();
				break;
			}		
			
		}
		
		GFI.writeGDCEntity(Target, GDCE);
		
		
	}
	
	
	
	
	//����µ�key
	public void putProcess(File Target,String key,String value,String SeparationSymbol) {
		
		//�ж��ļ��Ƿ�Ϊ��
		if(Target == null){
			DataCore.LogManager.logError("�ļ�ϵͳ����:û������Target");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! Target.exists()){
			DataCore.LogManager.logError("�ļ�ϵͳ����:�ļ�δ�ҵ�:putProcess");
			return;
		}
		
		
		GDCEntity GDCE=GFI.getGDCEntity(Target);
		
		GDCE.addLast(key+SeparationSymbol+value);
		
		GFI.writeGDCEntity(Target, GDCE);
		
	}
	
	
	
	
	//��ȡ�ļ���Keyֵ������ ת��ΪDouble
	public double getKeyValueForDoubleProcess(File File,String Key,String SeparationSymbol) {
		
		
		String KV = this.getKeyValueProcess(File, Key, SeparationSymbol);
		
		try {
			
			double Double_kv = Double.valueOf(KV);
			return Double_kv;	
			
		}catch(Exception e) {
			DataCore.LogManager.logError("�ļ�ϵͳ����:key:"+KV+" �޷�ת��Ϊint");
			return -2147483647;
		}
		
		
	}
	
	
	//��ȡ�ļ���Keyֵ������ ת��Ϊint
	public int getKeyValueForIntProcess(File File,String Key,String SeparationSymbol) {
		
		
		String KV = this.getKeyValueProcess(File, Key, SeparationSymbol);
		
		try {
			
			int int_kv = new Integer(KV);
			return int_kv;	
			
		}catch(Exception e) {
			DataCore.LogManager.logError("�ļ�ϵͳ����:key:"+KV+" �޷�ת��Ϊint");
			return -2147483647;
		}
		
		
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
	
	
	
	//�����ļ���Keyֵ������(����)
	public void setKeyValueProcess(File File,String Key,ArrayList<String> Value,String SeparationSymbol){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.logError("�ļ�ϵͳ����:û������Target");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.logError("�ļ�ϵͳ����:�ļ�δ�ҵ�:setKeyValueProcess");
			return;
		}
		
		//��ArrayList����ΪGDCList
		gdcList list=new gdcList(Value);
		
		
		GDCEntity GDCE= null;
		
		
		//��ȡGDCʵ��
		GDCE = GFI.getGDCEntity(File);
		
		
		while(GDCE.next()){
			
			if(GDCE.get().contains(Key+SeparationSymbol)){
				
				GDCE.set(Key+SeparationSymbol+list.toString());
				
			}
			
			
		}
		
		GFI.writeGDCEntity(File, GDCE);
		
		
	}
	
	
	//�����ļ���Keyֵ������
	public void setKeyValueProcess(File File,String Key,String Value,String SeparationSymbol){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.logError("�ļ�ϵͳ����:û������Target");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.logError("�ļ�ϵͳ����:�ļ�δ�ҵ�:setKeyValueProcess");
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
		
		GFI.writeGDCEntity(File, GDCE);
		
		
	}
	
	
	//����д��
	public void setFileContentProcess(File File,String Content){
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.logError("�ļ�ϵͳ����:û������Target");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.logError("�ļ�ϵͳ����:�ļ�δ�ҵ�:setFileContentProcess");
			return;
		}
		
		
		GFI.writeFile(File, Content);
		
	}
	
	
	//�����ļ����ж�������Match��ͬ���ַ���
	public int getRepeatLineCountProcess(File File,String Match){
		
		GDCEntity GDCE = null;
		
		//�ж��ļ��Ƿ�Ϊ��
		if(File == null){
			DataCore.LogManager.logError("�ļ�ϵͳ����:û������Target");
			return -1;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.logError("�ļ�ϵͳ����:�ļ�δ�ҵ�:getRepeatLineCountProcess");
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
			DataCore.LogManager.logError("�ļ�ϵͳ����:û������Target");
			return;
		}
		
		//�ж��ļ��Ƿ����
		if( ! File.exists()){
			DataCore.LogManager.logError("�ļ�ϵͳ����:�ļ�δ�ҵ�:addLineProcess");
			return;
		}
		
		GDCEntity GDCE = null;
		
		GDCE=GFI.getGDCEntity(File);
		
		GDCE.addLast(Content);
		
		GDCE.reset();
		
//		while(GDCE.next()){
//			System.out.println("||"+GDCE.get());
//		}
		
		
		GFI.writeGDCEntity(File, GDCE);
		
	}
	
	
	
	//����һ���µ�gdc�ļ�
	public boolean createNewGdcFileProcess(File File){
		
		if(File.exists()){
			return false;
		}
		

		
		try {
				
			File.getParentFile().mkdirs();
			File.createNewFile();
			this.addLineProcess(File, "@Type=GeneralDataCore");
			this.addLineProcess(File, "@Version=V6");
			this.addLineProcess(File, "@Symbol==");
			this.addLineProcess(File, "#	");
			
		} catch (IOException e) {
			e.printStackTrace();
			DataCore.LogManager.logError("���ء�����δ֪���ļ�ϵͳ����- createNewGdcFileProcess");
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
