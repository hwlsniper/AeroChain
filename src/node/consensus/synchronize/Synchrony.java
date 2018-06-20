package node.consensus.synchronize;

import com.alibaba.fastjson.JSON;
import model.block.Block;
import model.node.consensusMessage.PreparedEvidence;
import model.node.consensusMessage.SynchronizedModel;
import model.node.consensusMessage.SynchronyModel;
import model.node.consensusMessage.ViewChangeModel;
import node.communication.tcp.Tcp_Sender;
import node.communication.udp.UDP_Sender;
import model.node.Node;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;

import java.util.ArrayList;
import java.util.List;

public class Synchrony {
    public static void generate(){
        SynchronyModel model = new SynchronyModel();
        model.setHeight(Node.getBlockChainHeight());
        model.setId(Node.getId());
        Node.setSynSwitcher(true);
        UDP_Sender.broadcast("<synchronize>" + model);
    }

    public static void process(SynchronyModel model){
        SynchronizedModel synchronizedModel = new SynchronizedModel();
        synchronizedModel.setCurrentView(Node.getView());
        synchronizedModel.setId(Node.getId());
        int start = model.getHeight() + 1;
        int end = Node.getBlockChainHeight();
        if (start <= Checkpoint.getLatestCheckpoint())
            synchronizedModel.setCheckpointEvidence(Checkpoint.getCheckpointProofs());
        List<Block> blocks = new ArrayList<>();
        for (int i = start ; i <= end ; i++){
            blocks.add(Node.getBlock(i));
        }
        List<PreparedEvidence> preparedEvidences = new ArrayList<>(Prepared.getEvidenceByHeight(start, end));
        synchronizedModel.setBlockChain(blocks);
        synchronizedModel.setPreparedEvidence(preparedEvidences);
        Tcp_Sender.broadcastRecord("<synchronized>" + JSON.toJSONString(synchronizedModel));
    }
}
