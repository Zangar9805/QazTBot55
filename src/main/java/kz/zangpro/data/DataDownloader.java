package kz.zangpro.data;

import kz.zangpro.models.NewsModel;
import kz.zangpro.models.WeatherModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class DataDownloader {
    private int weatherCount = 0;
    private  int currentTokenId = 0;
    private String[] tokens = new String[]{"74a3bc58dfdfff176cc5ca72c1b0ec45"};

    public String getWeather(String message, WeatherModel model) throws IOException {
        weatherCount++;
        String result = "";
        String weathers = "";
        String token = tokens[currentTokenId];
        String link = "http://api.openweathermap.org/data/2.5/weather?q="+message+"&units=metric&lang=ru&APPID="+token;


        URL url = new URL(link);

        Scanner in = new Scanner((InputStream) url.getContent());
        while (in.hasNext()){
            result += in.nextLine();
        }

        JSONObject json = new JSONObject(result);
        model.setCity(json.getString("name"));
        model.setCountry(json.getJSONObject("sys").getString("country"));
        model.setTemp(json.getJSONObject("main").getDouble("temp"));
        model.setHumidity(json.getJSONObject("main").getDouble("humidity"));

        JSONArray weather = json.getJSONArray("weather");

        for (int i = 0; i < weather.length(); i++) {
            if (i == weather.length()-1) weathers += weather.getJSONObject(i).getString("description");
            else weathers += weather.getJSONObject(i).getString("description") + ", ";
        }
        model.setWeather(weathers);
        model.setIcon(weather.getJSONObject(0).getString("icon"));

        return "Қала:   " + model.getCity() + ", " + model.getCountry() + "\n" +
                "Ауа-райы:   " + model.getWeather() + "\n" +
                "Температура:   " + model.getTemp() + " C" + "\n" +
                "Ылғалдылық:   " + model.getHumidity() + "\n" +
                "Id:   " + weatherCount + "\n" +
                "http://openweathermap.org/img/w/"+model.getIcon()+".png";
    }

    public String getNewsFromStanKz(String type, NewsModel model) throws IOException {
        StringBuilder result = new StringBuilder();
        String category;
        switch (type){
            case "Спорт": category = "sport"; break;
            case "Әлемде": category = "alemde"; break;
            case "Шоу-бизнес": category = "madeniet-jane-show-biznes"; break;
            case "Саясат": category = "sayasat_jane_karji"; break;
            case "Өмір-айнасы": category = "omirainasy"; break;
            default: category = "madeniet-jane-show-biznes";
        }

        Document doc = Jsoup.connect("http://stan.kz/category/"+category+"/").get();
        Elements mainContainer = doc.getElementsByClass("bottom paginate-container");
        Elements innerContainers = mainContainer.select("a");

        for (int i = 0; i < 5; i++) {
            model.setUrlText(innerContainers.get(i).attr("href"));
            model.setHeaderText(innerContainers.get(i).getElementsByClass("bn").get(0).getElementsByClass("block").get(0).select("h2").text());
            //model.setText(innerContainers.get(i).getElementsByClass("bn").get(0).getElementsByClass("block").get(0).getElementsByClass("text").text());
            result.append("*").append(model.getHeaderText()).append("...*[Жалғасын оқу](http://stan.kz").append(model.getUrlText()).append(")\n\n");
        }


        return result.toString();
    }

    public String getQuote() throws IOException {
        String result = "";
        String link = "http://zangpro.kz/reqpage?type=quotes";

        URL url = new URL(link);
        Scanner in = new Scanner((InputStream) url.getContent());
        while (in.hasNext()) result += in.nextLine();

        JSONObject json = new JSONObject(result);
        String quote = json.getString("quotes");
        //String author = json.getString("author");

        return quote;
    }
}
