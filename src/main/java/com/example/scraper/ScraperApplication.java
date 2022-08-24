package com.example.scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ScraperApplication {

    Scraper scraper;

    ScraperApplication(Scraper scraper)
    {
        this.scraper = scraper;

        try {
            scraper.ReturnData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ScraperApplication.class, args);
    }

}
