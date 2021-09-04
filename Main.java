package com.mehdieidi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Crawler crawler = null;

        try {
            crawler = new Crawler("https://digikala.com");
        } catch (IOException e) {
            e.printStackTrace();
        }


        crawler.crawl();
    }
}
