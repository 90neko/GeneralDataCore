package com.ksptooi.generaldatacore.dataInteface;

import com.ksptooi.generaldatacore.entity.data.DataSet;

import java.io.*;
import java.util.ArrayList;

public class InputStreamConnection extends DataConnection{

    private String charset = "utf-8";

    //ISC������
    public InputStreamConnection(InputStream is) {
        super(is, null);

        //������֧��д�����
        this.setSupportWrite(false);
    }

    public InputStreamConnection(InputStream is,String charset) {
        super(is, null);
        this.charset = charset;

        //������֧��д�����
        this.setSupportWrite(false);
    }

    /**
     * ��[��������Դ]��ȡ��DataSetʵ��
     */
    @Override
    public DataSet getDataSet() {

        DataSet set = new DataSet(this,this.getStringList());

        //����ֻ�����ݼ�
        set.setOnlyRead(true);

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

            String line = "";

            while((line=br.readLine()) != null){
                list.add(line);
            }

            this.getInputStream().close();
            br.close();

            //��ȡһ�κ󽫲��ܹ��ٴζ�ȡ
            this.setSupportRead(false);

        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }
}
