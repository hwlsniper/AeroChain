package main;

import client.MulticastReceiver;
import node.consensus.mainStream.generateBlock.GenerateBlock;
import node.communication.Receiver;
import node.consensus.mainStream.prepared.Prepared;
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
    public static boolean running = false;

    private static int name = 1;

    public static void main(String[] args) throws Exception{
        running = true;
        Initial.init();

        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> {
            Thread thread = new Thread(r);
            switch (name++){
                case 1:
                    thread.setName("receiver");
                    break;
                case 2:
                    thread.setName("simulator");
                    break;
                case 3:
                    thread.setName("generateBlock");
                    break;
                case 4:
                    thread.setName("prepared");
                    break;
                case 5:
                    thread.setName("MulticastReceiver");
            }
            return thread;
        });
        executorService.execute(new Receiver());
        executorService.execute(new Simulator());
        executorService.execute(new GenerateBlock());
        executorService.execute(new Prepared());
        executorService.execute(new MulticastReceiver());
        while (true){
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = stdin.readLine()) != null){
                if (input.equals("exit")){
                    Clean.cleanUp();
                    executorService.shutdown();
                    System.exit(0);
                }
                if (input.equals("simulator_on"))
                    Simulator.switcher = true;
                if (input.equals("simulator_off"))
                    Simulator.switcher = false;
            }
        }
    }
}
