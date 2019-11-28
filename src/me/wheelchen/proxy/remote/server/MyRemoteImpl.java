package me.wheelchen.proxy.remote.server;

import me.wheelchen.proxy.remote.MyRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Kelvin Chen
 * @date 2019-11-26 22:58:45
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    /**
     * `UnicastRemoteObject`构造器中抛出RemoteException异常
     * 故必须在子类构造器中也抛出异常
     *
     * @throws RemoteException 远程调用异常
     */
    public MyRemoteImpl() throws RemoteException {
    }

    /**
     * say hello方法
     *
     * @return hello内容
     * @throws RemoteException 远程调用异常
     */
    @Override
    public String sayHello() throws RemoteException {
        return "hello";
    }

    public static void main(String[] args) {
        // write your code here
        try {
            MyRemote service = new MyRemoteImpl();
            //服务命名并注册
            Naming.rebind("RemoteHello", service);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
