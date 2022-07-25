package mainpackage.controller;

import mainpackage.model.Article;
import mainpackage.model.ArticleResponse;
import mainpackage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping(value="/article_post",consumes = "application/json",produces = "application/json")
    public ResponseEntity<ArticleResponse>  article_post(@RequestBody Article article)
    {
        ArticleResponse articleResponse = articleService.submit(article);

        return  new ResponseEntity<ArticleResponse>(articleResponse, HttpStatus.OK);
    }
}
