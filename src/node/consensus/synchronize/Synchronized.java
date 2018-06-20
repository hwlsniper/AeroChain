package node.consensus.synchronize;

import model.node.Node;
import model.node.consensusMessage.SynchronizedModel;

/**
 * Created by DSY on 2018/6/3.
 * 接受到同步请求的节点执行的操作
 */
public class Synchronized {
    public static void generate(){

    }

    public static void process(SynchronizedModel model){
        if (!Node.isSynSwitcher()) return;
    }
}
