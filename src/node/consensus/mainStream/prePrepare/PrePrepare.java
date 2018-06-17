package node.consensus.mainStream.prePrepare;

import com.alibaba.fastjson.JSON;
import model.annotation.MulThreadShareData;
import model.block.Block;
import model.node.Node;
import model.node.consensusMessage.PrePrepareModel;
import model.record.Record;
import node.buffer.BufferPool;
import node.communication.Sender;
import node.consensus.mainStream.prepare.Prepare;
import util.Log;
import util.hash.Hash;

/**
 * 实现共识算法中涉及prePrepare消息功能的类
 */
public class PrePrepare {
    /**
     * 记录当前共识轮次中主节点提出的新区块
     * 多线程共享数据
     */
    @MulThreadShareData
    private volatile static Block block;

    /**
     * 记录当前共识轮次中主节点提出的prePrepare消息
     * 多线程共享数据
     */
    @MulThreadShareData
    private static String digest;

    @MulThreadShareData
    private static volatile PrePrepareModel evidence;

    /**
     * 主节点发送prePrepare消息功能
     * @param block prePrepare中的新区块
     */
    public static void generate(Block block){
        PrePrepareModel model = new PrePrepareModel();
        model.setPrimary(Node.getPrimary());
        model.setView(Node.getView());
        model.setHeight(Node.getBlockChainHeight() + 1);
        model.setBlock(block);
        model.setDigest(Hash.hash(block.toString()));
        Sender.broadcast("<pre-prepare>" + JSON.toJSONString(model));
    }

    /**
     * 节点接收到主节点发送的prePrepare消息后，首先记录日志，进行验证，如果认可，则发送prepare消息
     * @param prePrepare 主节点广播的prePrepare消息
     */
    public static synchronized void process(PrePrepareModel prePrepare){
        if (isValid(prePrepare)){
            setDigest(prePrepare.getDigest());
            setBlock(prePrepare.getBlock());
            evidence = prePrepare;
            Prepare.generate(prePrepare);
        }
    }

    private static boolean isValid(PrePrepareModel prePrepare){
        //主节点直接认可自己提出的区块
        if (prePrepare.getPrimary() == Node.getId()) return true;
        if (!(Node.getView() == prePrepare.getView())) return false;
        if (Node.getBlockChainHeight() + 1 != prePrepare.getHeight()) return false;
        Block block = prePrepare.getBlock();
        for (Record record : block.getData()){
            if (!BufferPool.isContain(record)){
                Log.log(record.toString(), "error.txt", false);
                return false;
            }
        }
        return block.getPrevHash().equals(Node.getLatestHash());
    }

    public synchronized static String getDigest() {
        return digest;
    }

    public synchronized static void setDigest(String digest) {
        PrePrepare.digest = digest;
    }

    public synchronized static Block getBlock() {
        return block;
    }

    public synchronized static void setBlock(Block block) {
        PrePrepare.block = block;
    }

    public synchronized static PrePrepareModel getEvidence() {
        return evidence;
    }
}
