package node.consensus.viewChange;

import com.alibaba.fastjson.JSON;
import model.node.Node;
import model.node.consensusMessage.NewViewModel;
import model.node.consensusMessage.ViewChangeEvidence;
import node.communication.tcp.Tcp_Sender;
import node.consensus.synchronize.ViewChangeSynchrony;

/**
 * 实现视图切换过程中，新的主节点广播消息的行为
 */
public class NewView {
    public static void generate(){
        NewViewModel model = new NewViewModel();
        ViewChangeEvidence evidence = ViewChange.getViewChangeProofs();
        model.setEvidence(evidence);
        model.setHeight(validHeight(evidence));
        model.setHeight(Node.getId());
        model.setView(Node.getView());
        model.setId(Node.getId());
        Tcp_Sender.broadcastRecord("<new-view>" + JSON.toJSONString(model));
    }

    public static void process(NewViewModel model){
        if (validHeight(model.getEvidence()) != model.getHeight()) return;
        Node.setView(model.getView());
        Node.setPrimary(model.getId());
        if (Node.getBlockChainHeight() < model.getHeight()) ViewChangeSynchrony.synchrony(model.getEvidence());
    }

    private static int validHeight(ViewChangeEvidence evidence){
        return 0;
    }
}
