package com.example.scraper;
import lombok.*;

import javax.annotation.sql.DataSourceDefinition;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LondonStockExchange
{
    int id;
    String company_name;
    String title;
    String source;
    String date_time;
    String category;
    String rnsNumber;

/*    public LondonStockExchange() {
    }

    public LondonStockExchange(int id, String company_name, String title, String source, String date_time, String category, String rnsNumber) {
        this.id = id;
        this.company_name = company_name;
        this.title = title;
        this.source = source;
        this.date_time = date_time;
        this.category = category;
        this.rnsNumber = rnsNumber;
    }

    public LondonStockExchange(String company_name, String title, String source, String date_time, String category, String rnsNumber) {
        this.company_name = company_name;
        this.title = title;
        this.source = source;
        this.date_time = date_time;
        this.category = category;
        this.rnsNumber = rnsNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRnsNumber() {
        return rnsNumber;
    }

    public void setRnsNumber(String rnsNumber) {
        this.rnsNumber = rnsNumber;
    }

    @Override
    public String toString() {
        return "LondonStockExchange{" +
                "id=" + id +
                ", company_name='" + company_name + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", date_time='" + date_time + '\'' +
                ", category='" + category + '\'' +
                ", rnsNumber='" + rnsNumber + '\'' +
                '}';
    }*/
}
