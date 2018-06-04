package node.consensus.synchronize;

/**
 * Created by DSY on 2018/5/10.
 * 代表发起同步消息的对象
 */
public class SynchronyModel {
    private int id;

    private int height;

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

    @Override
    public String toString() {
        return "SynchronyModel{" +
                "id=" + id +
                ", height=" + height +
                '}';
    }
}
