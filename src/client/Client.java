package client;

import com.alibaba.fastjson.JSON;
import constant.Constant;
import node.communication.Sender;
import model.record.Record;
import util.Log;

import java.io.*;

/**
 * Created by DSY on 2018/5/10.
 * 理论上应该把区块链客户端从区块链应用中剥离，单独部署，但是在原型系统中为了简单，把客户端作为
 * 区块链节点的一部分功能实现。
 *
 * 客户端功能：（1）接受用户从雷达的输入，将数据在区块链中存储
 */
public class Client{
    static void send(Record record) {
        Sender.broadcast("<record>" + JSON.toJSONString(record));
    }
}
