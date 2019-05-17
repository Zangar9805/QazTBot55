package kz.zangpro;

import kz.zangpro.data.DataDownloader;
import kz.zangpro.data.GoToDB;
import kz.zangpro.models.NewsModel;
import kz.zangpro.models.User;
import kz.zangpro.models.WeatherModel;
import kz.zangpro.services.UserService;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class MainApp extends TelegramLongPollingBot {


    private final String GENERAL_MENU_STATE = "generalMenu";
    private final String NEWS_MENU_STATE = "newsMenu";
    private final String WEATHER_MENU_STATE = "weatherMenu";
    private final String QUOTE_MENU_STATE = "quoteMenu";
    private final String MESS_TO_ME_MENU_STATE = "messageToMeMenu";
    private final String GAMES_MENU_STATE = "gamesMenu";
    //private final String GENERAL_MENU_STATE = "general";

    private String menuState = GENERAL_MENU_STATE;

    private final String weatherMenuText = Buttons.weatherMenuText;
    private final String newsMenuText = Buttons.newsMenuText;
    private final String gamesMenuText = Buttons.gamesMenuText;
    private final String quoteMenuText = Buttons.quoteMenuText;
    private final String helpMenuText = Buttons.helpMenuText;
    private final String faqMenuText = Buttons.faqMenuText;

    private final String weatherMyCityText = Buttons.weatherMyCityText;
    private final String weatherOtherCityText = Buttons.weatherOtherCityText;
    private final String backToMainMenuText = Buttons.backToMainMenuText;

    private final String getQuotesMenuText = Buttons.getQuotesMenuText;
    private final String addQuotesMenuText = Buttons.addQuotesMenuText;

    private final String gamesPaintIoMenuText = Buttons.gamesPaintIoMenuText;
    private final String gamesCorsairsMenuText = Buttons.gamesCorsairsMenuText;
    private final String gamesSpinnerMenuText = Buttons.gamesSpinnerMenuText;
    private final String gamesLumberJackMenuText = Buttons.gamesLumberJackMenuText;
    private final String gamesMotofx2MenuText = Buttons.gamesMotofx2MenuText;

    private DataDownloader dataDownl = new DataDownloader();
    private String myChatId = "351165895";

    UserService userService = new UserService();

    public static void main(String... args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(); //7478098667
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

                User user = new User(userName, linkUser, fullName, chatId, strDate, "Almaty", "general");
                userService.saveUser(user);

//                GoToDB requestAuth = new GoToDB();
//                HashMap<String, String> data = new HashMap<>();
//                data.put("type", "auth");
//                data.put("name", userName);
//                data.put("userName", linkUser);
//                data.put("fullName", fullName);
//                data.put("chatId", chatId);
//                data.put("regDate", strDate);
//                requestAuth.performPostCall("http://zangpro.kz/reqpage", data);

                sendMsgAll("Сіздің ботыңызды " + userName + " @" + linkUser + " өзіне орнатты!", myChatId);

            }

            else if (mess.equals("/author")) sendDataMsg(message, " Мені ойлап тапқан:\nЕгетай Заңғар Саматұлы\n@zangking");
            else if (mess.equals("/settings")) sendDataMsg(message, "Дәл қазір бұл аймақ өңделіп жатыр...");
            else if (mess.equals("/aq")) menuState = QUOTE_MENU_STATE;

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
                sendDataMsg(message, "Қазіргі таңда бот сізге келесі бөлімдер бойынша жаңалықтар көрсете алады:\n");

            }
            else if (mess.equals(quoteMenuText)) {
                menuState = QUOTE_MENU_STATE;
                sendDataMsg(message, quoteMenuText);
            }
            else if (mess.equals(getQuotesMenuText)) sendDataMsg(message, "Get Quote");


            else if (mess.equals(backToMainMenuText)) {
                menuState = GENERAL_MENU_STATE;
                sendDataMsg(message, backToMainMenuText);
            }

            else if (mess.equals(gamesMenuText)){
                menuState = GAMES_MENU_STATE;
                sendDataMsg(message, "Қазіргі таңда бот келесі ойындарды ұсына алады:");
            }

            else if (menuState.equals(GAMES_MENU_STATE)) sendDataMsg(message, "Get Games");

            else if (mess.equals(addQuotesMenuText)) {
                sendDataMsg(message, "Сіз өзіңізге ұнайтын цитатаңызды қосуыңызға болады.\n" +
                        "Ол үшін хабарлама жолына цитатаңызды жазып жіберіңіз!\n\n");
                menuState = MESS_TO_ME_MENU_STATE;

            } else if (menuState.equals(MESS_TO_ME_MENU_STATE)) {
                sendMsgAll(mess+"\nжазған: "+userName+"\nЖеке чат номері: "+chatId, myChatId);
                sendDataMsg(message, "Рахмет, цитатаңыз тексерілуге жіберілді. Егер тексерістен өтсе, осы жерден өз цитатаңызды оқи аласыз!\n");
                menuState = GENERAL_MENU_STATE;

            } else if (menuState.equals(QUOTE_MENU_STATE)) {
                GoToDB requestAddQuote = new GoToDB();
                HashMap<String, String> data = new HashMap<>();
                data.put("type", "quote");
                data.put("quote", mess);
                data.put("author", fullName);
                requestAddQuote.performPostCall("http://zangpro.kz/reqpage", data);
                sendDataMsg(message, "Құттықтаймын, сіз жіберген нақыл сөз біздің декректер қорына енгізілді!\n");
                menuState = GENERAL_MENU_STATE;

            }
            else if (menuState.equals(WEATHER_MENU_STATE)) sendDataMsg(message, "Weather Text");
            else if (menuState.equals(NEWS_MENU_STATE)) sendDataMsg(message, "Get News");
            else if ( mess.equals("qazbot") || mess.equals("Qazbot") || mess.equals("qazBot")) sendDataMsg(message, "Ау?)");

            else {
                sendDataMsg(message, "Кешіріңіз дәл қазір мен бұл сұрағыңызға жауап бере алмаймын. " +
                        "Мен әлі дайындалу үстіндемін, ол үшін маған көөөп оқу керек. \n\n" +
                        "Егер осында жаңа функцияның енгізілгенін қаласаңыз, ол туралы толық мәліметті бот авторына-@zangking жіберсеңіз болады. Біз кез-келген идеяны қабылдауға дайынбыз!");
                sendMsgAll(mess+"\nжазған: "+userName+"\nЖеке чат номері: "+chatId, myChatId);
            }
        }
    }

    private void sendDataMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());

        /*
         *  !--- Менюдің басқарушы пәрмендері ---------------------------------------------------
         *  */
        if (menuState.equals(WEATHER_MENU_STATE)){
            if (text.equals("Weather Text")) {
                String city = message.getText();
                if (message.getText().equals(weatherMyCityText)) city = message.getText().split(" ")[1];

                try {
                    text = dataDownl.getWeather(city, new WeatherModel());
                    menuState = GENERAL_MENU_STATE;
                } catch (IOException e) {
                    text = "Бұл қала бойынша біздің деректер қорында мәліметтер жоқ екен. \nМүмкін латынша жазып көрерсіз...";
                    menuState = WEATHER_MENU_STATE;
                }
            }
        } else if (menuState.equals(NEWS_MENU_STATE)){
            if (text.equals("Get News")){
                try {
                    String type = message.getText().split(" ")[1];
                    text = dataDownl.getNewsFromStanKz(type, new NewsModel());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e){
                    text = "Кешіріңіз дәл қазір мен бұл сұрағыңызға жауап бере алмаймын. " +
                            "Мен әлі дайындалу үстіндемін, ол үшін маған көөөп оқу керек. \n\n" +
                            "Егер осында жаңа функцияның енгізілгенін қаласаңыз, ол туралы толық мәліметті бот авторына-@zangking жіберсеңіз болады. Біз кез-келген идеяны қабылдауға дайынбыз!";
                    sendMsgAll(message.getText()+"\nжазған: "+message.getChat().getFirstName()+"\nЖеке чат номері: "+message.getChatId(), myChatId);
                }
            }
        } else if (menuState.equals(QUOTE_MENU_STATE) || menuState.equals(MESS_TO_ME_MENU_STATE)){
            if (text.equals("Get Quote")) {
                try {
                    text = dataDownl.getQuote();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (menuState.equals(GAMES_MENU_STATE)){
            if (text.equals("Get Games")){
                if (message.getText().equals(gamesPaintIoMenuText)) text="https://www.gamee.com/game-bot/paintio";
                else if (message.getText().equals(gamesCorsairsMenuText)) text="https://tbot.xyz/corsairs/";
                else if (message.getText().equals(gamesLumberJackMenuText)) text="https://tbot.xyz/lumber/";
                else if (message.getText().equals(gamesSpinnerMenuText)) text="https://www.gamee.com/game-bot/ipUMpcUES";
                else if (message.getText().equals(gamesMotofx2MenuText)) text="https://www.gamee.com/game-bot/motofx2";
            }
        }else menuState = GENERAL_MENU_STATE;


        sendMessage.setText(text);
        try {
            switch (menuState){
                case WEATHER_MENU_STATE: Buttons.setWeatherMenuButtons(sendMessage); break;
                case NEWS_MENU_STATE: Buttons.setNewsMenuButtons(sendMessage); break;
                case QUOTE_MENU_STATE: Buttons.setQuotesMenuButtons(sendMessage); break;
                case GAMES_MENU_STATE: Buttons.setGamesMenuButtons(sendMessage); break;
                default: Buttons.setGeneralMenuButtons(sendMessage);
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsgAll(String text, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
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