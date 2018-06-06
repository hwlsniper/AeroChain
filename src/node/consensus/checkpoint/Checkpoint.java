package node.consensus.checkpoint;

import model.node.consensusMessage.CheckpointModel;
import node.communication.Sender;
import model.node.Node;
import util.Log;
import util.hash.Hash;

import java.util.List;

public class Checkpoint {
    public synchronized static void generate(){

    }

    public synchronized static void process(CheckpointModel model){

    }

    private static void deleteOldData(){}

    public static List<String> getCheckpointProofs(){
        return Log.getLogs("checkpoint");
    }

    public static boolean isValidCheckpoint(int checkpoint , String proof){
        return true;
    }
}
