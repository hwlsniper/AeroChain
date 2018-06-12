package main;

import client.MulticastReceiver;
import model.node.Node;
import node.communication.Receiver;
import node.consensus.mainStream.prepared.Prepared;
import util.simulator.Simulator;

class Initial {
    static void init(){
        Receiver.init();
        Simulator.init();
        Node.threshold();
        MulticastReceiver.init();
        Prepared.init();
    }
}
