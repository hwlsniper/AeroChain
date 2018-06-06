package node.consensus.synchronize;

import model.node.consensusMessage.SynchronyModel;
import node.communication.Sender;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;
import model.node.Node;

public class Synchrony {
    public static void generate(){
        SynchronyModel model = new SynchronyModel();
        model.setHeight(Node.getBlockChainHeight());
        model.setId(Node.getId());
        Node.setSynSwitcher(true);
        Sender.broadcast("<synchronize>" + model);
    }

    public static void process(SynchronyModel model){

    }
}
