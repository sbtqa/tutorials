package ru.sbtqa.tutorials.xunit.corematchers;

import org.hamcrest.*;
import org.junit.jupiter.api.Test;
import ru.sbtqa.tutorials.xunit.corematchers.CoreMatchersTest.PojoBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * В данном классе представлены реализации своих матчеров унаследованных от базовых матчеров hamcrest
 * <p>
 * Внимание! Некоторые примерны спецально сделаны failed, чтобы продемонстрировать сообщения при ошибках
 */
class MyMatchersTest {

    /**
     * Используем свом матчер hasAge, который проверяет тестируемый объект на наличие заданного условия.
     * Условия могут быть разные (Реализация логики зависит от вас и доменной модели тестируемого объекта)
     */
    @Test
    void pojoBeanShouldHaveCorrectAge() {
        PojoBean pojoBean = new PojoBean("Dmitriy", 25);
        assertThat(pojoBean, is(hasAge(25)));
    }

    /**
     * Используем свом матчер hasNameAndAge, который проверяет тестируемый объект на наличие заданных условий.
     * Также в примере используется синтаксичекский матчер is
     */
    @Test
    void pojoBeanShouldHaveCorrectNameAndAge() {
        PojoBean pojoBean = new PojoBean("Dmitriy", 225);
        assertThat(pojoBean, is(hasNameAndAge("Igor", 25)));
    }

    /**
     * Используем свом матчер containAge, который проверяет тестируемый объект на наличие заданных условий.
     */
    @Test
    void pojoBeanShouldHaveContainAge() {
        PojoBean pojoBean = new PojoBean("Dmitriy", 225);
        assertThat(pojoBean, containAge(225));
    }

    /**
     * Используем свом матчер hasNumberFeatureMatcher, который проверяет тестируемый объект на наличие заданных условий.
     */
    @Test
    void pojoBeanShouldHaveAgeFM() {
        PojoBean pojoBean = new PojoBean("Olga", 25);
        assertThat(pojoBean, hasNumberFeatureMatcher(256));
    }

    /**
     * Используем свом матчер length, который проверяет тестируемый объект на наличие заданных условий.
     */
    @Test
    void pojoBeanShouldHaveNameWithCorrectLenght() {
        PojoBean pojoBean = new PojoBean("Константин", 50);
        assertThat(pojoBean, length(equalTo(10))); //Используем встроенный матчер equalTo, так как length принимает только матчеры
    }

    /**
     * Реализуем свой матчер использую абстрактный класс BaseMatcher
     *
     * @param age - значение, которое должно быть в свойстве класса PojoBean поля age
     * @return {@link Matcher}
     */
    private Matcher<PojoBean> hasAge(final int age) {
        return new BaseMatcher<PojoBean>() {
            @Override
            public boolean matches(final Object item) {
                final PojoBean pojoBean = (PojoBean) item;
                return age == pojoBean.getAge();
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Age not correct. Should return ").appendValue(age);
            }
        };
    }

    /**
     * Реализуем свой матчер использую абстрактный класс TypeSafeMatcher.
     * TypeSafeMatcher автоматом проверяет, что значение тестируемого объекта не null и именно нужного типа
     *
     * @param name - имя
     * @param age  - возвраст
     * @return {@link Matcher}
     */
    private Matcher<PojoBean> hasNameAndAge(final String name, final int age) {
        return new TypeSafeMatcher<PojoBean>() {
            @Override
            public void describeTo(final Description description) {
                description.appendText(" should return name = ").appendValue(name).appendValue(" and age = ").appendValue(age);
            }

            @Override
            protected void describeMismatchSafely(final PojoBean item, final Description mismatchDescription) {
                mismatchDescription
                        .appendText(" was name = '" + item.getName()
                                + "' and age = '" + item.getAge() + "' "); //Конкатенация параметров через + Обратите внимание на вывод лога
            }

            @Override
            protected boolean matchesSafely(final PojoBean item) {
                return name.equals(item.getName()) & age == item.getAge();
            }
        };
    }

    /**
     * Реализуем свой матчер использую абстрактный класс TypeSafeDiagnosingMatcher.
     * Данный матчер похож на TypeSafeMatcher, за исключением того:
     * 1. Что не требуется реализовывать еще один метод describeMismatchSafely как это было с TypeSafeMatcher
     * 2. Если проверка не пройдет, то метод matchesSafely будет вызван ДВА раза! Это будет потому-что,
     * сначала будет вызвана проверка и вслучае ошибки будет вызван метод, чтобы вывести сообщение при ошибки
     *
     * @param age - возвраст
     * @return {@link Matcher}
     */
    private Matcher<PojoBean> containAge(final int age) {
        return new TypeSafeDiagnosingMatcher<PojoBean>() {

            //Описываем, что матчер делает
            @Override
            public void describeTo(final Description description) {
                description.appendText("Age should be ").appendValue(age);
            }

            //В данном методе, проводим различные проверки и выдаем сообщение в случае ошибки условия
            @Override
            protected boolean matchesSafely(final PojoBean item, final Description mismatchDescription) {
                mismatchDescription.appendText(" was ").appendValue(item.getAge());
                return age == item.getAge();
            }
        };
    }

    /**
     * Реализуем свой матчер использую абстрактный класс FeatureMatcher.
     * <p>
     * FeatureMatcher - это обертка над другими матчерами, но с определенной реализацией
     *
     * @param age - возвраст
     * @return {@link Matcher}
     */
    private Matcher<PojoBean> hasNumberFeatureMatcher(final Integer age) {

        /**
         * FeatureMatcher<T, U> - где T тестируемый объект, U - тип, который мы хотим проверить у данного объетка
         * В нашем примере, тип PojoBean - тестирыемый объект, а Integer - тот тип, который мы хотим провреить в PojoBean
         *
         * Конструктор FeatureMatcher принимает (матчер, текст то что мы проверяем, то что выведется в случае ошибки)
         */
        return new FeatureMatcher<PojoBean, Integer>(equalTo(age), "age ", "age not correct.") { //используем матчер equalTo
            @Override
            protected Integer featureValueOf(final PojoBean pojoBean) {
                return pojoBean.getAge();
            }
        };
    }

    /**
     * Пример взят с сайта: http://www.vogella.com/tutorials/Hamcrest/article.html
     * Использование FeatureMatcher
     *
     * @param matcher - {@link Matcher}
     * @return - {@link Matcher}
     */
    public static Matcher<PojoBean> length(Matcher<? super Integer> matcher) {
        return new FeatureMatcher<PojoBean, Integer>(matcher, "a String of length that", "length") {
            @Override
            protected Integer featureValueOf(PojoBean actual) {
                return actual.getName().length();
            }
        };
    }


}
