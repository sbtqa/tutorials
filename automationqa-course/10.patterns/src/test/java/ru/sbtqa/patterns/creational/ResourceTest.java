package ru.sbtqa.patterns.creational;

import org.junit.jupiter.api.Test;
import ru.sbtqa.patterns.creational.pool.Resource;
import ru.sbtqa.patterns.creational.pool.ResourceManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ResourceTest {

    @Test
    void resourceTest() {
        ResourceManager manager = ResourceManager.getInstance();
        Resource resource = manager.getResource();

        resource.operation1();
        resource.operation2();

        manager.releaseResource(resource);
    }

    @Test
    void resourceTestLambda() {
        ResourceManager.getInstance().use(resource ->
                resource.operation1()
                        .operation2()
        );
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(this::resourceTest);
    }
}
