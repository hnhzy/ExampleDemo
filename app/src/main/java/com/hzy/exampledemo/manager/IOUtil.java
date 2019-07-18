package com.hzy.exampledemo.manager;
 
import java.io.Closeable;
import java.io.IOException;
 
/**
* Description IOUtil
* @author hzy
* Create on 2019/7/18 11:38
*/
public class IOUtil {
    public static void closeAll(Closeable... closeables){
        if(closeables == null){
            return;
        }
        for (Closeable closeable : closeables) {
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}