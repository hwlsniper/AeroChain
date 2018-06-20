package node.consensus.viewChange;

import com.alibaba.fastjson.JSON;
import main.Clean;
import model.node.consensusMessage.PreparedEvidence;
import model.node.consensusMessage.ViewChangeEvidence;
import model.node.consensusMessage.ViewChangeModel;
import node.communication.udp.UDP_Sender;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prepared.Prepared;
import model.node.Node;

import java.util.ArrayList;
import java.util.List;

public class ViewChange {
    private static ViewChangeEvidence viewChangeProofs = new ViewChangeEvidence();

    private static int count = 0;

    public static void generate(){
        System.out.println("view change");
        Clean.cleanUp();
        System.exit(0);

        Node.setViewChangeSwitcher(true);
        ViewChangeModel model = new ViewChangeModel();
        model.setId(Node.getId());
        model.setCheckpointEvidence(Checkpoint.getCheckpointProofs());
        model.setNewView(Node.getView() + 1);
        model.setLatestCheckpoint(Checkpoint.getLatestCheckpoint());
        List<PreparedEvidence> evidence = Prepared.getEvidenceByHeight(Checkpoint.getLatestCheckpoint() + 1, Node.getBlockChainHeight());
        model.setPreparedEvidence(evidence);
        UDP_Sender.broadcast("<view-change>" + JSON.toJSONString(model));
    }

    public static void process(ViewChangeModel model){
        if (Node.isViewChangeSwitcher() && model.getNewView() == Node.getId() % Node.getNodeNum()){
            viewChangeProofs.add(model);
            count++;
            if (count >= Node.getThreshold())
                NewView.generate();
        }
    }

    public static ViewChangeEvidence getViewChangeProofs() {
        return viewChangeProofs;
    }
}
