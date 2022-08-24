package com.example.scraper.controller;

import com.example.scraper.LondonStockExchange;
import com.example.scraper.Scraper;
import com.example.scraper.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ScraperController {

    Scraper scraperService;
    @Autowired
    ScraperController(Scraper scraper)
    {
        this.scraperService = scraper;
    }


    @GetMapping("/Get")
    public ArrayList<LondonStockExchange> getAllRecord() {
        System.out.println("Get");
        return scraperService.getAllRecord();
    }

    @PostMapping("/AddingData")
    public int addRecord(@RequestBody LondonStockExchange londonStockExchange)
    {
        return scraperService.addRecord(londonStockExchange);
//        return scraperService.GetAllRecord();
    }

//    @RequestMapping(value = "/InsertingData", method = RequestMethod.POST)
//    public int AddRecord(@RequestBody LondonStockExchange londonStockExchange) {
//        return  scraperService.AddRecord(londonStockExchange);
//
//    }
}
