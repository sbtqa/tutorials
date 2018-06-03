package ru.sbtqa.patterns.structural;

import org.junit.jupiter.api.Test;
import ru.sbtqa.patterns.structural.proxy.MockServer;
import ru.sbtqa.patterns.structural.proxy.Server;

public class ProxyTest {
    @Test
    public void test(){
        Server server = new MockServer();
        System.out.println(server.sendMessage("Hello world"));
    }
}
