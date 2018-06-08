package util;

import constant.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private static final String basePath = Constant.LOG_BASE_PATH;

    public static void log(String log , String path , boolean append){
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(basePath + path, append), "UTF-8")));
            printWriter.println(log);
            printWriter.close();
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
