package ru.sbtqa.tutorials.xunit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.sbtqa.tutorials.xunit.MyOwnMatcher.justDoIt;


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

    @Test
    void shouldBeWithoutMatchersValidParamsOfPaymentBankAccount() {
        assertNotNull(paymentBankAccount, "Object is null");
        assertEquals("Artem", paymentBankAccount.getFirstName(), "Имя не совпадает");
        assertTrue("Sokovets".contains(paymentBankAccount.getLastName()), "Фамилия не совпадает");
        assertTrue(valueOf(1230000000L).equals(paymentBankAccount.getBalance()), "Баланс не совпадает");
    }

    @Test
    void shouldWithMatchersBeValidParamsOfPaymentBankAccount() {
        assertThat(paymentBankAccount,
                allOf(notNullValue(),
                        hasProperty("firstName", equalTo("Artem")),
                        hasProperty("lastName", equalTo("Sokovets")),
                        hasProperty("balance", greaterThanOrEqualTo(valueOf(1230000000L)))));
    }

    @Test
    void shouldBeWithMyMatcherValidParamsOfPaymentBankAccount() {
        assertThat(paymentBankAccount, justDoIt("Artem", "Sokovets", valueOf(1230000000L)));
    }


}

/**
 * Создаем свой матчер, который подходит для нашей бизнес-логике (Простой пример 💡).
 * Наследования абстрактного класса TypeSafeMatcher, дает сразу проверку значений на null, проверяет тип объекта и если требуется приводит к нужному типу
 */
class MyOwnMatcher extends TypeSafeMatcher<PaymentBankAccount> {

    private final String firstName;
    private final String lastName;
    private final BigDecimal balance;

    private MyOwnMatcher(String firstName, String lastName, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }


    /**
     * Проверка основных условий, которые нам требуются
     * @param item {@link PaymentBankAccount}
     * @return true/false
     */
    @Override
    protected boolean matchesSafely(PaymentBankAccount item) {
        return item.getFirstName().equals(firstName)
                && item.getBalance().equals(balance)
                && item.getLastName().equals(lastName);
    }

    /**
     * Формируем сообщение, что должно быть
     * @param description {@link String}
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("Объект должен содержать следующие значения  = ")
                .appendValueList("[", ",", "]", firstName, lastName, balance);
    }

    /**
     * Формируем сообщениче, что получилось
     * @param item {@link String}
     * @param mismatchDescription {@link String}
     */
    @Override
    protected void describeMismatchSafely(PaymentBankAccount item, Description mismatchDescription) {
        mismatchDescription.appendText("Объект содержит ")
                .appendValueList("[", ",", "]", item.getFirstName(), item.getLastName(), item.getBalance());
    }

    /**
     * Матчер метод, который мы будем вызывать в методе assertThat
     * @param firstName {@link String}
     * @param lastName {@link String}
     * @param balance {@link }
     * @return {@link MyOwnMatcher}
     */
    static MyOwnMatcher justDoIt(String firstName, String lastName, BigDecimal balance) {
        return new MyOwnMatcher(firstName, lastName, balance);
    }
}