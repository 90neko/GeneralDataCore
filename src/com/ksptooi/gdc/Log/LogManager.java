package com.ksptooi.gdc.Log;

import com.ksptooi.gdc.Main.DataCore;

public class LogManager {

	//����һ����Ϣ
	public void sendInfo(String Message){
		System.out.println("[GeneralDataCore"+DataCore.gdc_Version+"]��"+Message);
	}
	
	//���;�����Ϣ
	public void sendWarning(String Message){
		System.out.println("[GeneralDataCore"+DataCore.gdc_Version+"]����:"+Message);
	}
	
	//���ʹ�����Ϣ
	public void sendError(String Message){
		System.out.println("[GeneralDataCore"+DataCore.gdc_Version+"]����:"+Message);
	}
	
	
	
}
