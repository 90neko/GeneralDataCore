package com.ksptooi.generaldatacore.test;

import org.junit.Test;

import com.ksptooi.generaldatacore.DataCore;
import com.ksptooi.generaldatacore.dataInteface.DataInterface;

public class GeneralDataCoreTest {
	
	
	@Test
	public void dataIntefaceTest() {
		
		
		DataInterface di = DataCore.getDataInteface("C:\\asmc_core/asmc.conf1");
		
		
		if(di == null) {
			System.out.println("��ȡ���ݽӿ�ʧ��");
			return;
		}
		
		
		System.out.println("��ȡ���ݽӿڳɹ�");
		
	}
	
	
	

}
