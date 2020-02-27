package com.ksptooi.udc.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.ksptooi.udc.entity.data.UniversalData;

public class UdcWriter {

	/**
	 * д��UniversalDataʵ�����ļ�
	 * @param UniversalDataʵ��
	 * @throws IOException ��д��ʧ��ʱ�׳��쳣.
	 */
	public static void writeUniversalData(UniversalData ud) throws IOException {
		
		BufferedWriter nbw = Files.newBufferedWriter(ud.getSourcePath(), ud.getCharset());
		
		
		for(String str:ud.getContent()) {
			
			nbw.write(str);
			nbw.newLine();
		}
		
		nbw.flush();
		nbw.close();
		
	}
	
	//д��UniversalDataʵ�����ļ�
	public static void writeUniversalDataNE(UniversalData ud){
		
		try {
			writeUniversalData(ud);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
