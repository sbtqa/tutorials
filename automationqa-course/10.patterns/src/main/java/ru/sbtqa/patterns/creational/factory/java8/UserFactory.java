package ru.sbtqa.patterns.creational.factory.java8;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * @author Alexey Rumyantsev
 */
public class UserFactory {
    private final static EnumMap<User.UserType, Supplier<User>> users =
            new EnumMap<>(User.UserType.class);

    static {
        users.put(User.UserType.NEWBIE, NewbieUser::new);
        users.put(User.UserType.PRIVILEGED, PrivilegedUser::new);
        users.put(User.UserType.AVERAGE, AverageUser::new);
    }

    public static User create(User.UserType type) {
        return users.get(type).get();
    }
}
