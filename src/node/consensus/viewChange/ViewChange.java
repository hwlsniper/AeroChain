package node.consensus.viewChange;

import com.alibaba.fastjson.JSON;
import model.node.consensusMessage.PreparedEvidence;
import model.node.consensusMessage.ViewChangeModel;
import node.communication.Sender;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;
import model.block.Block;
import model.node.Node;
import node.communication.Resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewChange {
    private static List<ViewChangeModel> viewChangeProofs = new ArrayList<>();

    int count;

    public static void generate(){
        System.out.println("view change");
        System.exit(0);
        ViewChangeModel model = new ViewChangeModel();
        model.setId(Node.getId());
        model.setCheckpointEvidence(Checkpoint.getCheckpointProofs());
        model.setNewView(Node.getView() + 1);
        model.setLatestCheckpoint(Checkpoint.getLatesetCheckpoint());
        List<PreparedEvidence> evidence = Prepared.getEvidenceByHeight(Checkpoint.getLatesetCheckpoint() + 1, Node.getBlockChainHeight());
        model.setPreparedEvidence(evidence);
        Sender.broadcast("<view-change>" + JSON.toJSONString(model));
    }

    public static void process(ViewChangeModel model){
        if (model.getNewView() == Node.getId() % Node.getNodeNums()){
            viewChangeProofs.add(model);
        }
    }

}
