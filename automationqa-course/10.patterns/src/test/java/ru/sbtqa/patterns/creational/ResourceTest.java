package ru.sbtqa.patterns.creational;

import org.junit.Test;
import ru.sbtqa.patterns.creational.pool.Resource;
import ru.sbtqa.patterns.creational.pool.ResourceManager;

public class ResourceTest {

    @Test
    public void test() throws InterruptedException {
        ResourceManager manager = new ResourceManager(4);
        Resource resource = manager.getResource();

        resource.operation1();
        resource.operation2();

        manager.releaseResource(resource);
    }

    @Test
    public void resourceTest() throws InterruptedException {
        ResourceManager manager = new ResourceManager(4);

        manager.use(resource ->
                resource.operation1()
                        .operation2()
        );
    }
}
