import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Parsing {
    public Parsing() throws IOException {
    }
    ParserGoogle pg = new ParserGoogle();
    String extraCITY = null;
    Connection.Response response;

    Document getPage(String city) throws IOException {
        String cit = pg.city(city);
        extraCITY = cit;
        String url = "https://yandex.ru/pogoda/"+ cit + "/details?via=ms";
        try {
            response = Jsoup.connect(url).followRedirects(false).execute();
            if (404 != response.statusCode()) {
                Document page = Jsoup.parse(new URL(url), 3000);
                return page;
            }
        } catch (IOException e) {
            System.out.print("Страница не найдена: " + url);
        }
        return null;
    }

    String WEather(String city) throws IOException {
        Document page = getPage(city);
        if (page==null) {
            System.out.print(" getPage(city) is null \n");
            return null;
        }
        else {
            Element articleWeather = page.select("article.card").first();
            Elements Name = articleWeather.select("span.a11y-hidden");
            String day = Name.select("span").first().text();
            Elements tables = articleWeather.select("tr.weather-table__row").select("span.a11y-hidden");

            String [] dValues = day.split("\\s");
            String [] tValues = null;
            ArrayList<String> list = new ArrayList<>();
            for (Element table: tables) {
                if (table.text().contains("утром") || table.text().contains("днём") || table.text().contains("вечером") || table.text().contains("ночью")) {
                    tValues = table.text().split(", ");
                    list.add(tValues[1]);
                }
            }
            Elements conditions = articleWeather.select("td[class=weather-table__body-cell weather-table__body-cell_type_condition]");
            ArrayList<String> ConditionW = new ArrayList<>();
            for (Element condition: conditions) {
                ConditionW.add(condition.text());
            }

            emoji emoj = new emoji(ConditionW);
            emoj.check();

            String da = extraCITY.toUpperCase() + " " + dValues[1] + " " + dValues[2].toUpperCase() +
                    "\nУтром:  \n      " + emoj.emojiCheck(0) + " " + list.get(0) + ", " + ConditionW.get(0).toLowerCase() +
                    "\nДнём:  \n      " + emoj.emojiCheck(1) + " " + list.get(1) + ", " + ConditionW.get(1).toLowerCase() +
                    "\nВечером:  \n     " + emoj.emojiCheck(2) + " " + list.get(2) + ", " + ConditionW.get(2).toLowerCase() +
                    "\nНочью:  \n     " + emoj.emojiCheck(3) + " " + list.get(3) + ", " + ConditionW.get(3).toLowerCase();
            return da;
        }
    }

}
