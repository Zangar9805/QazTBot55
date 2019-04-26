package kz.zangpro;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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

    public String getSportNews() throws IOException {
        String result = "";

        Document doc = Jsoup.connect("https://sports.kz/football").get();
        Elements uls = doc.getElementsByClass("today_ npdg");
        Elements lis = uls.select("li");
        Elements news = lis.select(".t_best");

        for (int i = 0; i < 5; i++) {
            String date = news.get(i).select("b").select("i").text();
            String header = news.get(i).select("span").select("a").get(1).text();
            String link = news.get(i).select("span").select("a").get(0).attr("href");
            String views = news.get(i).select("span").select("span").select("u").text();

            result += "_"+date + "_\n*" + header + "*... " +  "[Мақаланы оқу](https://m.sports.kz"+link+")" + "\n" +
                    "Оқылды: *" + views + "* рет\n\n";
        }

        //System.out.println(news.size());

        return result;
    }

    public String getQuote() throws IOException {
        String result = "";
        String link = "http://zangpro.kz/reqpage?type=quotes";

        URL url = new URL(link);
        Scanner in = new Scanner((InputStream) url.getContent());
        while (in.hasNext()) result += in.nextLine();

        JSONObject json = new JSONObject(result);
        String quote = json.getString("quotes");
        String author = json.getString("author");

        return quote;
    }
}
