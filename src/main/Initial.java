package main;

import client.MulticastReceiver;
import model.node.Node;
import node.communication.tcp.Tcp_Receiver;
import node.communication.tcp.Tcp_Sender;
import node.communication.udp.UDP_Receiver;
import node.consensus.mainStream.prepared.Prepared;
import util.simulator.Simulator;

class Initial {
    static void init(){
        UDP_Receiver.init();
        Simulator.init();
        Node.threshold();
        MulticastReceiver.init();
        Prepared.init();
        Tcp_Sender.init();
        Tcp_Receiver.init();
        Main.setRunning(true);
        Node.setViewChangeSwitcher(false);
        Node.setSynSwitcher(false);
    }
}
