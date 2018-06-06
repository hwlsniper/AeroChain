package main.init;

import constant.Constant;
import model.node.Node;
import node.communication.Receiver;
import client.Client;
import org.apache.commons.lang3.SystemUtils;
import util.Sign;
import util.simulator.Simulator;

public class Initial {
    public static void init(){
        Receiver.init();
        Simulator.init();
        Client.init();
        Node.threshold();
    }
}
