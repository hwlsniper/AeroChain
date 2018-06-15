package main;

import client.MulticastReceiver;
import node.communication.Receiver;
import node.consensus.mainStream.prepared.Prepared;
import util.simulator.Simulator;

/**
 * 用于处理程序退出时的相关需要
 */
public class Clean {
    public static void cleanUp(){
        Receiver.clean();
        MulticastReceiver.clean();
        Prepared.clean();
        Simulator.clean();
    }
}
