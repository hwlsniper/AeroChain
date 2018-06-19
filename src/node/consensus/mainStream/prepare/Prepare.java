package node.consensus.mainStream.prepare;

import com.alibaba.fastjson.JSON;
import model.annotation.MulThreadShareData;
import model.node.consensusMessage.PrepareModel;
import node.communication.UDP_Sender;
import node.consensus.mainStream.prePrepare.PrePrepare;
import model.node.consensusMessage.PrePrepareModel;
import model.node.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现共识算法中涉及prepare消息功能的类
 */
public class Prepare {
    /**
     * 当前共识轮次中，主节点提出的区块的确认数
     * 多线程共享数据
     */
    @MulThreadShareData
    private static volatile int validPrepare;

    @MulThreadShareData
    private static volatile Set<PrepareModel> evidence = new HashSet<>();

    /**
     * 区块链节点根据自己认可的prePrepare消息广播prepare消息
     * 处在同步状态的节点不广播prepare消息
     * @param prePrepare 区块链节点认可的prePrepare消息
     */
    public static void generate(PrePrepareModel prePrepare){
        if (!Node.isSynSwitcher()){
            PrepareModel model = new PrepareModel();
            model.setId(Node.getId());
            model.setView(prePrepare.getView());
            model.setHeight(prePrepare.getHeight());
            model.setDigest(prePrepare.getDigest());
            UDP_Sender.broadcast("<prepare>" + JSON.toJSONString(model));
        }
    }

    /**
     * 节点接收一条prepare消息后，进行验证，如果认可，则将当前区块的确认数加一
     * @param prepare 节点广播的prepare消息
     */
    public synchronized static void process(PrepareModel prepare){
        if (isValid(prepare)) {
            setValidPrepare(validPrepare + 1);
            evidence.add(prepare);
        }
    }

    private static boolean isValid(PrepareModel prepare){
        return prepare.getView() == Node.getView() &&
               prepare.getHeight() == (Node.getBlockChainHeight() + 1) &&
               prepare.getDigest().equals(PrePrepare.getDigest());
    }

    public synchronized static int getValidPrepare() {
        return validPrepare;
    }

    public synchronized static void setValidPrepare(int validPrepare) {
        Prepare.validPrepare = validPrepare;
    }

    public synchronized static Set<PrepareModel> getEvidence() {
        return evidence;
    }
}
