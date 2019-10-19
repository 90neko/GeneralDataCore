package uk.iksp.v7.Session;

import java.io.IOException;
import java.io.InputStream;



public class InputStreamReaderSession extends Session{

	
	
	
	InputStream dataSources = null;
	
	
	
	
	//����
	public void assign(InputStream dataSources){
		
		this.isRelease = false;
		
		//��������Դ
		this.dataSources=dataSources;
		
		//��������
		this.dataCache = io.readAsGeneralDataEntity(this.dataSources);
		
	}
	
	
	//�ر�/�ͷ� Session
	@Override
	public void close(){
			
		this.isRelease = true;
		dataCache = null;
		
		try {
			dataSources.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void release() {
		this.close();
	}

	
	
	
	
	
}
