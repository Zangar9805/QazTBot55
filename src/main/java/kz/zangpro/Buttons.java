package kz.zangpro;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

class Buttons {

    static String weatherMenuText = EmojiParser.parseToUnicode(":partly_sunny: Ауа-райы");
    static String newsMenuText = EmojiParser.parseToUnicode(":mega: Соңғы жаңалықтар");
    static String addQuoteMenuText = EmojiParser.parseToUnicode(":heavy_plus_sign: Нақыл сөз қосу");
    static String quoteMenuText = EmojiParser.parseToUnicode(":speech_balloon: Нақыл сөздер");
    static String helpMenuText = EmojiParser.parseToUnicode(":interrobang: Көмек");
    static String faqMenuText = EmojiParser.parseToUnicode(":recycle: Кері байланыс");

    static String weatherMyCityText = EmojiParser.parseToUnicode(":love_hotel: Алматы");
    static String weatherOtherCityText = EmojiParser.parseToUnicode(":city_sunset: Басқа қала");
    static String backToMainMenuText = EmojiParser.parseToUnicode(":house_with_garden: Басты меню");


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
        keyboardRowSecond.add(new KeyboardButton(addQuoteMenuText));
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
}
