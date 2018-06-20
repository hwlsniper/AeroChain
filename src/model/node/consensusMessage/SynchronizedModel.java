package model.node.consensusMessage;

import model.block.Block;
import model.node.consensusMessage.PreparedEvidence;

import java.util.List;

/**
 * Created by DSY on 2018/6/3.
 */
public class SynchronizedModel {
    private int currentView;

    private int id;

    private List<PreparedEvidence> preparedEvidence;

    private CheckpointEvidence checkpointEvidence;

    private List<Block> blockChain;

    public CheckpointEvidence getCheckpointEvidence() {
        return checkpointEvidence;
    }

    public void setCheckpointEvidence(CheckpointEvidence checkpointEvidence) {
        this.checkpointEvidence = checkpointEvidence;
    }

    public List<Block> getBlockChain() {
        return blockChain;
    }

    public void setBlockChain(List<Block> blockChain) {
        this.blockChain = blockChain;
    }

    public int getCurrentView() {
        return currentView;
    }

    public void setCurrentView(int currentView) {
        this.currentView = currentView;
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
}
