package client;

import constant.Constant;
import main.Main;
import model.record.Record;
import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver implements Runnable{
    private static MulticastSocket socket;

    private static PrintWriter printWriter;

    public static void init(){
        try {
            socket = new MulticastSocket(Constant.RADAR_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InetAddress address = InetAddress.getByName(Constant.MULTICAST_IP);
            socket.joinGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Constant.LOG_BASE_PATH + "radar.txt", true), "UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (Main.running){
            byte[] buf = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buf , buf.length);
            try {
                socket.receive(packet);
            }catch (IOException e){
                e.printStackTrace();
            }
            String receive = new String(packet.getData(), 0, packet.getLength());
            printWriter.println(receive);
            Client.send(new Record(receive));
        }
    }

    public static void clean(){
        printWriter.close();
    }
}
