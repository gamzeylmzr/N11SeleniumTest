package com.gamzeyilmazer.helpers;

import java.io.*;

public class FileHelpers {

    FileReader fr;
    BufferedReader br;
    String readLine;
    String filePath;
    StringBuffer sb=new StringBuffer();
    String line;

    public void writeTextFile(String productDetail,String path) throws IOException{
        File file = new File(path);
        FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(productDetail);
        bw.close();
    }

    public StringBuffer readTextFile(String filePath) throws Exception{
       fr=new FileReader(filePath);
       br=new BufferedReader(fr);

       while((readLine=br.readLine())!=null){
           line=readLine;
           sb.append(line);
       }
       return sb;
    }
}
