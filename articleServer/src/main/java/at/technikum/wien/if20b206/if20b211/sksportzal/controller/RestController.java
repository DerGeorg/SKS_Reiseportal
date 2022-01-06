package at.technikum.wien.if20b206.if20b211.sksportzal.controller;


import at.technikum.wien.if20b206.if20b211.sksportzal.data.ArticlesRepo;
import at.technikum.wien.if20b206.if20b211.sksportzal.entity.ArticlesEntity;
import at.technikum.wien.if20b206.if20b211.sksportzal.kafka.KafkaSender;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(value = "*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final String KEY_STATSCREATE = "statscreate";
    private final String KEY_STATSCOUNT = "statscount";

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private ArticlesRepo articlesRepo;

    @Operation(summary = "Gets all articles on the Article Server")
    @RequestMapping(value = "/articles", method = RequestMethod.GET, produces = "application/json")
    public String getArticles(){
        assert articlesRepo.findAll() != null;
        return new Gson().toJson(articlesRepo.findAll());
    }

    @Operation(summary = "Get one Article by id")
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getArticle(@PathVariable Integer id, @RequestParam (required = false) boolean doNotCount){
        ArticlesEntity articlesEntity = articlesRepo.findById(id).get();
        if(!doNotCount)
            kafkaSender.sendMessage(id.toString(), KEY_STATSCOUNT);
        return new Gson().toJson(articlesEntity);
    }

    @Operation(summary = "Create article")
    @RequestMapping(value = "/article", method = RequestMethod.POST, produces = "application/json")
    private ResponseEntity<String> addArticle(@RequestParam String title, @RequestParam String text, @RequestParam String author, @RequestParam Long sightseeingID){
        ArticlesEntity articlesEntity = new ArticlesEntity(title, text, author, sightseeingID);
        articlesRepo.save(articlesEntity);
        kafkaSender.sendMessage(new Gson().toJson(articlesEntity), KEY_STATSCREATE);
        return ResponseEntity.ok(new Gson().toJson(articlesEntity));
    }
}
