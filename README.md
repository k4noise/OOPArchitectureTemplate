
# Задача 

**Применить ниспадающее программирование для задачи построения архитектуры чат-бот платформы**

Построить базовую архитектуру бота, который сможет одинаково работать в нескольких чат-бот системах (в шаблоне реализовано подключение к Telegram и Discord). 

В качестве основного функционала бота требуется возвращать в ответ текст, который отправил пользователь с пометкой от бота. Формат возвращаемого ответа "Ваше сообщение: 'сообщение>'".

Учтите, что в будущем предполагается расширение функционала.

Нужно акцентировать внимание именно на архитектуре и интерфейсах. Постарайтесь сделать **минимально необходимое** количество интерфейсов.

Большим плюсом будет подумать заранее о возможных путях расширения функционала и учесть это в архитектуре.

### Пример:

`User: привет`

`Bot: "Ваше сообщение: 'привет'".`

## Рабочий шаблон

В шаблоне реализовано подключение к Telegram и Discord. Реализована вся специфичная логика работы в этих чат-бот системах: достаёт из специальных классов текстовое сообщение от пользователя и имеет методы для отправки ответа.
Осталось лишь реализовать архитектуру и требуемый функционал.

**Отправлять домашнее задание нужно в виде Pull Request. Пожалуйста, сразу ведите разработку в отдельной ветке.**

## Дополнительное задание (по желанию)

Исправить FIXME из файла pom.xml, нужно устранить уязвимости транзитивных библиотек.

Менять версию библиотек telegrambots, discord4j-core - нельзя. Они используются в коде и обновление может нарушить стабильную работу приложения.

# Запуск проекта

## Параметры запуска приложения

Для запуска приложения нужно указать параметры в разделе "Environment Variables":
* telegram_botName
* telegram_token
* discord_token

## Как получить токен для Telegram бота

1. Запускаем телеграмм бота [@BotFather](https://t.me/botfather)
2. Вводим команду /newbot и вводим имя для нашего нового бота. Имя должно быть уникальным и заканчиваться на bot
3. Бот сгенерирует нам уникальный токен. Данный токен лучше держать в секрете и никому не показывать, потому что узнав его можно получить полный контроль над вашим ботом.

## Как получить токен для Discord бота

1. Войдите на [портал разработчиков Discord](https://discord.com/developers/applications).
2. На вкладке «Applications» нажмите «New Application». 
3. Введите имя для нашего бота и нажмите «Create» 
4. (не обязательно) Загрузите значок приложения и описание и нажмите «Save Changes».
5. (устарело) На вкладке «Bot» нажимаем «Add Bot» и подтверждаем, что мы хотим это сделать.
6. На вкладке «Bot» нажимаем «Reset Token» и подтверждаем, что мы хотим это сделать.
7. Копируем готовый токен в настройки запуска приложения
8. Перейдём во вкладку OAuth2, пролистаем чуть ниже, выберем «Bot» и отметим нужные боту привилегии ("Bot" и "Administrator")