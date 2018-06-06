package model.node.consensusMessage;

import model.node.consensusMessage.CheckpointEvidence;
import model.node.consensusMessage.PreparedEvidence;

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
