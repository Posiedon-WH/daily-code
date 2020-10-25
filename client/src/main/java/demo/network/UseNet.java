package demo.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Author: Posiedon.wh
 * @Date: 2020/10/25 20:23
 */
public class UseNet {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()){
            System.out.println(networkInterfaces.nextElement());
            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()){
                System.out.println("child： "+inetAddresses.nextElement().getAddress());
            }
        }

    }
}
