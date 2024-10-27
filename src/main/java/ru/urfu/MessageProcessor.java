package ru.urfu;

/**
 * Обработать пользовательские сообщения
 */
public class MessageProcessor {
    /**
     * Обрабатывает сообщение пользователя и повторяет его, используя метку
     * @param message Сообщение пользователя
     * @return Сообщение пользователя с меткой
     */
    public String processRepeatable(String message) {
        return String.format("Ваше сообщение: '%s'", message);
    }
}
