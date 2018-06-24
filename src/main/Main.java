package main;

import client.MulticastReceiver;
import node.consensus.mainStream.generateBlock.GenerateBlock;
import node.communication.udp.UDP_Receiver;
import node.consensus.mainStream.prepared.Prepared;
import node.consensus.synchronize.Synchrony;
import util.simulator.Simulator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by DSY on 2018/5/31.
 * 区块链应用包括区块链节点和客户端两部分
 * 区块链应用程序入口，启动客户端，区块链节点。
 */
public class Main {
    /** 标志着区块链应用是否启动 */
    private static boolean running;

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception{
        Initial.init();
        executorService.execute(new UDP_Receiver());
        executorService.execute(new Simulator());
        executorService.execute(new GenerateBlock());
        executorService.execute(new Prepared());
        executorService.execute(new MulticastReceiver());
        while (true){
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = stdin.readLine()) != null){
                if (input.equals("exit")){
                    Main.shutdown();
                }
                if (input.equals("simulator_on"))
                    Simulator.switcher = true;
                if (input.equals("simulator_off"))
                    Simulator.switcher = false;
                if (input.equals("syn"))
                    Synchrony.generate();
            }
        }
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Main.running = running;
    }

    public static void shutdown(){
        Clean.cleanUp();
        executorService.shutdown();
        System.exit(0);
    }
}
