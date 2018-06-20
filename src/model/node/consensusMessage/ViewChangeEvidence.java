package model.node.consensusMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSY on 2018/6/21.
 */
public class ViewChangeEvidence {
    private List<ViewChangeModel> evidence = new ArrayList<>();

    public List<ViewChangeModel> getEvidence() {
        return evidence;
    }

    public void add(ViewChangeModel evidence) {
        this.evidence.add(evidence);
    }
}
