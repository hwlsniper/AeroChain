package node.communication.tcp;

import constant.Constant;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSY on 2018/6/19.
 * 全部使用TCP长连接
 */
public class Tcp_Sender {
    private static List<Socket> recordSockets;

    private static List<PrintWriter> printWriters;

    public static void init(){
        recordSockets = new ArrayList<>();
        for (String ip : Constant.ADDRESS){
            try {
                recordSockets.add(new Socket(ip, Constant.TCP_PORT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printWriters = new ArrayList<>();
        for (Socket socket : recordSockets){
            try {
                printWriters.add(new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void broadcastRecord(String data){
        for (PrintWriter printWriter : printWriters){
            printWriter.println(data);
            printWriter.flush();
        }
    }

    public static void clean(){
        for (PrintWriter printWriter : printWriters)
            printWriter.close();
        for (Socket socket : recordSockets){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
