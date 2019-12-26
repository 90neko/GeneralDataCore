package uk.iksp.v7.session.stream;

import java.io.IOException;
import java.io.InputStream;
import uk.iksp.v7.session.common.AbstractStreamSession;


public class InputStreamReaderSession extends AbstractStreamSession{

	
	
	
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
