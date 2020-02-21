package com.ksptooi.udc.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.ksptooi.udc.entity.UnityData;

import uk.iksp.v6.CharSet.Detector;

public class UdcRead {

	private static Detector detector=new Detector();
	
	/**
	 * ���ļ������ɲ�����UnityDataʵ��
	 * @param filePath �ļ�·��
	 * @return UnityDataʵ��
	 * @throws IOException �ļ���ȡ����ʱ�׳��쳣
	 */
	public static UnityData readAsUnityData(String filePath) throws IOException {
	
		Path path = Paths.get(filePath);
		
		Charset encode = detector.nDetector(path.toFile());
		
		UnityData udf = new UnityData((ArrayList<String>)Files.readAllLines(path, encode),path,encode);
	
		return udf;
	}
	
}
