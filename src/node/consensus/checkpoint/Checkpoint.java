package node.consensus.checkpoint;

import model.block.Block;
import model.node.consensusMessage.CheckpointModel;
import node.communication.Sender;
import model.node.Node;
import util.Log;
import util.hash.Hash;

import java.util.List;

/**
 * 用于实现检查点协议的相关内容
 */
public class Checkpoint {
    public static String stataHash;

    public synchronized static void generate(){
        CheckpointModel model = new CheckpointModel();
        model.setId(Node.getId());
        model.setDigest(Hash.hash(Node.getBlockChain().get(Node.getBlockChainHeight() - 1).toString()));
        model.setView(Node.getView());
        stataHash = Hash.hash(model.toString());
    }

    /**
     * 实现一个区块链节点接受到其他节点发送的checkpoint消息后执行的相关操作
     * @param model 别的节点发送的checkpoint消息
     */
    public synchronized static void process(CheckpointModel model){
        if ()
    }

    private static void deleteOldData(){}

    public static List<String> getCheckpointProofs(){
        return Log.getLogs("checkpoint");
    }

    public static boolean isValidCheckpoint(int checkpoint , String proof){
        return true;
    }
}
