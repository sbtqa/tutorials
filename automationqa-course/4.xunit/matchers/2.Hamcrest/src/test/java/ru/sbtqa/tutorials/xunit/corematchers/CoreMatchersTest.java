package ru.sbtqa.tutorials.xunit.corematchers;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Примеры использования стандартных матчеров библиотеки Hamcrest
 */
class CoreMatchersTest {

    private String efsName = "ЕФС - Единая Фронтальная Система";

    /*
     * ====================
     * ЛОГИЧЕСКИЕ МАТЧЕРЫ
     * ====================
     */

    /**
     * efsName - объект, который проверяем. allOf - это логический матчер библиотеки Hamcrest,
     * который принимает цепочку матчеров и ждет, что каждый будет выполнен успешно
     */
    @Test
    void shouldWorkHamcrestMatcherAllOf() {
        assertThat(efsName, allOf(startsWith("ЕФС"), containsString("Фронтальная")));
    }

    /**
     * anyOf - это логический матчер библиотеки Hamcrest,
     * который принимает цепочку матчеров и ждет, что один из них выполнится
     */
    @Test
    void shouldWorkHamcrestMatcherAnyOf() {
        assertThat(efsName, anyOf(startsWith("ЕФС"), containsString("Троль")));
    }

    /**
     * Использование матчера both и and для создания цепочек матчеров.
     * Так же в цепочку передаем спецальные матчеры startsWith и sameInstance. both - ожидает оба успешных условия
     */
    @Test
    void shouldWorkHamcrestMatcherBoth() {
        assertThat(efsName, both(startsWith("ЕФС")).and(instanceOf(String.class)).and(startsWith("Е")));
    }

    /**
     * Использование логического матчера not в комбинации с startsWith.
     * Получается более читабельный вид
     */
    @Test
    void shouldWorkHamcrestMatcherNot() {
        assertThat(efsName, not(startsWith("ППРБ")));
    }

    /**
     * Использование логического матчера either в комбинациях с другими матчерами
     */
    @Test
    void shouldWorkHamcrestMatcherEither() {
        assertThat(efsName, either(not(equalTo(""))).or(is(equalToIgnoringCase(""))));
    }


    /*
     * ======================
     * СИНТАКСИЧЕСКИЕ МАТЧЕРЫ
     * ======================
     */

    /**
     * Использование матчера is, который влияет только на читабельность кода.ё
     * Hamcrest strives to make your tests as readable as possible.
     * For example, the is matcher is a wrapper that doesn't add any extra behavior to the underlying matcher.
     * <p>
     * Все три утверждения эквивалентны - см. ниже
     */
    @Test
    void shouldWorkHamcrestMatcherIs() {
        assertThat("ППРБ", equalTo("ППРБ"));
        assertThat("ППРБ", is(equalTo("ППРБ")));
        assertThat("ППРБ", is(("ППРБ")));
    }

    /**
     * Используем матчер anything(), который всегда отрабатывает успешно
     */
    @Test
    void shouldWorkHamcrestMatcherAnyThing() {
        assertThat(efsName, anything());
    }


    /* =====================================
     * МАТЧЕРЫ ПРОВЕРКИ СВОЙСТВ POJO-КЛАССОВ
     * =====================================
     *
     * Рекомендуемая библиотека, которая расширяет возможности работы с Beans:
     * github.com/sandromancuso/bean-property-matcher
     */

    /**
     * Проверяем свойство класса {@link PojoBean}
     */
    @Test
    void shouldWorkHamcrestHasProperty() {
        PojoBean pojoBean = new PojoBean("Artem", 29);
        assertThat(pojoBean, allOf(
                hasProperty("name", equalTo("Artem")),
                hasProperty("age")));
    }

    /**
     * Проверяем свойства одного объекта идентичны другому объекту
     */
    @Test
    void shouldWorkSamePropertyValuesAs() {
        PojoBean pojoBean1 = new PojoBean("Artem", 29);
        PojoBean pojoBean2 = new PojoBean("Artem", 29);
        assertThat(pojoBean1, samePropertyValuesAs(pojoBean2));
    }


    /* =====================================
     * МАТЧЕРЫ ДЛЯ РАБОТЫ С ЧИСЛАМИ
     * =====================================
     */

