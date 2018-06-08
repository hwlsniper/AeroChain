package model.node.consensusMessage;

import java.util.Set;

/**
 * Created by DSY on 2018/6/3.
 * 每个区块对应的证据信息：
 * 一条prePrepare消息和最少R条与之对应的prepare消息
 */
public class PreparedEvidence {
    private PrePrepareModel prePrepare;

    private Set<PrepareModel> prepare;

    private int height;

    public PreparedEvidence(PrePrepareModel prePrepare, Set<PrepareModel> prepare, int height) {
        this.prePrepare = prePrepare;
        this.prepare = prepare;
        this.height = height;
    }

    public PrePrepareModel getPrePrepare() {
        return prePrepare;
    }

    public Set<PrepareModel> getPrepare() {
        return prepare;
    }

    public int getHeight() {
        return height;
    }
}
