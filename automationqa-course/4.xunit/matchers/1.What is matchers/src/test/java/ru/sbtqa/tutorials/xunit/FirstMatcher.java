package ru.sbtqa.tutorials.xunit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.math.BigDecimal;

/**
 * Создаем свой матчер, который подходит для нашей бизнес-логике (Простой пример 💡).
 * Наследования абстрактного класса TypeSafeMatcher, дает сразу проверку значений на null, проверяет тип объекта и если требуется приводит к нужному типу
 */
class FirstMatcher extends TypeSafeMatcher<PaymentBankAccount> {

    private final String firstName;
    private final String lastName;
    private final BigDecimal balance;

    private FirstMatcher(String firstName, String lastName, BigDecimal balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }


    /**
     * Проверка основных условий, которые нам требуются. Проверяем, что поля равны ожидаемым
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
     * @return {@link FirstMatcher}
     */
    static FirstMatcher useFirstMatcher(String firstName, String lastName, BigDecimal balance) {
        return new FirstMatcher(firstName, lastName, balance);
    }
}