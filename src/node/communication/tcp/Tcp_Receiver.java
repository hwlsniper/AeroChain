package node.communication.tcp;

import constant.Constant;
import main.Main;
import model.annotation.MulThreadShareData;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by DSY on 2018/6/19.
 * 共识算法的消息传递过程应该使用TCP进行可靠传输
 */
public class Tcp_Receiver implements Runnable{
    private static ServerSocket serverSocket;

    private static final int MAX_CONNECTION = 1;

    public static final String name = Tcp_Receiver.class.getName();

    @MulThreadShareData
    private static int count = 0;

    public static void init(){
        try {
            serverSocket = new ServerSocket(Constant.TCP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (Main.isRunning()){
            try {
                Thread thread = new Thread(new TcpLongLink(serverSocket.accept()));
                thread.start();
                if (addCount() == MAX_CONNECTION) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void clean(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized int addCount(){
        count++;
        return count;
    }
}
