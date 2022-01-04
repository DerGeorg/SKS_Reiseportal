package at.technikum.wien.if20b206.if20b211.sightseeingserver.controller;

import at.technikum.wien.if20b206.if20b211.sightseeingserver.data.SightseeingRepo;
import at.technikum.wien.if20b206.if20b211.sightseeingserver.entity.SightseeingEntity;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(value = "*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private SightseeingRepo sightseeingRepo;

    @Operation(summary = "Get all Sightseeing")
    @RequestMapping(value = "/sightseeings", method = RequestMethod.GET, produces = "application/json")
    private ResponseEntity<String> getAllSightseeings(){
        List<SightseeingEntity> sightseeingEntityList = new ArrayList<>();
        sightseeingRepo.findAll().forEach(sightseeingEntityList::add);
        if (sightseeingEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("DB empty");
        }
        return ResponseEntity.ok(new Gson().toJson(sightseeingEntityList));
    }

    @Operation(summary = "Get one Sightseeing by id")
    @RequestMapping(value = "/sightseeing/{id}", method = RequestMethod.GET, produces = "application/json")
    private ResponseEntity<String> getSightseeingById(@PathVariable int id){
        if(sightseeingRepo.findById(id).isPresent()) {
            SightseeingEntity sightseeingEntity = sightseeingRepo.findById(id).get();
            return ResponseEntity.ok(new Gson().toJson(sightseeingEntity));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID: "+id+" not found");
        }
    }

}
