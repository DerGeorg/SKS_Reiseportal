package at.technikum.wien.if20b206.if20b211.sksportzal.controller;


import at.technikum.wien.if20b206.if20b211.sksportzal.data.ArticlesRepo;
import at.technikum.wien.if20b206.if20b211.sksportzal.entity.ArticlesEntity;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private ArticlesRepo articlesRepo;

    @Operation(summary = "Gets all articles on the Article Server")
    @RequestMapping(value = "/articles", method = RequestMethod.GET, produces = "application/json")
    public String getArticles(){
        return new Gson().toJson(articlesRepo.findAll());
    }

    @Operation(summary = "Get one Article by id")
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getArticle(@PathVariable Integer id){
        ArticlesEntity articlesEntity = articlesRepo.findById(id).get();
        return new Gson().toJson(articlesEntity);
    }
}
