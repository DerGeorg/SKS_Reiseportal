package at.technikum.wien.if20b206.if20b211.moneyserver;

import at.technikum.wien.if20b206.if20b211.moneyserver.data.ArticlesRepo;
import at.technikum.wien.if20b206.if20b211.moneyserver.data.StatsRepo;
import at.technikum.wien.if20b206.if20b211.moneyserver.entity.ArticlesEntity;
import at.technikum.wien.if20b206.if20b211.moneyserver.entity.StatsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

@SpringBootApplication
public class MoneyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyServerApplication.class, args);
    }

    @Autowired
    private StatsRepo statsRepo;

    @Autowired
    private ArticlesRepo articlesRepo;

    //second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "1 48 13 19 * ?")
    private void checkMoney(){
        statsRepo.findAll().forEach(statsEntity -> {
            if(articlesRepo.findById(statsEntity.getId()).isPresent()) {
                ArticlesEntity articlesEntity = articlesRepo.findById(statsEntity.getId()).get();
                String articleTitle = articlesEntity.getTitle();
                String author = articlesEntity.getAuthor();

                System.out.println("Author: " + author + " gets: " + statsEntity.getCount() + " for the article named: " + articleTitle);

                statsEntity.setCount(0);
                statsRepo.save(statsEntity);
            }
        });
    }
}
