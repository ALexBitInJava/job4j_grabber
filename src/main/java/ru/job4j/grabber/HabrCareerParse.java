package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse {

    /* У нас есть две константы.
    Первая это ссылка на сайт в целом.
    Вторая указывает на страницу с вакансиями непосредственно
    */
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);
    private static final int COUNT = 5;

    public static void main(String[] args) throws IOException {
        for (int page = 1; page <= COUNT; page++) {
            Connection connection = Jsoup.connect(PAGE_LINK + "?page=" + page);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element linkDateTime = row.select(".vacancy-card__date").first().child(0);
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String dateTime = String.format(linkDateTime.attr("datetime"));
                String[] dF = dateTime.split("T");
                System.out.printf("%s %s %s%n", dF[0], vacancyName, link);
            });
        }
    }
}