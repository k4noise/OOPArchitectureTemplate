package ru.urfu;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Телеграм бот
 */
public class TelegramBot extends TelegramLongPollingBot {

    /** Имя бота */
    private final String telegramBotName;
    /** Обработчик пользовательских сообщений */
    private final MessageProcessor messageProcessor;

    /**
     * Создает телеграмм бота
     * @param telegramBotName Имя бота
     * @param token Токен бота
     * @param messageProcessor Обработчик пользовательских сообщений
     */
    public TelegramBot(String telegramBotName, String token, MessageProcessor messageProcessor) {
        super(token);
        this.telegramBotName = telegramBotName;
        this.messageProcessor = messageProcessor;
    }

    /**
     * Запустить бота
     */
    public void start() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
            System.out.println("Telegram бот запущен");
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось запустить телеграм бота", e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message updateMessage = update.getMessage();
            String chatId = updateMessage.getChatId().toString();
            String messageFromUser = updateMessage.getText();
            sendMessage(chatId, messageProcessor.processRepeatable(messageFromUser));
        }
    }

    /**
     * Отправить сообщение
     * @param chatId идентификатор чата
     * @param message текст сообщения
     */
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("Не удалось отправить сообщение. " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotName;
    }
}
