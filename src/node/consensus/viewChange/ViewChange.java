package node.consensus.viewChange;

import com.alibaba.fastjson.JSON;
import main.Clean;
import model.node.consensusMessage.PreparedEvidence;
import model.node.consensusMessage.ViewChangeModel;
import node.communication.UDP_Sender;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;
import model.node.Node;

import java.util.ArrayList;
import java.util.List;

public class ViewChange {
    private static List<ViewChangeModel> viewChangeProofs = new ArrayList<>();

    int count;

    public static void generate(){
        System.out.println("view change");
        Clean.cleanUp();
        System.exit(0);
        ViewChangeModel model = new ViewChangeModel();
        model.setId(Node.getId());
        model.setCheckpointEvidence(Checkpoint.getCheckpointProofs());
        model.setNewView(Node.getView() + 1);
        model.setLatestCheckpoint(Checkpoint.getLatesetCheckpoint());
        List<PreparedEvidence> evidence = Prepared.getEvidenceByHeight(Checkpoint.getLatesetCheckpoint() + 1, Node.getBlockChainHeight());
        model.setPreparedEvidence(evidence);
        UDP_Sender.broadcast("<view-change>" + JSON.toJSONString(model));
    }

    public static void process(ViewChangeModel model){
        if (model.getNewView() == Node.getId() % Node.getNodeNums()){
            viewChangeProofs.add(model);
        }
    }

}
