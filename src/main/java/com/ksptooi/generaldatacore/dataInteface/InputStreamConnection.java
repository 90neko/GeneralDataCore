package com.ksptooi.generaldatacore.dataInteface;

import com.ksptooi.generaldatacore.entity.data.DataSet;

import java.io.*;
import java.util.ArrayList;

public class InputStreamConnection extends DataConnection{

    private String charset = "utf-8";

    //ISC������
    public InputStreamConnection(InputStream is) {
        super(is, null);
    }

    public InputStreamConnection(InputStream is,String charset) {
        super(is, null);
        this.charset = charset;
    }

    /**
     * ��[��������Դ]��ȡ��DataSetʵ��
     */
    @Override
    public DataSet getDataSet() {
        DataSet set = new DataSet(this,this.getStringList());
        return set;
    }

    /**
     * ���Խ�DataSet������[����Դ]
     *
     * @param dataSet ���ݼ�
     * @return �ɹ�����true
     */
    @Override
    public boolean setDataSet(DataSet dataSet) {
        return false;
    }

    /**
     * ֱ�Ӵ�����Դ��ȡ�ַ�����ʽ������
     */
    @Override
    public ArrayList<String> getStringList() {

        ArrayList<String> list = new ArrayList<String>();

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(this.getInputStream(),charset));

            String str = "";

            while((str = br.readLine())!=null){
                list.add(str);
            }

            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }
}
