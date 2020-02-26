package frc.robot;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Keyboard {

    // public DatagramSocket socket;
    // public DatagramPacket packet;
    // public static String s;

    // public Keyboard() {
    //     System.out.println("poopie");
    //     try {
    //         socket = new DatagramSocket(null);
    //         socket.bind(new InetSocketAddress(5800));
    //         packet = new DatagramPacket(new byte[1500], 1500);
    //     } 
    //     catch (SocketException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }
    
    // public static String getKeyPress() {
    //     return s;
    // }

    // public void receivePackets() {
    //     try {
    //         socket.receive(packet);
    //         s = new String(packet.getData(), StandardCharsets.UTF_8);
    //     } 
    //     catch (SocketException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    //     catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    //     receivePackets();
    // }
}