package me.wheelchen.proxy.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Kelvin Chen
 * @date 2019-11-26 22:54:14
 */
public interface MyRemote extends Remote {

    /**
     * say hello方法
     *
     * @return hello内容
     * @throws RemoteException 远程调用异常
     */
    String sayHello() throws RemoteException;

}
