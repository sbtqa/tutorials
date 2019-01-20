package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class BDDNestTest {
    @Test
    @DisplayName("Дано: Пользователь проходит авторизацию в системе")
    void login() { /*...*/ }


    @Nested
    @DisplayName("Когда:")
    class Send {

        @DisplayName("Пользователь отправляет сообщение")
        void sendMess() { /*...*/ }


        @Nested
        @DisplayName("Тогда система присылает ответ")
        class AfterSending {

            @Test
            @DisplayName("Проверяем, что идентификатор уникальный")
            void checkTicket() { /*...*/ }

            @Test
            @DisplayName("Проверяем, что идентификатор заполнен корректно")
            void checkTicketField() { /*...*/ }

            @Test
            @DisplayName("Пользователь сохраняет тикет")
            void saveTicket() { /*...*/ }

            @Nested
            @DisplayName("Когда:")
            class Status {

                @Test
                @DisplayName("пользователь запрашивает статус по тикету")
                void getTicketID() { /*...*/ }
            }
        }
    }
}
