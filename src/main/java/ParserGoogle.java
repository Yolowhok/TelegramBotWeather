import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class ParserGoogle {
    Connection.Response response;
    String [] englCity;
    String deliter(String city) {
        String cit="";
        for (int i = 0; i < city.length(); i++) {
            if (city.charAt(i)==' ') {
                cit+="-";
            }
            if (city.charAt(i)!=' ') {
                cit+=city.charAt(i);
            }
        }
        return cit;
    }
    String city(String city) {
        city = deliter(city);
        String url = "https://www.google.ru/search?q="+city+"+yandex+pogoda";
        try {
            response = Jsoup.connect(url).followRedirects(false).execute();
            if (404 != response.statusCode()) {

                Document page = Jsoup.parse(new URL(url), 3000);

                Element element = page.select("cite.iUh30").first();

                englCity = element.text().split("› ");
                return englCity[2];
            }
        } catch (IOException e) {
            System.out.print("Страница не найдена: " + url);
        }
        return null;
    }
}
