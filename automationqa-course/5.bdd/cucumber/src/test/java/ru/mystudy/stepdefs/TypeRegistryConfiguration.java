package ru.mystudy.stepdefs;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.cucumberexpressions.Transformer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableRowTransformer;
import ru.mystudy.pojo.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *  Для возможности передачи из фичи в код шага объектов пользовательского типа необходимо добавить в Реестр Типов
 *  соответствующий преобразователь.
 *
 *  Для этого в пакете, указанном в glue, необходимо создать класс, реализующий интерфейс TypeRegistryConfigurer,
 *  и через него добавить свой тип данных в реестр.
 *  Класс, реализующий интерфейс TypeRegistryConfigurer, в проекте должен быть один, иначе будет выброшено исключение.
 *
 */
public class TypeRegistryConfiguration implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return new Locale("ru");
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>(
                "localdate",
                "[0-9]{2}.[0-9]{2}.[0-9]{4}",
                LocalDate.class,
                (Transformer<LocalDate>) s -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    return LocalDate.parse(s, formatter);
                }
        ));
        typeRegistry.defineDataTableType(new DataTableType(
                User.class,
                (TableRowTransformer<User>) list -> {
                    User user = new User();
                    user.setFirstName(list.get(0));
                    user.setLastName(list.get(1));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    user.setBirthDay(LocalDate.parse(list.get(2), formatter));
                    return user;
                }
        ));
    }
}
