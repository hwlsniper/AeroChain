package node.consensus.viewChange;

import model.node.Node;
import model.node.consensusMessage.NewViewModel;
import model.node.consensusMessage.ViewChangeModel;
import node.consensus.synchronize.Synchrony;

import java.util.List;

/**
 * 实现视图切换过程中，新的主节点广播消息的行为
 */
public class NewView {
    public static void generate(){
        NewViewModel model = new NewViewModel();
        List<ViewChangeModel> evidence = ViewChange.getViewChangeProofs();
        model.setEvidence(evidence);
        model.setHeight(validHeight(evidence));
        model.setHeight(Node.getId());
        model.setView(Node.getView());
    }

    public static void process(NewViewModel model){
        if (validHeight(model.getEvidence()) != model.getHeight()) return;
        Node.setView(model.getView());
        Node.setPrimary(model.getId());
        if (Node.getBlockChainHeight() < model.getHeight()) Synchrony.viewChangeSynchrony(model.getEvidence());
    }

    private static int validHeight(List<ViewChangeModel> evidence){
        return 0;
    }
}
