package ru.sbtqa.patterns.structural;

import org.junit.Test;
import ru.sbtqa.patterns.structural.bridge.DBSource;
import ru.sbtqa.patterns.structural.bridge.RequestService;
import ru.sbtqa.patterns.structural.bridge.Service;

public class BridgeTest {
    @Test
    public void test(){
        Service service = new RequestService(new DBSource());
        service.run();
    }
}
