package node.communication;

import constant.Constant;
import main.Main;
import util.Log;

import java.io.*;
import java.net.*;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点使用UDP协议与彼此和客户端通信
 */
public class Receiver implements Runnable {
    private volatile static DatagramSocket server;

    private static PrintWriter printWriter;

    public void run(){
        while (Main.running){
            byte[] buf = new byte[32768];
            DatagramPacket packet = new DatagramPacket(buf , buf.length);
            try {
                server.receive(packet);
            }catch (IOException e){
                e.printStackTrace();
            }
            String receive = new String(packet.getData() , 0 , packet.getLength());
            printWriter.println(receive);
            Resolver.resolve(receive);
        }
    }

    public static void init(){
        if (server == null){
            synchronized (Receiver.class){
                if (server == null){
                    try {
                        server = new DatagramSocket(Constant.PORT);
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.LOG_BASE_PATH + "receive.txt", true), "UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clean(){
        server.close();
        printWriter.close();
    }
}
