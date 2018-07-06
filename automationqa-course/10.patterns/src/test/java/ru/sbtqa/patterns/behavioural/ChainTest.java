package ru.sbtqa.patterns.behavioural;

import org.junit.Assert;
import org.junit.Test;
import ru.sbtqa.patterns.behavioural.chain.Request;
import ru.sbtqa.patterns.behavioural.chain.RequestHandlers;
import ru.sbtqa.patterns.behavioural.chain.RequestType;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ChainTest {

    private UnaryOperator<Function<Request, Request>> handledChecker =
            (f) -> (r) -> {
                if (r.isHandled()) {
                    return r;
                }
                return f.apply(r);
            };

    private Function<Request, Request> chainOfHandlers =
            Stream.<Function<Request, Request>>of(
                    handledChecker.apply(RequestHandlers::handleAudio),
                    handledChecker.apply(RequestHandlers::handleImage),
                    handledChecker.apply(RequestHandlers::handleExecutable))
                    .reduce(Function.identity(), Function::andThen);

    @Test
    public void chainTest() {
        Request request = chainOfHandlers.apply(new Request(RequestType.IMAGE));
        Assert.assertTrue(request.isHandled());
    }
}
