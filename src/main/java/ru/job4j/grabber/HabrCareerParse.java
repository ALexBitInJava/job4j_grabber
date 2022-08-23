package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {

    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);
    private static final int COUNT = 5;
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws IOException {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> list = habrCareerParse.list(PAGE_LINK);
        list.forEach(System.out::println);
    }

    private static String retrieveDescription(String link) {
        Document document;
        try {
            document = Jsoup.connect(link).get();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return document.select(".style-ugc").text();
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        for (int page = 1; page <= COUNT; page++) {
            Document document = null;
            try {
                document = Jsoup.connect(link + page).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title")
                        .first().child(0);
                String vacancyName = titleElement.text();
                String vacancyDate = row.select(".vacancy-card__date")
                        .first().child(0).attr("datetime");

                String vacancyLink = String.format("%s%s", SOURCE_LINK, titleElement.attr("href"));
                String vacancyDescriprion = retrieveDescription(vacancyLink);
                list.add(new Post(vacancyLink, vacancyName, vacancyDescriprion, dateTimeParser.parse(vacancyDate)));
            });
        }
        return list;
    }
}