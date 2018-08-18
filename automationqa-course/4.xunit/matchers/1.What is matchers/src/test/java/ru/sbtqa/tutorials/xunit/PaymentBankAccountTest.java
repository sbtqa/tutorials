package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.sbtqa.tutorials.xunit.FirstMatcher.useFirstMatcher;


/**
 * Введение в Матчеры. Использование библиотеки Hamcrest
 */
class PaymentBankAccountTest {

    private PaymentBankAccount paymentBankAccount;

    @BeforeEach
    void setUp() {
        paymentBankAccount =
                new PaymentBankAccount("Artem", "Sokovets", valueOf(1230000000L));
    }

    /**
     * Использование в тесте методов проверок из класса Assertions (Junit 5). Также используем метод assertTrue из
     * класса org.hamcrest.MatcherAssert
     *
     * Тест специально поломан, чтобы показать: первая проверка падает, другие НЕ проходят
     */
    @Test
    void shouldBeWithoutMatchersValidParamsOfPaymentBankAccount() {
        assertNotNull(paymentBankAccount, "Object is null");
        assertEquals("Артем", paymentBankAccount.getFirstName(), "Имя не совпадает");
        assertTrue("Sokovets".contains(paymentBankAccount.getLastName()), "Фамилия не совпадает");
        assertTrue(valueOf(1230000000L).equals(paymentBankAccount.getBalance()), "Баланс не совпадает");
    }

    /**
     * Использование в тесте готовых матчеров(notNullValue, equalTo, greaterThanOrEqualTo) библиотеки org.hamcrest.
     *
     * Тест специально поломан, чтобы показать: первая проверка падает, другие проходят
     */
    @Test
    void shouldWithMatchersBeValidParamsOfPaymentBankAccount() {
        assertThat(paymentBankAccount,
                allOf(notNullValue(),
                        hasProperty("firstName", equalTo("Артем")),
                        hasProperty("lastName", equalTo("Sokovets")),
                        hasProperty("balance", greaterThanOrEqualTo(valueOf(1230000000L)))));
    }

    /**
     * Использование своего матчера для проверки объекта paymentBankAccount. Вызывается матчер класса {@link FirstMatcher}
     */
    @Test
    void shouldBeWithMyMatcherValidParamsOfPaymentBankAccount() {
        assertThat(paymentBankAccount,
                useFirstMatcher("Artem", "Sokovets", valueOf(1230000000L)));
    }
}
