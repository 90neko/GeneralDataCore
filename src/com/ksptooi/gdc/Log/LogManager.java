package com.ksptooi.gdc.Log;

import com.ksptooi.gdc.Util.Var;

public class LogManager {

	//����һ����Ϣ
	public void writeLogOfINFO(String Message){
		System.out.println("[GeneralDataCore"+Var.gdc_Version+"]��"+Message);
	}
	
	//���;�����Ϣ
	public void writeLogOfWarning(String Message){
		System.out.println("[GeneralDataCore"+Var.gdc_Version+"]����:"+Message);
	}
	
	//���ʹ�����Ϣ
	public void writeLogOfError(String Message){
		System.out.println("[GeneralDataCore"+Var.gdc_Version+"]����:"+Message);
	}
	
	
}
