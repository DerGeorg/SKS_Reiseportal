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
    @Scheduled(cron = "1 12 11 20 * ?")
    private void checkMoney(){
        statsRepo.findAll().forEach(statsEntity -> { // /api/stats/all get all stats --> for each diese steps
            if(articlesRepo.findById(statsEntity.getId()).isPresent()) {
                ArticlesEntity articlesEntity = articlesRepo.findById(statsEntity.getId()).get(); //zu jedem dieser stats holt er sich den article, mit der article id von statsEntity
                String articleTitle = articlesEntity.getTitle(); //holt title
                String author = articlesEntity.getAuthor(); //holt author

                System.out.println("Author: " + author + " gets: " + statsEntity.getCount() + " for the article named: " + articleTitle); //gibts aus

                statsEntity.setCount(0); //counter in stats wieder auf 0 setzten
                statsRepo.save(statsEntity); //stats in db aktualisieren
            }
        });
    }
}
