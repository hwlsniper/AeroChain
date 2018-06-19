package node.communication;

import constant.Constant;
import main.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by DSY on 2018/6/19.
 * 共识算法的消息传递过程应该使用TCP进行可靠传输
 */
public class Tcp_Receiver implements Runnable{
    private static ServerSocket serverSocket;

    public static void init(){
        try {
            serverSocket = new ServerSocket(Constant.TCP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (Main.running){
            try {
                Socket socket = serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    Resolver.resolve(line);
                }
                socket.shutdownInput();
                bufferedReader.close();
                socket.close();
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
}
