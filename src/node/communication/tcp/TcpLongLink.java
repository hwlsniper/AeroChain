package node.communication.tcp;

import main.Main;
import node.communication.Resolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by DSY on 2018/6/19.
 */
public class TcpLongLink implements Runnable {
    private Socket socket;

    private BufferedReader bufferedReader;

    TcpLongLink(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (Main.isRunning()){
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    Resolver.resolve(line);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
