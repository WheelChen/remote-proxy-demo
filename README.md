# Remote proxy demo
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
