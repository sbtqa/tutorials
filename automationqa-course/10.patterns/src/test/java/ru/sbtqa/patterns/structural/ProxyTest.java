package ru.sbtqa.patterns.structural;

import org.junit.jupiter.api.Test;
import ru.sbtqa.patterns.structural.proxy.RealServer;
import ru.sbtqa.patterns.structural.proxy.SecuredServer;
import ru.sbtqa.patterns.structural.proxy.Server;
import ru.sbtqa.patterns.structural.proxy.ServerStub;

class ProxyTest {
    @Test
    void test(){
        Server server = new ServerStub();//Proxy class
        System.out.println(server.sendMessage("Hello world"));
    }
}
