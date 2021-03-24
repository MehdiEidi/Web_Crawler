package com.mehdieidi;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    private final HashSet<String> seenUrls = new HashSet<>();
    private final ArrayList<String> categoriesList = new ArrayList<>();

    public Crawler(String baseUrl) throws IOException {
        Document document = Jsoup.connect(baseUrl).get();
        Elements categories = document.getElementsByClass("c-navi-new-list__inner-categories").get(0).getElementsByTag("a");

        for (Element element : categories) {
            categoriesList.add("http://digikala.com" + element.getElementsByTag("a").attr("href"));
        }
    }

    public void crawl() {
        for (String category : categoriesList) {
            try {
                crawlCategory(category);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void crawlCategory(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements subCategories = document.getElementsByClass("c-catalog__plain-list-link");

        for (Element subCategory : subCategories) {
            crawlCategoryPage("https://digikala.com" + subCategory.attr("href"));
        }
    }

    private void crawlCategoryPage(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements subCategoriesPages = document.getElementsByClass("c-pager__item");

        crawlPage(url);

        for (Element page : subCategoriesPages) {
            String pageAttr = page.attr("href");

            if (!pageAttr.equals("javascript:")) {
                crawlPage("https://digikala.com" + pageAttr);
            }
        }
    }

    private void crawlPage(String url) throws IOException {
        System.out.println("=================================================================================");

        Document document = Jsoup.connect(url).get();

        Elements discountedGoods = document.getElementsByClass("c-price__discount-oval");
        for (Element element : discountedGoods) {
            System.out.println("http://digikala.com" + element.parent().parent().parent().parent().getElementsByTag("a").get(0).attr("href"));
            System.out.println("=================================================================================");
        }
    }

//    public void crawl2(String URL) throws IOException {
//        System.out.println("Crawler Started...");
//
//        if (!seenUrls.contains(URL)) {
//            seenUrls.add(URL);
//
//            System.out.println(URL + " is being processed");
//
//            Document document = Jsoup.connect("https://www.digikala.com/").get();
//
//            Elements discountedGoods = document.getElementsByClass("c-new-price__discount");
//            for (Element element : discountedGoods) {
////                if (element.attr("href").contains("%")) {
////                    crawl(element.attr("abs:href"));
////                }
//                System.out.println("http://digikala.com" + element.parent().parent().parent().parent().parent().parent().getElementsByTag("a").get(0).attr("href"));
//                System.out.println("--------------------------------------------------------------------------------------------------");
//            }
//
//
//        }
//    }
}
