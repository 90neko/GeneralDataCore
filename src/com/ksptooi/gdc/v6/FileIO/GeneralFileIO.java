package com.ksptooi.gdc.v6.FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.ksptooi.gdc.Entity.GDCEntity;
import com.ksptooi.gdc.Main.DataCore;

import uk.iksp.gdc.v6.CharSet.Detector;

public class GeneralFileIO {

	
	Detector detector=null;
	
	
	public GeneralFileIO(){
		this.detector=new Detector();
	}
	
	
	//从文件获取GDC实体
	public GDCEntity getGDCEntity(File File){
		
		ArrayList<String> List=new ArrayList<String>();
		
		try{
			
			//自动探测文件编码
			String encode = this.detector.detector(File);
			
			//使用自动探测的编码读文件
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(File),encode));
			
			String line=null;
			
			while((line=br.readLine()) != null){
				
				List.add(line);
				
			}
			br.close();
			
			return new GDCEntity(List);
				
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("文件系统错误 at getFileContent");
		}
		
		return new GDCEntity(List);
		
	}
	
	
	//写GDC实体至文件
	public void writeGDCEntity(File file,GDCEntity entity){
		
		entity.reset();
		
		try{
			
			PrintWriter pw=new PrintWriter(file,"UTF-8");
						
			String content="";
			
			content = entity.getFirst();
			
			while(entity.next()){
				
				content=content+"\r\n"+entity.get();
				
			}
			
			pw.println(content);		
			
			pw.flush();
			pw.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("文件系统错误 at writeToFile");
		}	
		
	}
	
	
	//覆盖写入至文件
	public void writeFile(File file,String str){
				
		try{
			
			PrintWriter pw=new PrintWriter(file,"UTF-8");
						
			
			pw.println(str);		
			
			pw.flush();
			pw.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("文件系统错误 at writeToFile");
		}	
		
		
	}
	
	
	//获取文件的指定行 通过 InputStream
	public String getFileKeyLineOfInputStream(InputStream is, String key, String separationSymbol) {
		
		
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String line=null;
			
			
			while((line=br.readLine()) != null){
				
				if(line.contains(key+separationSymbol)){
					br.close();
					return line;
				}
				
			}
			
			br.close();
			return null;
					
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("文件系统错误 at getFileKey");
		}
		
		
		return null;
		
	}
	
	
	
}
