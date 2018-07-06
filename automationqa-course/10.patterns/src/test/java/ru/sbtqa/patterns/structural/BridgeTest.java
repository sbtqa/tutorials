package ru.sbtqa.patterns.structural;

import org.junit.Test;
import ru.sbtqa.patterns.structural.bridge.LoginService;
import ru.sbtqa.patterns.structural.bridge.data.DBSource;
import ru.sbtqa.patterns.structural.bridge.RequestService;
import ru.sbtqa.patterns.structural.bridge.Service;
import ru.sbtqa.patterns.structural.bridge.data.FileDataSource;

public class BridgeTest {
    @Test
    public void test(){
        Service service = new RequestService(new FileDataSource("file.txt"));
        service.run();
    }
}
