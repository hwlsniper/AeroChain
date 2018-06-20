package model.node;

import model.annotation.MulThreadShareData;
import model.block.Block;
import util.hash.Hash;
import java.util.*;

/**
 * Created by DSY on 2018/3/15.
 * 区块链节点模型
 * 第一个区块的高度是1
 * 当前版本不考虑节点动态变化的功能
 */
public class Node {
    /**
     * 判断是否进入同步状态，如果进入同步状态，那么不再发送prepare消息，
     * 但是正常接受prePrepare和prepare消息
     * */
    @MulThreadShareData
    private static boolean synSwitcher;

    /**
     * 判断是否进入视图切换状态，如果进入视图切换状态，则只接受跟视图切换相关的消息
     */
    @MulThreadShareData
    private static boolean viewChangeSwitcher;

    @MulThreadShareData
    private static List<Block> blockChain = new ArrayList<>();

    /**
     * 用于存储区块同步过程中，生成的临时区块
     */
    @MulThreadShareData
    private static List<Block> tmpBlocks = new ArrayList<>();

    private static final int id = 1;

    @MulThreadShareData
    private static int primary = 1;

    @MulThreadShareData
    private static int view = 1;

    private static final int nodeNum = 4;

    private static final int faultyNodeNum = 1;

    private static int threshold = Integer.MAX_VALUE;

    private static int crashThreshold = Integer.MAX_VALUE;

    public static void threshold(){
        int n = Node.getNodeNum();
        int f = faultyNodeNum;
        threshold = (int)Math.ceil((double)(n-f)/2) + f + ((n - f ) % 2 == 0 ? 1 : 0);
        crashThreshold = 2 * f + 1;
    }

    public static boolean isPrimary(){
        return (id == view % nodeNum);
    }

    public synchronized static String getLatestHash(){
        if (blockChain.size() == 0) return "non";
        return Hash.hash(blockChain.get(blockChain.size() - 1).toString());
    }

    public synchronized static int getBlockChainHeight(){
        return blockChain.size();
    }

    public synchronized static void addBlock(Block block){
        blockChain.add(block);
    }

    public static synchronized List<Block> getTmpBlocks() {
        return tmpBlocks;
    }

    public static synchronized void addTmpBlock(Block block){
        tmpBlocks.add(block);
    }

    public static int getThreshold() {
        return threshold;
    }

    public static synchronized boolean isSynSwitcher() {
        return synSwitcher;
    }

    public static synchronized void setSynSwitcher(boolean synSwitcher) {
        Node.synSwitcher = synSwitcher;
    }

    public static synchronized boolean isViewChangeSwitcher() {
        return viewChangeSwitcher;
    }

    public static synchronized void setViewChangeSwitcher(boolean viewChangeSwitcher) {
        Node.viewChangeSwitcher = viewChangeSwitcher;
    }

    public static synchronized int getPrimary() {
        return primary;
    }

    public static synchronized void setPrimary(int primary) {
        Node.primary = primary;
    }

    public static synchronized int getView() {
        return view;
    }

    public static synchronized void setView(int view) {
        Node.view = view;
    }

    public static int getNodeNum() {
        return nodeNum;
    }

    public static int getId() {
        return id;
    }

    public synchronized static Block getBlock(int height) {
        return blockChain.get(height - 1);
    }

    public static int getCrashThreshold() {
        return crashThreshold;
    }
}
