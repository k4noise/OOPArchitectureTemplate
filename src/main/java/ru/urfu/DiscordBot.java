package ru.urfu;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * Дискорд бот
 * Текущая реализация отвечает пользователю его же сообщением с небольшой припиской
 */
public class DiscordBot {

    /** Токен, отвечающий за управление ботом */
    private final String token;
    /** Обработчик пользовательских сообщений */
    private final MessageProcessor messageProcessor;
    /** Клиент для работы с Дискорд */
    private GatewayDiscordClient client;

    /**
     * Создает дискорд бота
     * @param token Токен для бота
     * @param messageProcessor Обработчик пользовательских сообщений
     */
    public DiscordBot(String token, MessageProcessor messageProcessor) {
        this.token = token;
        this.messageProcessor = messageProcessor;
    }

    /**
     * Запустить бота
     */
    public void start() {
        client = DiscordClient.create(token).login().block();
        if (client == null) {
            throw new RuntimeException("Ошибка при входе в Discord");
        }
        client.on(MessageCreateEvent.class)
                .doOnError(throwable -> {
                    throw new RuntimeException("Ошибка при работе Discord бота", throwable);
                })
                .subscribe(event -> {
                    Message eventMessage = event.getMessage();
                    if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {
                        String chatId = eventMessage.getChannelId().asString();
                        String messageFromUser = eventMessage.getContent();
                        sendMessage(chatId, messageProcessor.processRepeatable(messageFromUser));
                    }
                });
        System.out.println("Discord бот запущен");
        client.onDisconnect().block();
    }

    /**
     * Отправить сообщение
     * @param chatId идентификатор чата
     * @param message текст сообщения
     */
    public void sendMessage(String chatId, String message) {
        Snowflake channelId = Snowflake.of(chatId);
        MessageChannel channel = client.getChannelById(channelId).ofType(MessageChannel.class).block();
        if (channel != null) {
            channel.createMessage(message).block();
        } else {
            System.err.println("Канал не найден");
        }
    }
}
