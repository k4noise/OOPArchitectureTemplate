package ru.urfu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты к обработчику пользовательских сообщений
 */
class MessageProcessorTest {

    /**
     * Проверяет, корректно ли подставляется метка к сообщению пользователя для его повторения
     */
    @Test
    void testGetRepeatable() {
        String message = "Привет";
        String expectedMessage = String.format("Ваше сообщение: '%s'", message);
        Assertions.assertEquals(expectedMessage, new MessageProcessor().processRepeatable(message));
    }
}