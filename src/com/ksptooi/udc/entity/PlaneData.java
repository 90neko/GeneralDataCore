package com.ksptooi.udc.entity;

import com.ksptooi.udc.io.UdcWriter;

public class PlaneData extends BasicData{

	
	
	/**
	 * ��PlaneData[ƽ������]ת��ΪUniversalData[ͨ������]
	 * @return ����UniversalDataʵ��
	 */
	public UniversalData toUniversalData() {
		
		BasicData basic = (BasicData)this;
		UniversalData universal = (UniversalData) basic;
		return universal;
	}
	
	
	/**
	 * ����ͬ�������ļ�
	 */
	public void flush() {
		UdcWriter.writeUniversalDataNE(this.toUniversalData());
	}
	
	
}
