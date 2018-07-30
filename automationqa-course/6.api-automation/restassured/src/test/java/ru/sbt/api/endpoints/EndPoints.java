package ru.sbt.api.endpoints;

/**
 * Хорошей практикой будет вынести все эндпойты тестируемого приложения в во внешнее хранилище.
 * Таким образом, во первых уменьшается вероятность допустить ошибку при наборе адреса endpoint'а в каждом запросе вручную,
 * во вторых при изменении адреса endpoint'а, его нужно будет изменить только в одном месте.
 */
public final class EndPoints {
    public final static String manufacture = "manufacturers/{id}";
    public final static String manufactures = "manufacturers";
    public final static String models = "models";

    private EndPoints() {
        throw new IllegalStateException();
    }
}
