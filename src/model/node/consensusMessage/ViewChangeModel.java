package model.node.consensusMessage;

import java.util.List;

/**
 * Created by DSY on 2018/5/10.
 */
public class ViewChangeModel {
    private int newView;

    private int latestCheckpoint;

    private CheckpointEvidence checkpointEvidence;

    private int id;

    private List<PreparedEvidence> preparedEvidence;

    public int getNewView() {
        return newView;
    }

    public void setNewView(int newView) {
        this.newView = newView;
    }

    public int getLatestCheckpoint() {
        return latestCheckpoint;
    }

    public void setLatestCheckpoint(int latestCheckpoint) {
        this.latestCheckpoint = latestCheckpoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PreparedEvidence> getPreparedEvidence() {
        return preparedEvidence;
    }

    public void setPreparedEvidence(List<PreparedEvidence> preparedEvidence) {
        this.preparedEvidence = preparedEvidence;
    }

    public CheckpointEvidence getCheckpointEvidence() {
        return checkpointEvidence;
    }

    public void setCheckpointEvidence(CheckpointEvidence checkpointEvidence) {
        this.checkpointEvidence = checkpointEvidence;
    }
}
