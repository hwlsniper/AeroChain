package node.consensus.mainStream.prepared;

import constant.Constant;
import model.annotation.MulThreadShareData;
import model.node.Node;
import model.node.consensusMessage.PreparedEvidence;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prePrepare.PrePrepare;
import node.consensus.mainStream.prepare.Prepare;
import node.consensus.viewChange.ViewChange;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by DSY on 2018/5/31.
 * 每隔一个区块生成间隔，各个节点都会检查是否生成了新的区块，如果有那么将其添加至区块链
 * 如果没有，那么认为主节点出现错误，通过调用viewChange协议使系统回归正常状态
 *
 * viewChange和同步协议中涉及为区块生成证据的功能目前考虑在该类中实现，不确定
 */
public class Prepared implements Runnable {
    @MulThreadShareData
    private static List<PreparedEvidence> evidence = new ArrayList<>();

    @Override
    public void run() {
        while (!Node.isViewChangeSwitcher()){
            try {
                //为了保证不会过早的验证是否生成区块，使检查新区块的时间和生成新区块的时间错开3秒
                if (Node.getBlockChainHeight() == 0)
                    TimeUnit.SECONDS.sleep(3);
                TimeUnit.SECONDS.sleep(2 * Constant.BLOCK_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Prepare.getValidPrepare() >= Node.getThreshold()) {
                Node.addBlock(PrePrepare.getBlock());
                add(new PreparedEvidence(PrePrepare.getEvidence(), Prepare.getEvidence(), Node.getBlockChainHeight()));
                PrePrepare.setBlock(null);
                PrePrepare.setDigest(null);
                Prepare.setValidPrepare(0);
                if (Node.getBlockChainHeight() % Constant.CHECKPOINT_HEIGHT == 0){
                    Checkpoint.generate();
                }
            }else {
                PrePrepare.setBlock(null);
                PrePrepare.setDigest(null);
                Prepare.setValidPrepare(0);
                ViewChange.generate();
            }
        }
    }

    public synchronized static List<PreparedEvidence> getEvidenceByHeight(int start, int end) {
        return evidence.stream().
                filter(evidence -> evidence.getHeight() >= start && evidence.getHeight() <= end).
                collect(Collectors.toList());
    }

    private synchronized static void add(PreparedEvidence evidence) {
        Prepared.evidence.add(evidence);
    }
}
