package uk.iksp.v7.session.factory;

import java.io.InputStream;

import uk.iksp.v7.session.stream.InputStreamDownloadSession;
import uk.iksp.v7.session.stream.InputStreamReaderSession;

public class StreamFactory {

	
	//��һ����������ȡSession
	public synchronized InputStreamReaderSession openInputStreamReaderSession(InputStream is){
		

		InputStreamReaderSession isrs=new InputStreamReaderSession();
		
		isrs.assign(is);
			
		return isrs;
	}
	

	//��һ������������Session
	public synchronized InputStreamDownloadSession openInputStreamDownloadSession(InputStream is){
		
		
		InputStreamDownloadSession isds=new InputStreamDownloadSession();
		
		isds.assign(is);
			
		return isds;
	}
	
	
}
