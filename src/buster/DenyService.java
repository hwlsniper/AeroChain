package buster;

import main.Main;

/**
 * Created by DSY on 2018/6/24.
 * 模拟恶意节点下线的攻击方式
 */
public class DenyService {
    public static void main(String[] args) {
        Main.shutdown();
    }
}
