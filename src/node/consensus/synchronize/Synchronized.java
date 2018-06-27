package node.consensus.synchronize;

import model.block.Block;
import model.node.Node;
import model.node.consensusMessage.SynchronizedModel;

/**
 * Created by DSY on 2018/6/3.
 * 接受到同步请求的节点执行的操作
 */
public class Synchronized {
    public static void process(SynchronizedModel model){
        if (Node.isSynSwitcher()) {
            for (Block block : model.getBlockChain()){
                Node.addBlock(block);
            }
            for (Block block : Node.getTmpBlocks()){
                if (block.getIndex() > Node.getBlockChainHeight()){
                    Node.addBlock(block);
                }
            }
            Node.setSynSwitcher(false);
        }
    }
}
