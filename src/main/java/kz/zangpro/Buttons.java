package kz.zangpro;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

class Buttons {

    /* !- Басты мәзірдің текст айнымалылары------------------------------------------------------ */
    static String weatherMenuText = EmojiParser.parseToUnicode(":partly_sunny: Ауа-райы");
    static String newsMenuText = EmojiParser.parseToUnicode(":radio: Жаңалықтар stan.kz-тен");
    static String gamesMenuText = EmojiParser.parseToUnicode(":video_game: Ойындар");
    static String quoteMenuText = EmojiParser.parseToUnicode(":speech_balloon: Нақыл сөз");
    static String helpMenuText = EmojiParser.parseToUnicode(":interrobang: Көмек");
    static String faqMenuText = EmojiParser.parseToUnicode(":recycle: Кері байланыс");

    /* !- Ауа-райы мәзірдің текст айнымалылары------------------------------------------------------ */
    static String weatherMyCityText = EmojiParser.parseToUnicode(":love_hotel: Алматы");
    static String weatherOtherCityText = EmojiParser.parseToUnicode(":city_sunset: Басқа қала");
    static String backToMainMenuText = EmojiParser.parseToUnicode(":leftwards_arrow_with_hook: Басты меню");

    /* !- Нақыл сөз мәзірдің текст айнымалылары------------------------------------------------------ */
    static String getQuotesMenuText = EmojiParser.parseToUnicode(":speech_balloon: Кездейсоқ нақыл сөз");
    static String addQuotesMenuText = EmojiParser.parseToUnicode(":heavy_plus_sign: Нақыл сөз қосу");
    //static String backToMainMenuText = EmojiParser.parseToUnicode(":leftwards_arrow_with_hook: Басты меню");

    /* !- Жаңалықтар мәзірдің текст айнымалылары------------------------------------------------------ */
    private static String newsBiznesMenuText = EmojiParser.parseToUnicode(":dancer: Шоу-бизнес");
    private static String newsWorldMenuText = EmojiParser.parseToUnicode(":earth_africa: Әлемде");
    private static String newsPoliticiesMenuText = EmojiParser.parseToUnicode(":briefcase: Саясат");
    private static String newsLifeMenuText = EmojiParser.parseToUnicode(":diamond_shape_with_a_dot_inside: Өмір-айнасы");
    private static String newsSportMenuText = EmojiParser.parseToUnicode(":football: Спорт");
    //static String backToMainMenuText = EmojiParser.parseToUnicode(":house_with_garden: Басты меню");


    static void setGeneralMenuButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        KeyboardRow keyboardRowSecond = new KeyboardRow();
        KeyboardRow keyboardRowThird = new KeyboardRow();

        keyboardRowFirst.add(new KeyboardButton(weatherMenuText));
        keyboardRowFirst.add(new KeyboardButton(newsMenuText));
        keyboardRowSecond.add(new KeyboardButton(gamesMenuText));
        keyboardRowSecond.add(new KeyboardButton(quoteMenuText));
        keyboardRowThird.add(new KeyboardButton(helpMenuText));
        keyboardRowThird.add(new KeyboardButton(faqMenuText));

        keyboardRows.add(keyboardRowFirst);
        keyboardRows.add(keyboardRowSecond);
        keyboardRows.add(keyboardRowThird);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    static void setWeatherMenuButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        KeyboardRow keyboardRowSecond = new KeyboardRow();
        KeyboardRow keyboardRowThird = new KeyboardRow();

        keyboardRowFirst.add(new KeyboardButton(weatherMyCityText));
        keyboardRowSecond.add(new KeyboardButton(weatherOtherCityText));
        keyboardRowThird.add(new KeyboardButton(backToMainMenuText));

        keyboardRows.add(keyboardRowFirst);
        keyboardRows.add(keyboardRowSecond);
        keyboardRows.add(keyboardRowThird);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    static void setQuotesMenuButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        KeyboardRow keyboardRowSecond = new KeyboardRow();
        KeyboardRow keyboardRowThird = new KeyboardRow();

        keyboardRowFirst.add(new KeyboardButton(getQuotesMenuText));
        keyboardRowSecond.add(new KeyboardButton(addQuotesMenuText));
        keyboardRowThird.add(new KeyboardButton(backToMainMenuText));

        keyboardRows.add(keyboardRowFirst);
        keyboardRows.add(keyboardRowSecond);
        keyboardRows.add(keyboardRowThird);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    static void setNewsMenuButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowFirst = new KeyboardRow();
        KeyboardRow keyboardRowSecond = new KeyboardRow();
        KeyboardRow keyboardRowThird = new KeyboardRow();

        keyboardRowFirst.add(new KeyboardButton(newsBiznesMenuText));
        keyboardRowFirst.add(new KeyboardButton(newsPoliticiesMenuText));
        keyboardRowSecond.add(new KeyboardButton(newsWorldMenuText));
        keyboardRowSecond.add(new KeyboardButton(newsLifeMenuText));
        keyboardRowThird.add(new KeyboardButton(newsSportMenuText));
        keyboardRowThird.add(new KeyboardButton(backToMainMenuText));

        keyboardRows.add(keyboardRowFirst);
        keyboardRows.add(keyboardRowSecond);
        keyboardRows.add(keyboardRowThird);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

}
