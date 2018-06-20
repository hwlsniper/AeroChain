package node.consensus.synchronize;

import model.node.consensusMessage.SynchronyModel;
import model.node.consensusMessage.ViewChangeModel;
import node.communication.udp.UDP_Sender;
import model.node.Node;

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

    }

    /**
     * 根据新的主节点提出的区块证据信息，更新本地区块链
     * @param evidence 各个区块的证据信息
     */
    public static void viewChangeSynchrony(List<ViewChangeModel> evidence){

    }
}
