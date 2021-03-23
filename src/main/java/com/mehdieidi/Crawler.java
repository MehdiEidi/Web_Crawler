package com.mehdieidi;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    private static final HashSet<String> seen = new HashSet<>();
//    public static DB db = new DB();

    public static void crawl(String URL) throws SQLException, IOException {
        System.out.println("Crawler Started");
        String query = "SELECT * FROM Crawler WHERE URL = '" + URL + "'";
//        ResultSet resultSet = db.runQuery(query);

        boolean urlIsSeen = seen.contains(URL);

        if (!urlIsSeen) {
//            query = "INSERT INTO `Crawler`.`Data` " + "(`URL`) VALUES " + "(?);";
//            PreparedStatement preparedStatement = db.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, URL);
//            preparedStatement.execute();

            seen.add(URL);

            System.out.println(URL);

            Document document = Jsoup.connect("https://www.digikala.com/").get();

            Elements elements = document.getElementsByClass("c-new-price__discount");
            for (Element element : elements) {
//                if (element.attr("href").contains("%")) {
//                    crawl(element.attr("abs:href"));
//                }
                System.out.println(element.text());
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
//        db.runQuery2("TRUNCATE Data");
        crawl("https://www.digikala.com/");
    }
}
