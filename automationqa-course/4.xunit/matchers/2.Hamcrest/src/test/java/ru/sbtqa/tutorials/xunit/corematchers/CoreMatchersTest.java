package ru.sbtqa.tutorials.xunit.corematchers;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Примеры использования стандартных матчеров
 */
class CoreMatchersTest {

    private String efsName = "ЕФС - Единая Фронтальная Система";

    /*  ===================
     *  ЛОГИЧЕСКИЕ МАТЧЕРЫ
     * ===================/

    /*
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
        assertThat(efsName, both(startsWith("ЕФС")).and(instanceOf(String.class)));
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


    /* ======================
     * СИНТАКСИЧЕСКИЕ МАТЧЕРЫ
     * ======================/

    /**
     * Использование матчера is, который влияет только на читабельность кода.ё
     * Hamcrest strives to make your tests as readable as possible.
     * For example, the is matcher is a wrapper that doesn't add any extra behavior to the underlying matcher.
     *
     * Все три утверждения эквивалентны - см. ниже
     */
    @Test
    void shouldWorkHamcrestMatcherIs() {
        assertThat("ППРБ", equalTo("ППРБ"));
        assertThat("ППРБ", is(equalTo("ППРБ")));
        assertThat("ППРБ", is(("ППРБ")));
    }

    /* =====================================
     * МАТЧЕРЫ ПРОВЕРКИ СВОЙСТВ POJO КЛАССОВ
     * =====================================/



       /**
        * Используем матчер anything(), который всегда отрабатывает успешно
        */
    @Test
    void shouldWorkHamcrestMatcherAnyThing() {
        assertThat(efsName, anything());
    }


}
