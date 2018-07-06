package ru.sbtqa.patterns.creational.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * @author Alexey Rumyantsev
 */
public class ResourceManager {

    private static ResourceManager instance = new ResourceManager(4);

    private BlockingQueue<Resource> queue;

    private ResourceManager(int poolSize) {
        queue = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++)
            queue.add(new Resource());
    }

    public static ResourceManager getInstance() {
        return instance;
    }

    public Resource getResource() {
        return queue.remove();
    }

    public void releaseResource(Resource resource) {
        resource.close();
        queue.add(resource);
    }

    public void use(Consumer<Resource> task) {
        Resource resource = getResource();
        task.accept(resource);
        releaseResource(resource);
    }
}
