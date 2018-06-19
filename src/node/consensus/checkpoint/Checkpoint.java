package node.consensus.checkpoint;

import com.alibaba.fastjson.JSON;
import model.annotation.MulThreadShareData;
import model.node.consensusMessage.CheckpointEvidence;
import model.node.consensusMessage.CheckpointModel;
import model.node.Node;
import node.communication.udp.UDP_Sender;
import util.hash.Hash;

import java.util.*;

/**
 * 用于实现检查点协议的相关内容
 */
public class Checkpoint {
    private static String stateHash;

    private static int count = 0;

    @MulThreadShareData
    private static Set<CheckpointModel> evidence = new HashSet<>();

    private static int checkpoint = 0;

    public synchronized static void generate(){
        CheckpointModel model = new CheckpointModel();
        model.setId(Node.getId());
        model.setDigest(Hash.hash(Node.getBlockChain().get(Node.getBlockChainHeight() - 1).toString()));
        model.setView(Node.getView());
        model.setHeight(Node.getBlockChainHeight());
        stateHash = Hash.hash(model.toString());
        count = 0;
        UDP_Sender.broadcast("<checkpoint>" + JSON.toJSONString(model));
    }

    /**
     * 实现一个区块链节点接受到其他节点发送的checkpoint消息后执行的相关操作
     * @param model 别的节点发送的checkpoint消息
     */
    public synchronized static void process(CheckpointModel model){
        String hash = Hash.hash(model.toString());
        if (hash.equals(stateHash) && model.getView() == Node.getId()){
            evidence.add(model);
            count++;
            if (count >= Node.getCrashThreshold()){
                checkpoint = model.getHeight();
                reset();
            }
        }
    }

    private synchronized static void reset(){
        evidence = new HashSet<>();
    }

    private static void deleteOldData(){}

    public static synchronized CheckpointEvidence getCheckpointProofs(){
        CheckpointEvidence evidence = new CheckpointEvidence();
        evidence.setEvidence(Checkpoint.evidence);
        return evidence;
    }

    public static boolean isValidCheckpoint(int checkpoint, Set<CheckpointModel> evidence){
        if (evidence.size() < Node.getCrashThreshold()) return false;
        Iterator<CheckpointModel> iterator = evidence.iterator();
        CheckpointModel example = iterator.next();
        return example.getHeight() == checkpoint;
    }

    public static int getLatesetCheckpoint() {
        return checkpoint;
    }
}
