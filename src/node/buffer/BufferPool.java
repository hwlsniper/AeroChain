package node.buffer;

import model.annotation.MulThreadShareData;
import model.record.Record;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点存储未达成共识数据的数据缓冲池。
 * 我们假设不保存数据的输入顺序，因此使用HashSet存储顺序
 */
public class BufferPool {
    @MulThreadShareData
    private static Set<Record> pool = ConcurrentHashMap.newKeySet();

    public static List<Record> generateBlockRecord(){
        List<Record> result = new ArrayList<>(pool);
        pool = new HashSet<>();
        return result;
    }

    public static void add(Record record){
        pool.add(record);
    }

    public static void remove(Record record){pool.remove(record);}

    public static boolean isContain(Record record){
        return pool.contains(record);
    }
}
