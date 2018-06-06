package node.communication;

import com.alibaba.fastjson.JSON;
import model.node.Node;
import model.node.consensusMessage.*;
import node.consensus.checkpoint.Checkpoint;
import node.consensus.mainStream.prePrepare.PrePrepare;
import node.consensus.mainStream.prepare.Prepare;
import node.buffer.BufferPool;
import node.consensus.synchronize.Synchronized;
import node.consensus.synchronize.Synchrony;
import node.consensus.viewChange.NewView;
import node.consensus.viewChange.ViewChange;
import model.record.Record;

/**
 * Created by DSY on 2018/3/15.
 * 根据区块链节点收到的消息执行相应的操作
 */
public class Resolver {
    static void resolve(String data){
        if (!Node.isViewChangeSwitcher()){
            if (data.startsWith("<record>")){
                String tmp = data.replace("<record>", "");
                BufferPool.add(JSON.parseObject(tmp, Record.class));
            }
            else if (data.startsWith("<pre-prepare>")) {
                String tmp = data.replace("<pre-prepare>", "");
                PrePrepareModel model = JSON.parseObject(tmp, PrePrepareModel.class);
                PrePrepare.process(model);
            }
            else if (data.startsWith("<prepare>")){
                String tmp = data.replace("<prepare>", "");
                PrepareModel model = JSON.parseObject(tmp, PrepareModel.class);
                Prepare.process(model);
            }
            else if (data.startsWith("<checkpoint>")) {
                String tmp = data.replace("<checkpoint>", "");
                CheckpointModel model = JSON.parseObject(tmp, CheckpointModel.class);
                Checkpoint.process(model);
            } else if (data.startsWith("<synchrony>")) {
                String tmp = data.replace("<synchrony>", "");
                SynchronyModel model = JSON.parseObject(tmp, SynchronyModel.class);
                Synchrony.process(model);
            } else if (data.startsWith("<synchronized>")) {
                String tmp = data.replace("<synchronized>", "");
                SynchronizedModel model = JSON.parseObject(tmp, SynchronizedModel.class);
                Synchronized.process(model);
            }
        }
        if (data.startsWith("<view-change>")){
            String tmp = data.replace("<view-change>", "");
            ViewChangeModel model = JSON.parseObject(tmp, ViewChangeModel.class);
            ViewChange.process(model);
        }
        if (data.startsWith("<new-view>")){
            String tmp = data.replace("<new-view>", "");
            NewViewModel model = JSON.parseObject(tmp, NewViewModel.class);
            NewView.process(model);
        }
    }
}
