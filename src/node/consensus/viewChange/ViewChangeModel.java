package node.consensus.viewChange;

import node.consensus.checkpoint.CheckpointEvidence;
import node.consensus.mainStream.prepared.PreparedEvidence;

/**
 * Created by DSY on 2018/5/10.
 */
public class ViewChangeModel {
    private int newView;

    private int LatestCheckpoint;

    private CheckpointEvidence CheckpointEvidence;

    private int id;

    private PreparedEvidence preparedEvidence;
}
