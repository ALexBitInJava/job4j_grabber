package ru.job4j.quartz;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class HabrCareerParse {

    /* У нас есть две константы.
    Первая это ссылка на сайт в целом.
    Вторая указывает на страницу с вакансиями непосредственно
    */
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        /*Сначала мы получаем страницу, чтобы с ней можно было работать:
        */
        Connection connection = Jsoup.connect(PAGE_LINK);
        Document document = connection.get();

        /* Сначала мы получаем все вакансии страницы.
        Перед CSS классом ставится точка. Это правила CSS селекторов, с которыми работает метод JSOUP select()
        */

        Elements rows = document.select(".vacancy-card__inner");
        /*Проходимся по каждой вакансии и извлекаем нужные для нас данные.
        Сначала получаем элементы содержащие название и ссылку.
        Стоит обратить внимание, что дочерние элементы можно получать через индекс - метод child(0)
        или же через селектор - select(".vacancy-card__title").
         */
        rows.forEach(row -> {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element linkElement = titleElement.child(0);
            Element cardDate = row.select(".vacancy-card__date").first();
            Element linkDateTime = cardDate.child(0);
            /*
            Наконец получаем данные непосредственно.
            text() возвращает все содержимое элемента в виде текста,
            т.е. весь текст что находится вне тегов HTML.
            Ссылку находится в виде атрибута, поэтому ее значение надо получить как значение атрибута.
            Для этого служит метод attr()
             */
            String vacancyName = titleElement.text();
            String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            String dateTime = String.format(linkDateTime.attr("datetime"));
            String[] dF = dateTime.split("T");
            DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yy-mm-dd");
            System.out.printf("%s %s %s%n", String.format(dF[0], dTF), vacancyName, link);
        });
    }
}