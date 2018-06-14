package model.node.consensusMessage;

import java.util.Set;

/**
 * Created by DSY on 2018/6/3.
 * 对应一个区块的证据信息
 */
public class CheckpointEvidence {
    private Set<CheckpointModel> evidence;

    public Set<CheckpointModel> getEvidence() {
        return evidence;
    }

    public void setEvidence(Set<CheckpointModel> evidence) {
        this.evidence = evidence;
    }
}
