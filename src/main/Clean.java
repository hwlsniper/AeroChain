package main;

import client.MulticastReceiver;
import node.communication.tcp.Tcp_Receiver;
import node.communication.tcp.Tcp_Sender;
import node.communication.udp.UDP_Receiver;
import node.consensus.mainStream.prepared.Prepared;
import util.simulator.Simulator;

/**
 * 用于处理程序退出时的相关需要
 */
public class Clean {
    public static void cleanUp(){
        UDP_Receiver.clean();
        MulticastReceiver.clean();
        Prepared.clean();
        Simulator.clean();
        Tcp_Sender.clean();
        Tcp_Receiver.clean();
    }
}
