package model.node.consensusMessage;

import model.node.consensusMessage.PreparedEvidence;

/**
 * Created by DSY on 2018/6/3.
 */
public class SynchronizedModel {
    private int currentView;

    private int id;

    private PreparedEvidence preparedEvidence;

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

    public PreparedEvidence getPreparedEvidence() {
        return preparedEvidence;
    }

    public void setPreparedEvidence(PreparedEvidence preparedEvidence) {
        this.preparedEvidence = preparedEvidence;
    }

    @Override
    public String toString() {
        return "SynchronizedModel{" +
                "currentView=" + currentView +
                ", id=" + id +
                ", preparedEvidence=" + preparedEvidence +
                '}';
    }
}
