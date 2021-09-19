package at.technikum.wien.if20b206.if20b211.statsserver.controller;

import at.technikum.wien.if20b206.if20b211.statsserver.data.StatsRepo;
import at.technikum.wien.if20b206.if20b211.statsserver.entity.StatsEntity;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private StatsRepo statsRepo;

    @Operation(summary = "Count +1 to given article id")
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    private ResponseEntity<String> count(@PathVariable int id){
        if(statsRepo.findById(id).isPresent()) {
            StatsEntity statsToEdit = statsRepo.findById(id).get();
            statsToEdit.addCount();
            statsRepo.save(statsToEdit);
            return ResponseEntity.ok("");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID: " + id + " not found");
        }
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