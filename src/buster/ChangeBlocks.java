package buster;

import com.alibaba.fastjson.JSON;
import constant.Constant;
import model.block.Block;
import model.record.Record;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DSY on 2018/6/13.
 * 模拟一个坏人
 */
public class ChangeBlocks {
    public static void main(String[] args) throws IOException {
        List<Block> blockChain = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(Constant.LOG_BASE_PATH + "block.txt"),"UTF-8"));
            String blockJson;
            while ((blockJson = bufferedReader.readLine()) != null){
                Block block = JSON.parseObject(blockJson, Block.class);
                blockChain.add(block);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("按1查看区块，按2修改区块");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = bufferedReader.readLine()) != null){
            switch (Integer.valueOf(input)){
                case 1:
                    for (Block tmp : blockChain)
                        System.out.println(tmp.toString());
                    break;
                case 2:
                    System.out.println("你要修改第几个区块？");
                    input = bufferedReader.readLine();
                    int num = Integer.valueOf(input);
                    Block previous = blockChain.get(num - 1);
                    System.out.println("请输入修改之后的数据");
                    input = bufferedReader.readLine();
                    previous.setData(Collections.singletonList(new Record(input)));
                    blockChain.set(num - 1, previous);
            }
        }
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.LOG_BASE_PATH + "block.txt", true), "UTF-8")));
        for (Block tmp : blockChain){
            printWriter.println(JSON.toJSONString(tmp));
        }
        printWriter.close();
        bufferedReader.close();
    }
}
