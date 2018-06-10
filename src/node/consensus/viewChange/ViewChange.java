package node.consensus.viewChange;

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
    private static Map<String , Integer> map = new HashMap<>();

    private static List<String> viewChangeProofs = new ArrayList<>();

    public static void generate(){
        System.out.println("view change");
        System.exit(0);
    }

    public static void process(ViewChangeModel model){
    }

}
