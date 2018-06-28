package client;

import com.alibaba.fastjson.JSON;
import constant.Constant;
import main.Main;
import model.annotation.MulThreadShareData;
import model.block.Block;
import node.communication.udp.UDP_Sender;
import model.record.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by DSY on 2018/5/10.
 * 理论上应该把区块链客户端从区块链应用中剥离，单独部署，但是在原型系统中为了简单，把客户端作为
 * 区块链节点的一部分功能实现。
 *
 * 客户端功能：（1）接受用户从雷达的输入，将数据在区块链中存储
 * （2）确保每条数据都已经被区块链确认，只在最新的两个区块中查找（如果两个区块中没有，则认为数据已经丢失）
 */
public class Client implements Runnable{
    @MulThreadShareData
    private static Set<Record> confirming = ConcurrentHashMap.newKeySet();

    private static List<Record> warning = new ArrayList<>();

    private static List<Record> lost = new ArrayList<>();

    static void send(Record record) {
        confirming.add(record);
        UDP_Sender.broadcast("<record>" + JSON.toJSONString(record));
    }

    @Override
    public void run() {
        while (Main.isRunning()){
            try {
                TimeUnit.SECONDS.sleep(Constant.HALF_BLOCK_GAP * 2);
                List<List<Block>> blockchains = getBlockChains();
                for (Record record : confirming){
                    if (onChain(record)){
                        confirming.remove(record);
                    }
                }
                for (Record record : warning){
                    if (!onChain(record)){
                        lost.add(record);
                    }
                }
                warning = new ArrayList<>(confirming);
                for (Record record : lost){
                    Client.send(record);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean onChain(Record record){

    }

    private List<List<Block>> getBlockChains(){

    }
}