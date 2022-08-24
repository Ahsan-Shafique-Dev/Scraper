package com.example.scraper;

import com.example.scraper.repository.ScraperRepository;
import com.example.scraper.service.ScraperService;
import org.json.JSONArray;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class Scraper<Jsonreader> {

    ScraperRepository scraperRepository;
    @Autowired
    Scraper(ScraperRepository scraperRepo)
    {
        this.scraperRepository = scraperRepo;
    }


    String postApi = "https://api.londonstockexchange.com/api/v1/components/refresh";
    String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
    String referer = "https://www.londonstockexchange.com/";
    String contentType = "application/json";
    //    String jsonBody = "{\"path\":\"news\",\"parameters\":\"tab%3Dnews-explorer%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":null}]}";
    int pageSize = 5;
    String jsonBody;

    ArrayList<LondonStockExchange> scrapedData = new ArrayList<>();

    public void CurrentData() throws IOException {

        for (int i = 0; i < 1; i++) {
            String jsonBody = "{\"path\":\"news\",\"parameters\":\"tab%3Dnews-explorer%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\"," +
                    "\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":\"" +
                    "page=" + i + "&size=20&sort=datetime,desc\"}]}";

            Connection.Response currentResponse = Jsoup.connect(postApi)
                    .userAgent(userAgent)
                    .method(Connection.Method.POST)
                    .header("Content-Type", contentType)
                    .header("Accept", contentType)
                    .header("referer", referer)
                    .requestBody(jsonBody)
                    .followRedirects(true)
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .timeout(0)
                    .execute();

            int statusCode = currentResponse.statusCode();
            String responseJson = currentResponse.body();

//            JSONArray jsonArray = new JSONArray(responseJson);
           /* for (int x = i; x < 20 ; x++)
            {
//                System.out.println("ID: " + x + " " + jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("id"));
//                System.out.println("Company name: " + x + " " + jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("companyname"));
//                System.out.println("Source: " + x + " " + jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("source"));
//                System.out.println("Date and TIme : " + x + " " + jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("datetime"));
//                System.out.println(" ");
            }*/
        }
    }

    public ArrayList<LondonStockExchange> HistoricData() throws IOException {
        for (int i = 0; i < pageSize; i++) {
            if (i == 0) {
                jsonBody = "{\"path\":\"news\",\"parameters\":\"tab%3Dnews-explorer%26period%3Dcustom%" +
                        "26beforedate%3D20220811%" +
                        "26afterdate%3D20220801%" +
                        "26headlinetypes%3D1%2C2%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":null}]}";
            } else {
                jsonBody = "{\"path\":\"news\",\"parameters\":\"" +
                        "tab%3Dnews-explorer%26period%3Dcustom%26beforedate%3D20220811%26afterdate%3D20220801%" +
                        "26page%" +
                        "3" +
                        "D" +
                        i +
                        "%26headlinetypes%3D1%2C2%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\"" +
                        ",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":null}]}";
            }
            Connection.Response historicalResponse = Jsoup.connect(postApi)
                    .userAgent(userAgent)
                    .method(Connection.Method.POST)
                    .header("Content-Type", contentType)
                    .header("Accept", contentType)
                    .header("referer", referer)
                    .requestBody(jsonBody)
                    .followRedirects(true)
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .timeout(0)
                    .execute();

            int statusCode = historicalResponse.statusCode();
            String responseJson = historicalResponse.body();

            JSONArray jsonArray = new JSONArray(responseJson);


            for (int x = i; x < 20; x++) {

                int id = (int) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("id");
                String company_name = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("companyname");
                String title = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("title");
                String source = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("source");
                String dateTime = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("datetime");
                String category = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("category");
                String rnsNumber = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("rnsnumber");

                scrapedData.add(new LondonStockExchange(id, company_name, title, source, dateTime, category, rnsNumber));

                scrapedData.forEach((r) -> addRecord(r) );
            }
        }
        return scrapedData;
    }

    public ArrayList<LondonStockExchange> ReturnData() {
        try {
            if (scrapedData.isEmpty()) {
                return HistoricData();
            } else
                return scrapedData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int addRecord(LondonStockExchange londonStockExchange)
    {
        return scraperRepository.addRecord(londonStockExchange);
    }
    public ArrayList<LondonStockExchange> getAllRecord()
    {
        return scraperRepository.getAllRecord();
    }

//    public ArrayList<LondonStockExchange> getAllRecord() {
//        return repo.getAllRecord();
//    }
//
//    public <scrapedData> int addRecord(scrapedData data) {
//        return repo.addRecord((LondonStockExchange) data);
//    }
}
