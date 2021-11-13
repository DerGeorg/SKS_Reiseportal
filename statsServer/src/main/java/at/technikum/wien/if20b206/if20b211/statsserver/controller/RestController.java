package at.technikum.wien.if20b206.if20b211.statsserver.controller;

import at.technikum.wien.if20b206.if20b211.statsserver.data.StatsRepo;
import at.technikum.wien.if20b206.if20b211.statsserver.entity.ArticlesEntity;
import at.technikum.wien.if20b206.if20b211.statsserver.entity.StatsEntity;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(value = "http://localhost:8081")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private StatsRepo statsRepo;

    @Operation(summary = "Add article")
    @RequestMapping(value = "/article/{id}", method = RequestMethod.POST)
    private void addArticleController(@PathVariable int id, @RequestParam Long sightseeingID){
        addArticle(id, sightseeingID);
    }

    private void addArticle(int id, Long sightseeingID){
        StatsEntity newStat = new StatsEntity(id, new Date(), sightseeingID);
        statsRepo.save(newStat);
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "statscreate")
    public void addArticeKafkaListener(String data){
        System.out.println("Kafka Receive: " + data);
        System.out.println(data);
        ArticlesEntity articlesEntity = new Gson().fromJson(data, ArticlesEntity.class);
        addArticle(articlesEntity.getId(), articlesEntity.getSightseeingId());
    }

    @Operation(summary = "Count +1 to given article id")
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    private ResponseEntity<String> countController(@PathVariable int id){
        return count(id);
    }

    private ResponseEntity<String> count(int id){
        if(statsRepo.findById(id).isPresent()) {
            StatsEntity statsToEdit = statsRepo.findById(id).get();
            statsToEdit.addCount();
            statsRepo.save(statsToEdit);
            return ResponseEntity.ok("");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID: " + id + " not found");
        }
    }


    @org.springframework.kafka.annotation.KafkaListener(topics = "statscount")
    private void countKafkaListener(String data){
        System.out.println("Kafka receive :");
        System.out.println(data);
        count(Integer.parseInt(data));
    }

    @Operation(summary = "Get Stats from Month")
    @RequestMapping(value = "/stats/month", method = RequestMethod.GET, produces = "application/json")
    private ResponseEntity<String> statsMonth(){
        List<StatsEntity> statsEntityList = new ArrayList<>();
        Date date = new Date();
        date.setMonth(new Date().getMonth() - 1);
        statsRepo.findAll().forEach(statsEntity -> {
            if(statsEntity.getPubDate().after(date)) {
                statsEntityList.add(statsEntity);
            }
        });
        if (statsEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Entry in the last month");
        }
        return ResponseEntity.ok(new Gson().toJson(statsEntityList));
    }

    @Operation(summary = "Get all Stats")
    @RequestMapping(value = "/stats/all", method = RequestMethod.GET, produces = "application/json")
    private ResponseEntity<String> statsGes(){
        List<StatsEntity> statsEntityList = new ArrayList<>();
        statsRepo.findAll().forEach(statsEntityList::add);
        if (statsEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("DB empty");
        }
        return ResponseEntity.ok(new Gson().toJson(statsEntityList));
    }

    @Operation(summary = "Get Stats from one Article")
    @RequestMapping(value = "/stat/{id}", method = RequestMethod.GET, produces = "application/json")
    private ResponseEntity<String> statsSingle(@PathVariable int id){
        if(statsRepo.findById(id).isPresent()) {
            StatsEntity stats = statsRepo.findById(id).get();
            return ResponseEntity.ok(new Gson().toJson(stats));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID: "+id+" not found");
        }
    }
}
