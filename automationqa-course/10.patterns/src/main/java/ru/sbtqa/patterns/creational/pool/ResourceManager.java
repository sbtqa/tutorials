package ru.sbtqa.patterns.creational.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

/**
 * @author Alexey Rumyantsev
 */
public class ResourceManager {

    private static BlockingQueue<Resource> queue;

    public ResourceManager(int poolSize) {
        queue = new LinkedBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++)
            queue.add(new Resource());
    }

    public Resource getResource() throws InterruptedException {
        return queue.take();
    }

    public void releaseResource(Resource resource) throws InterruptedException {
        resource.close();
        queue.put(resource);
    }

    public void use(Consumer<Resource> task) throws InterruptedException {
        Resource resource = getResource();
        task.accept(resource);
        releaseResource(resource);
    }
}
