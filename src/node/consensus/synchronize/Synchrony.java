package node.consensus.synchronize;

import node.communication.Sender;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;
import model.block.Block;
import model.node.Node;

import java.util.List;

import static node.consensus.checkpoint.Checkpoint.isValidCheckpoint;
import static node.consensus.mainStream.prepared.Prepared.isValidBlock;

public class Synchrony {
    private static final int checkpointProofs = 3;

    private static final int checkpoint = 2;

    private static final int blockProofs = 4;

    private static final int blockchain = 6;

    public static void generate(){
        SynchronyModel model = new SynchronyModel();
        model.setHeight(Node.getBlockChainHeight());
        model.setId(Node.getId());
        Node.setSynSwitcher(true);
        Sender.broadcast("<synchronize>" + model);
    }

    public static void process(String data){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<synchronized").append(",");
        stringBuilder.append(Node.getView()).append(",");
        stringBuilder.append(Checkpoint.getLatestCheckpoint()).append(",");
        stringBuilder.append(Checkpoint.getCheckpointProofs()).append(",");
        stringBuilder.append(Prepared.getPreparedProofsAfterCheckpoint()).append(",");
        stringBuilder.append(Node.getId()).append(",");
        stringBuilder.append(Node.getBlockChain()).append(">");
        Sender.broadcast(stringBuilder.toString());
    }
}
