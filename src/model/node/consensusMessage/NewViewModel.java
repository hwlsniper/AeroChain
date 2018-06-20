package model.node.consensusMessage;

import java.util.List;

/**
 * newView通信消息的模型类
 */
public class NewViewModel {
    private int id;

    private int height;

    private int view;

    private ViewChangeEvidence evidence;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public ViewChangeEvidence getEvidence() {
        return evidence;
    }

    public void setEvidence(ViewChangeEvidence evidence) {
        this.evidence = evidence;
    }
}
