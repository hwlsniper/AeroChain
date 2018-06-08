package main;

import model.node.Node;
import node.communication.Receiver;
import client.Client;
import util.simulator.Simulator;

class Initial {
    static void init(){
        Receiver.init();
        Simulator.init();
//        Client.init();
        Node.threshold();
    }
}
