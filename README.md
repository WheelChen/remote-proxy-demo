# remote proxy demo

## Run
> use `java.rmi.*`

- change directory to same level with /me

- Run rmiregistry in background:
```bash
rmiregistry &
```

- Run Remote Server
> must run after rmi registry running
> to register in rmi registry
```bash
javac me/wheelchen/proxy/remote/server/MyRemoteImpl.java
java  me.wheelchen.proxy.remote.server.MyRemoteImpl
```

- Run Remote Client 
```bash
javac me/wheelchen/proxy/remote/client/MyRemoteClient.java
java me.wheelchen.proxy.remote.client.MyRemoteClient
```

## Step 1. 制作远程接口

> 扩展java.rmi.Remote

> The <code>Remote</code> interface serves to **identify** interfaces whose methods may be invoked from a non-local virtual machine.
>
> Remote接口仅做标记使用，表明该方法支持远程调用。类似于`Serializable`标记可序列化一般。

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote {

    /**
     * say hello方法
     *
     * @throws RemoteException 远程调用异常
     * @return hello内容
     */
    public String sayHello() throws RemoteException;
}
```

Note:

- 方法中的返回值必须是**原语（Primitive）**或**可序列化对象（实现Serializable接口）**

  因为远程方法的变量必须被打包并通过网络传输，需要转化为字节流传输

- 所有方法必须加上`RemoteException`，是告知客户端，每次远程调用都是有风险的。



## Step 2. 制作远程实现

```java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
        return null;
    }
}
```

- 继承`UnicastRemoteObject`实现基本的远程服务方法
- 实现远程接口`MyRemote`

Note:

- `UnicastRemoteObject`构造器中抛出RemoteException异常，故必须在子类构造器中也抛出异常。

**注册服务**

```java
try {
  MyRemote service = new MyRemoteImpl();
  //服务命名并注册进入RMI registry
  Naming.rebind("RemoteHello", service);
} catch (RemoteException | MalformedURLException e) {
  e.printStackTrace();
}
```

## Step 3. 客服端调用

```java
try {
  MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
  String s  = service.sayHello();
  System.out.println(s);
} catch (NotBoundException | MalformedURLException | RemoteException e) {
  e.printStackTrace();
}
```


