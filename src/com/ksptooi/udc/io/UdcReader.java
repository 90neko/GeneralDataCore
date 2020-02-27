package com.ksptooi.udc.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.ksptooi.udc.entity.UniversalData;

import uk.iksp.v6.CharSet.Detector;

public class UdcReader {

	private static Detector detector=new Detector();
	

	/**
	 * ���ļ������ɲ�����UniversalDataʵ�� [���׳��쳣]
	 * @param filePath �ļ�·��
	 * @return UnityDataʵ��
	 */
	public static UniversalData read(String filePath) {
		
		UniversalData udf = null;
		
		try {
			udf = readAsUniversalData(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return udf;
	}
	
	
	/**
	 * ���ļ������ɲ�����UniversalDataʵ��
	 * @param filePath �ļ�·��
	 * @return UnityDataʵ��
	 * @throws IOException �ļ���ȡ����ʱ�׳��쳣
	 */
	public static UniversalData readAsUniversalData(String filePath) throws IOException {
	
		Path path = Paths.get(filePath);
		
		Charset encode = detector.nDetector(path.toFile());
		
		UniversalData udf = new UniversalData((ArrayList<String>)Files.readAllLines(path, encode),path,encode);
	
		return udf;
	}
	
	/**
	 * ���ļ������ɲ�����UniversalDataʵ�� [���׳��쳣]
	 * @param filePath �ļ�·��
	 * @return UnityDataʵ��
	 */
	public static UniversalData readAsUniversalDataNE(String filePath) {
		
		UniversalData udf = null;
		
		try {
			udf = readAsUniversalData(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return udf;
	}
	
}
