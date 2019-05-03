package kz.zangpro;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class MainApp extends TelegramLongPollingBot {


    private final String GENERAL_MENU_STATE = "general";
    private final String NEWS_MENU_STATE = "news";
    private final String WEATHER_MENU_STATE = "weather";
    private final String QUOTE_MENU_STATE = "quote";
    private final String MESS_TO_ME_MENU_STATE = "messageToMe";
    //private final String GENERAL_MENU_STATE = "general";

    private String menuState = GENERAL_MENU_STATE;

    private String weatherMenuText = Buttons.weatherMenuText;
    private String newsMenuText = Buttons.newsMenuText;
    private String addQuoteMenuText = Buttons.addQuoteMenuText;
    private String quoteMenuText = Buttons.quoteMenuText;
    private String helpMenuText = Buttons.helpMenuText;
    private String faqMenuText = Buttons.faqMenuText;

    private String weatherMyCityText = Buttons.weatherMyCityText;
    private String weatherOtherCityText = Buttons.weatherOtherCityText;
    private String backToMainMenuText = Buttons.backToMainMenuText;

    private DataDownloader dataDownl = new DataDownloader();
    private String myChatId = "351165895";

    public static void main(String... args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        MainApp bot = new MainApp();
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        //bot.sendMsgMe("Сәлем мен ботпын");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        /* !- Собрать данные о пользователя --------------------------------------------------------*/
        String userName = "defName", linkUser = "defUserName", fullName, chatId;
        if (message.getChat().getFirstName() != null) userName = message.getChat().getFirstName();
        chatId = message.getChatId().toString();
        fullName = message.getChat().getFirstName() + " " + message.getChat().getLastName();
        if (message.getChat().getUserName() != null) linkUser = message.getChat().getUserName();

        if (message.hasText()) {
            String mess = message.getText();

            if (mess.equals("/about"))
                sendDataMsg(message, "Обо мне: \n" + "idUser = " + chatId + "\n" + "linkUser = " + linkUser + "\n");

            else if (mess.equals("/start")) {
                sendDataMsg(message, "Қош келдіңіз, " + userName + "! \n" +
                        "Мен сіздің жеке көмекшіңізбін \n" +
                        "есімім \"*QazTBot*\" \n" +
                        "Әміріңізді орындауға дайынмын!\n");

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String strDate = dateFormat.format(date);

                GoToDB requestAuth = new GoToDB();
                HashMap<String, String> data = new HashMap<>();
                data.put("type", "auth");
                data.put("name", userName);
                data.put("userName", linkUser);
                data.put("fullName", fullName);
                data.put("chatId", chatId);
                data.put("regDate", strDate);
                requestAuth.performPostCall("http://zangpro.kz/reqpage", data);

                sendMsgAll("Сіздің ботыңызды " + userName + " @" + linkUser + " өзіне орнатты!", myChatId);

            }

            else if (mess.equals("/help") || mess.equals(helpMenuText))
                sendDataMsg(message, "Қандай көмек көрсете аламын?\n" + "Қол жетімді командалар(пәрмен) тізімін көру үшін \"/\" символын теріңіз.");

            else if (mess.equals(weatherMenuText)) {
                menuState = WEATHER_MENU_STATE;
                sendDataMsg(message, "Өз қалаңыз бойынша ауа-райы болжамын білу үшін, төмендегі мәзір жолын қолданыңыз:");
                //sendDataMsg(message, "Өз қалаңыз бойынша ауа-райы болжамын білу үшін осы чатқа:\n" +"Қалаңыздың атын жазып жіберіңіз.\n" +"\n" +"Мысалға: *Алматы* немесе *London*");

            } else if (mess.equals(weatherOtherCityText)) {
                menuState = WEATHER_MENU_STATE;
                sendDataMsg(message, "Басқа қалалар бойынша ауа-райы болжамын білу үшін осы чатқа:\n" +"Қалаңыздың атын жазып жіберіңіз.\n" +"\n" +"Мысалға: *Семей* немесе *Moscow*");

            } else if (mess.equals(faqMenuText)) {
                menuState = GENERAL_MENU_STATE;
                sendDataMsg(message, "Осы бот бойынша ескертпелер мен ұсыныстарды мына қолданушыға, яғни, бот авторына жіберсеңіздер болады: \n" + "@zangking");

            } else if (mess.equals(newsMenuText)) {
                menuState = NEWS_MENU_STATE;
                sendDataMsg(message, "Қазіргі таңда бот сізге келесі бөлімдер бойынша жаңалықтар көрсете алады:\n\n" +"Соңғы спорт жаңалықтары(ҚПЛ): \n/sportNewsKPL");

            }
            else if (mess.equals(quoteMenuText)) sendDataMsg(message, "Get Quote");

            else if (mess.equals("/sportNewsKPL")) sendDataMsg(message, "Sport News");

            else if (mess.equals("/author")) sendDataMsg(message, " Мені ойлап тапқан:\nЕгетай Заңғар Саматұлы\n@zangking");

            else if (mess.equals(backToMainMenuText)) {
                menuState = GENERAL_MENU_STATE;
                sendDataMsg(message, backToMainMenuText);
            }

            else if (mess.equals("/settings")) sendDataMsg(message, "Дәл қазір бұл аймақ өңделіп жатыр...");

            else if (mess.equals("/aq")) menuState = QUOTE_MENU_STATE;

            else if (mess.equals(addQuoteMenuText)) {
                sendDataMsg(message, "Сіз өзіңізге ұнайтын цитатаңызды қосуыңызға болады.\n" +
                        "Ол үшін хабарлама жолына цитатаңызды жазып жіберіңіз!\n\n");
                menuState = MESS_TO_ME_MENU_STATE;

            } else if (menuState.equals(MESS_TO_ME_MENU_STATE)) {
                sendMsgAll(mess, myChatId);
                sendDataMsg(message, "Рахмет, цитатаңыз тексерілуге жіберілді. Егер тексерістен өтсе, осы жерден өз цитатаңызды оқи аласыз!\n");
                menuState = GENERAL_MENU_STATE;

            } else if (mess.equals("QazTBot") ||
                    mess.equals("QaztBot") ||
                    mess.equals("qaztBot") ||
                    mess.equals("qaztbot") ||
                    mess.equals("Qaztbot") ||
                    mess.equals("qazTbot") ||
                    mess.equals("qazTBot")) sendDataMsg(message, "Ау?)");

            else if (menuState.equals(QUOTE_MENU_STATE)) {
                GoToDB requestAddQuote = new GoToDB();
                HashMap<String, String> data = new HashMap<>();
                data.put("type", "quote");
                data.put("quote", mess);
                data.put("author", fullName);
                String responseCode = requestAddQuote.performPostCall("http://zangpro.kz/reqpage", data);
                sendDataMsg(message, "Рахмет, цитатаңыз тексерілуге жіберілді. Егер тексерістен өтсе, осы жерден өз цитатаңызды оқи аласыз!\n\n" + responseCode);
                menuState = GENERAL_MENU_STATE;

            }

            else if (menuState.equals(WEATHER_MENU_STATE)) sendDataMsg(message, "Weather Text");

            else {
                sendDataMsg(message, "Кешіріңіз дәл қазір мен бұл сұрағыңызға жауап бере алмаймын. " +
                        "Мен әлі дайындалу үстіндемін, ол үшін маған көөөп оқу керек. \n\n" +
                        "Егер сізге ауа-райы болжамы қажет болса, \"Басты менюден\" - " +
                        weatherMenuText + " - батырмасын басып, қалаңызды басынан енгізіңіз.");
            }
        }
    }

    private void sendDataMsg(Message message, String text) {
        WeatherModel weatherModel = new WeatherModel();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());

        /*
         *  !--- Разбор новости ---------------------------------------------------
         *  */
        if (text.equals("Sport News")) {
            String sportNews = "Sport News";
            try {
                sportNews = dataDownl.getSportNews();
            } catch (IOException e) {
                e.printStackTrace();
            }
            text = sportNews;

        }


        /*
         *  !--- Разбор погоды ---------------------------------------------------
         *  */
        if (menuState.equals(WEATHER_MENU_STATE)){
            if (text.equals("Weather Text")) {
                String city = message.getText();
                if (message.getText().equals(weatherMyCityText)) city = message.getText().split(" ")[1];

                try {
                    text = dataDownl.getWeather(city, weatherModel);
                    menuState = GENERAL_MENU_STATE;
                } catch (IOException e) {
                    text = "Бұл қала бойынша біздің базада мәліметтер жоқ екен. \nМүмкін латынша жазып көрерсіз...";
                    menuState = WEATHER_MENU_STATE;
                }
            }
        } else menuState = GENERAL_MENU_STATE;


        if (text.equals("Get Quote")) {
            try {
                text = dataDownl.getQuote();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        sendMessage.setText(text);
        try {
            if (menuState.equals(WEATHER_MENU_STATE)) Buttons.setWeatherMenuButtons(sendMessage);
            else Buttons.setGeneralMenuButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsgAll(String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(chatId + " = мен тіркелген қолданушы, бізді блокқа салып қойыпты!");
        }
    }

    @Override
    public String getBotUsername() {
        return "QazTBot";
    }

    @Override
    public String getBotToken() {
        return "853753643:AAFMdYHyRPULifWjVBDx4DO_GOpzyhI8zYw";
    }
}