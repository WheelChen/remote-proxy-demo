package me.wheelchen.proxy.remote.client;

import me.wheelchen.proxy.remote.MyRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Kelvin Chen
 * @date 2019-11-28 00:00:07
 */
public class MyRemoteClient {

    public void go() {
        try {
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");

            String s  = service.sayHello();

            System.out.println(s);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new MyRemoteClient().go();
    }
}
