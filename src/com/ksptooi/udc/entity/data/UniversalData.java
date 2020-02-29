package com.ksptooi.udc.entity.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.ksptooi.udc.entity.node.RNode;
import com.ksptooi.udc.io.UdcWriter;

public class UniversalData extends BasicData implements AutoCloseable{
	

	private RNode root = null;
	
	//ʵ����ͨ������
	public UniversalData(List<String> content,Path path,Charset cs) {
		this.content = (ArrayList<String>) content;
		this.sourcePath = path;
		this.charset = cs;
		root = new RNode("root", this.content);
		

		
	}
	
	public UniversalData(ArrayList<String> content,Path path,Charset cs) {
		
		this.content = content;
		this.sourcePath = path;
		this.charset = cs;
		root = new RNode("root", this.content);
		
	}
	
	
	/**
	 * ��ȡ���ڵ�
	 * @return ����RootNodeʵ��
	 */
	public RNode getRoot() {
		return this.root;
	}
	
	/**
	 * ��UniversalData[ͨ������]ת��ΪPlaneData[ƽ������]
	 * @return ����PlaneData[ƽ������]ʵ��
	 */
	public PlaneData toPlaneData() {
		
		BasicData bd = (BasicData)this;
		PlaneData pd = (PlaneData)bd;
		return pd;
	}
	
	/**
	 * ����ͬ���������ļ�
	 * @throws IOException 
	 */
	public void flush() {
		UdcWriter.writeUniversalDataNE(this);
	}

	
	@Override
	public void close() throws Exception {
		UdcWriter.writeUniversalData(this);
	}

	
}
