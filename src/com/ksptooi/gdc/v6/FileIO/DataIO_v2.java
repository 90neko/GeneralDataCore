package com.ksptooi.gdc.v6.FileIO;

import com.ksptooi.gdc.Entity.GDCEntity;
import com.ksptooi.gdc.v6.FileProcess.dataSession;

public interface DataIO_v2 {

	
	//��ȡGDCʵ��
	public GDCEntity getGDCEntity(dataSession ds,GDCEntity gdce);
	
	//дGDCʵ��
	public void writeGDCEntity(dataSession ds,GDCEntity gdce);
	
	//����д��
	public void writeFile(dataSession ds,GDCEntity gdce);
	
	//����д��
	public void writeFile(dataSession ds,String string);
	
	
}
