package ru.sbtqa.tutorials.xunit.corematchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import ru.sbtqa.tutorials.xunit.corematchers.CoreMatchersTest.PojoBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
     * Реализуем свой матчер использую абстрактный класс BaseMatcher
     * @param i - значение, которое должно быть в свойстве класса PojoBean поля age
     * @return {@link Matcher}
     */
    private Matcher<PojoBean> hasAge(final int i) {
        return new BaseMatcher<PojoBean>() {
            @Override
            public boolean matches(final Object item) {
                final PojoBean pojoBean = (PojoBean) item;
                return i == pojoBean.getAge();
            }
            @Override
            public void describeTo(final Description description) {
                description.appendText("Age not correct. Should return ").appendValue(i);
            }
        };
    }



}
