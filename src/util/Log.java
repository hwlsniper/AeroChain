package util;

import constant.Constant;
import java.io.*;

public class Log {
    private static final String basePath = Constant.LOG_BASE_PATH;

    /**
     * 只有当偶尔调用这个方法的时候才使用，为了避免经常的打开、关闭流
     */
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
