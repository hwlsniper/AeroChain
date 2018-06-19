package main;

import client.MulticastReceiver;
import model.node.Node;
import node.communication.Tcp_Receiver;
import node.communication.Tcp_Sender;
import node.communication.UDP_Receiver;
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
    }
}
