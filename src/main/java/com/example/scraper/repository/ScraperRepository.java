package com.example.scraper.repository;

import com.example.scraper.LondonStockExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class ScraperRepository {

    private final JdbcTemplate jdbcTemplate;

    RowMapper<LondonStockExchange> londonStockExchangeRowMapper = new RowMapper<LondonStockExchange>() {


        @Override
        public LondonStockExchange mapRow(ResultSet rs, int rowNum) throws SQLException {
            LondonStockExchange londonStockExchange = new LondonStockExchange();
            londonStockExchange.setId(rs.getInt("id"));
            londonStockExchange.setCompany_name(rs.getString("company_name"));
            londonStockExchange.setTitle(rs.getString("title"));
            londonStockExchange.setSource(rs.getString("source"));
            londonStockExchange.setDate_time(rs.getString("date_time"));
            londonStockExchange.setCategory(rs.getString("category"));
            londonStockExchange.setRnsNumber(rs.getString("rnsNumber"));
            return londonStockExchange;
        }
    };

    @Autowired
    public ScraperRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ArrayList<LondonStockExchange> getAllRecord() {
        String querry = "SELECT * FROM scraper_record";
        return (ArrayList<LondonStockExchange>) jdbcTemplate.query(querry, londonStockExchangeRowMapper);
    }

    public int addRecord(LondonStockExchange londonStockExchange) {
        String querry = "INSERT INTO scraper_record (id, company_name, title, source, date_time, category, rnsNumber) VALUES (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(querry,
                londonStockExchange.getId(),
                londonStockExchange.getCompany_name(),
                londonStockExchange.getTitle(),
                londonStockExchange.getSource(),
                londonStockExchange.getDate_time(),
                londonStockExchange.getCategory(),
                londonStockExchange.getRnsNumber());
    }
}
