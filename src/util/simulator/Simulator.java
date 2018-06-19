package util.simulator;

import com.alibaba.fastjson.JSON;
import constant.Constant;
import model.record.Record;
import node.communication.UDP_Sender;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Simulator implements Runnable{
    public static boolean switcher = true;
    private static BufferedReader bufferedReader;

    public static void init(){
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(Constant.SIMULATE), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void clean(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (Simulator.switcher){
            Record record = null;
            String line;
            try {
                if ((line = bufferedReader.readLine()) != null){
                    record = new Record(line);
                }
                TimeUnit.SECONDS.sleep(5);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            if (record == null)
                record = new Record("end!");
            UDP_Sender.broadcast("<record>"+JSON.toJSONString(record));
        }
    }

}
