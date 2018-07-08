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
            (function) -> (request) -> {
                if (request.isHandled())
                    return request;
                return function.apply(request);
            };

    private Function<Request, Request> chain =
            Stream.<Function<Request, Request>>of(
                    handledChecker.apply(RequestHandlers::handleXML),
                    handledChecker.apply(RequestHandlers::handleJSON),
                    handledChecker.apply(RequestHandlers::handleBinary)
            ).reduce(Function.identity(), Function::andThen);

    @Test
    public void chainTest() {
        Request request = chain.apply(new Request(RequestType.JSON));
        Assert.assertTrue(request.isHandled());
    }
}