    /**
     * Матчер closeTo - используем, чтобы проверить равно ли значение "200.24" числу 202, в пределах диапозона +/- 10.
     * В пределах диапозона плюс, минус 10.
     */
    @Test
    void shouldWorkCloseTo() {
        assertThat(200.24, is(closeTo(202, 10)));
    }

    /**
     * Матчер greaterThan, проверяем, что 100 больше, чем 18
     */
    @Test
    void shouldWorkGreaterThan() {
        assertThat(100, greaterThan(18));
    }

    /**
     * Матчер greaterThanOrEqualTo проверяет, что проверяемое значение больше 18.
     * В текущем примере работаем со списком - используем матчер everyItem для обработки каждого значения списка
     */
    @Test
    void shouldWorkEveryItemInListGreaterThanOrEqualToNumber() {
        IntStream ages = IntStream.of(21, 25, 30, 18);
        assertThat(ages.boxed().collect(toList()), everyItem(greaterThanOrEqualTo(18)));
    }


    /* =====================================
     * МАТЧЕРЫ ДЛЯ РАБОТЫ С КОЛЛЕКЦИЯМИ
     * =====================================
     */

    /**
     * Матчер hasItem проверяет, что в проверяемой коллекции содержится требуемый элемент.
     * Так же в данном примере используется синтаксический матчер not
     */
    @Test
    void singleElementShouldBeInCollection() {
        List<String> collection = asList("Артем", "Ольга", "Анатолий");
        assertThat("Коллекция не содержит требуемое значение", collection, hasItem("Артем"));
        assertThat("Коллекция содержит требуемое значение", collection, not(hasItem("Дмитрий")));
    }

    /**
     * Матчер hasItems проверяет, что в проверяемой коллекции содержятся требуемые элементы.
     */
    @Test
    void multipleElementShouldBeInCollection() {
        List<String> collection = asList("Артем", "Ольга", "Анатолий");
        assertThat("Коллекция не содержит требуемое значение", collection,
                hasItems("Анатолий", "Ольга"));
    }

    /**
     * Матчер contains проверяет, что в проверяемой коллекции содержятся требуемые элементы в заданном порядке
     */
    @Test
    void multipleElementWithStrictOrder() {
        List<String> collection = asList("Дмитрий", "Роман", "Давлет");
        assertThat(collection, contains("Дмитрий", "Роман", "Давлет"));
    }

    /**
     * Матчер containsInAnyOrder проверяет, что в проверяемой коллекции содержатся требуемые элементы.
     * Требуются, чтобы присутствовали все элементы
     */
    @Test
    void multipleElementWithAnyOrderButAll() {
        List<String> collection = asList("Дмитрий", "Роман", "Давлет");
        assertThat(collection, containsInAnyOrder("Роман", "Дмитрий"));
    }

    /**
     * Матчер empty проверяет, что в проверяемой коллекции нет элементов.
     */
    @Test
    void collectionShouldBeEmpty() {
        List<String> collection = Collections.emptyList();
        assertThat("Коллекция не пустая", collection, empty());
    }

    /**
     * Матчер hasSize проверяет, что в проверяемой коллекции содержится определенное число элементов
     */
    @Test
    void listWithSpecialElementShouldHaveSpecialSize() {
        List<String> collection = asList("Allure", "Kotlin", "Appium");
        assertThat(collection, hasSize(3));
    }

    /**
     * Синтаксический матчер everyItem перебирает каждый элемент коллекции и выполняет заданную проверку.
     * Матчер greaterThan - сравнивает элемент с заданным числом.
     */
    @Test
    void everyElementShouldBeGreaterSpecialNumber() {
        List<Integer> collection = asList(150, 201, 11, 5);
        assertThat(collection, everyItem(greaterThan(10)));
    }

    /**
     * Матчер hasKey проверяет, что в map есть элемент с заданным ключем
     */
    @Test
    void mapShouldHaveSpecialKey() {
        Map<String, String> map = new HashMap<>();
        map.put("Артем", "30");
        map.put("Ольга", "25");
        map.put("Анатолий", "33");
        assertThat(map, hasKey("Ольга"));
    }

    /**
     * Матчер hasValue проверяет, что в map есть элемент с заданным значением
     */
    @Test
    void mapShouldHaveSpecialValue() {
        Map<String, String> map = new HashMap<>();
        map.put("Kotlin", "1.2.60");
        assertThat(map, hasValue("1.2.60"));
    }

