package ru.urfu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageProcessorTest {

    @Test
    void testGetRepeatable() {
        String message = "Привет";
        String expectedMessage = "Ваше сообщение: '" + message + "'";
        Assertions.assertEquals(expectedMessage, new MessageProcessor().processRepeatable(message));
    }
}