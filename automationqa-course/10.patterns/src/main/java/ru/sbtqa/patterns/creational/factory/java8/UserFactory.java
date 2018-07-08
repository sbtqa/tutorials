package ru.sbtqa.patterns.creational.factory.java8;

import ru.sbtqa.patterns.creational.factory.java8.User.UserType;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * @author Alexey Rumyantsev
 */
public class UserFactory {
    private final static EnumMap<UserType, Supplier<User>> users =
            new EnumMap<>(UserType.class);

    static {
        users.put(UserType.NEWBIE, NewbieUser::new);
        users.put(UserType.PRIVILEGED, PrivilegedUser::new);
        users.put(UserType.AVERAGE, AverageUser::new);
    }

    public static User create(UserType type) {
        return users.get(type).get();
    }
}
