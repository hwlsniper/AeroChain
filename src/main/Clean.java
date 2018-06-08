package main;

import node.communication.Receiver;

/**
 * 用于处理程序退出时的相关需要
 */
class Clean {
    static void cleanUp(){
        Receiver.clean();
    }
}