    /**
     * Матчер hasEntry проверяет, что в map есть заданный элемент с ключем и значением
     */
    @Test
    void mapShouldHaveSpecialEntry() {
        Map<String, String> map = new HashMap<>();
        map.put("Artem", "Sokovets");
        assertThat(map, hasEntry("Artem", "Sokovets"));
    }


    /* =====================================
     * МАТЧЕРЫ ДЛЯ РАБОТЫ СО СТРОКАМИ
     * =====================================
     */

    /**
     * Матчер stringContainsInOrder проверяет, что тестируемая строка, содержит последовательность подстрок.
     */
    @Test
    void stringShouldHaveSpecialSubString() {
        assertThat("xUnit is't test framework", stringContainsInOrder(asList("x", "test", "framework")));
    }

    /**
     * Матчер isEmptyString проверяет, что строка пустая
     * Матчер isEmptyOrNullString проверяет, что строка null или пустая
     */
    @Test
    void stringShouldBeEmpty() {
        assertThat("", isEmptyString());
        assertThat(null, isEmptyOrNullString());
    }

    /**
     * Матчер containsString проверяет, что тестируемая строка содержит подстроку
     * Матчер startsWith проверяет, что строка начинается с определеной подстроки
     * Матчер endsWith проверяет, что строка заканчивается определенной подстрокой
     */
    @Test
    void textShouldBeWithSpecialString() {
        String text = "Kotlin Apppium Android";
        assertThat(text, containsString("Apppium"));
        assertThat(text, startsWith("Kotlin"));
        assertThat(text, endsWith("Android"));
    }

    /**
     * Матчер equalToIgnoringCase проверяет строки, игнорируя заглавные буквы
     * Матчер equalToIgnoringWhiteSpace проверяет строки, игнорирую пробелы сначала строки,
     * конца строки и модицифирует все пробелы среди строк до одного пробела.
     */
    @Test
    void textShouldBeEquals() {
        assertThat("Charlies Angels", equalToIgnoringCase("charlies angels"));
        assertThat("charlies  angels        ", equalToIgnoringWhiteSpace(" charlies  angels   "));
    }

    /* =====================================
     * МАТЧЕРЫ ДЛЯ РАБОТЫ С ОБЪЕКТАМИ
     * =====================================
     */

    /**
     * Матчер hasToString проверяет, что тестируемый объект содержит toString метод и возвращает верную сформированную строку
     *
     */
    @Test
    void pojoBeanShouldHaveCorrectToStringMethod() {
        PojoBean pojoBean = new PojoBean("Vadim", 41);
        assertThat(pojoBean, hasToString("[Name: Vadim, Age: 41]"));
    }


    /**
     * Матчер instanceOf проверяет, что тестируемый объект является нужным типом заданного класса
     */
    @Test
    void pojoBeanShouldHaveRightType() {
        PojoBean pojoBean = new PojoBean("Mark", 23);
        assertThat(pojoBean.getClass(), is(instanceOf(PojoBean.class)));
    }

    /**
     * Матчер typeCompatibleWith проверяет, что тестируемый объект является нужным типом/подтипом заданного класса
     */
    @Test
    void pojoBeanShouldHaveCompatibleType() {
        PojoBeanExtend pojoBeanExtend = new PojoBeanExtend("Mark", 23);
        assertThat(pojoBeanExtend.getClass(), is(typeCompatibleWith(PojoBean.class)));
    }

    /**
     * Матчер nullValue проверяет, что тестируемый объект null
     */
    @Test
    void pojoBeanShouldBeNull() {
        PojoBean pojoBean = null;
        assertThat("Объетк не null", pojoBean, is(nullValue()));
    }

    /**
     *  Для демонстрации примеров
     */
    public static class PojoBean {
        private String name;
        private Integer age;

        PojoBean(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        Integer getAge() {
            return age;
        }

        void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            if (this.name == null && this.age == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("Name: ");
            sb.append(this.name);
            sb.append(", ");
            sb.append("Age: ");
            sb.append(this.age);
            sb.append("]");
            return sb.toString();
        }
    }

    /**
     *  Для демонстрации примеров
     */
    public class PojoBeanExtend extends PojoBean {

        PojoBeanExtend(String name, Integer age) {
            super(name, age);
        }
    }
}


