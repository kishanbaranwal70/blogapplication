package mainpackage.service;

import mainpackage.model.Article;
import mainpackage.model.ArticleResponse;
import mainpackage.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public ArticleResponse submit(Article article){
        Article newArticle =  articleRepository.save(article);
        ArticleResponse articleResponse =  new ArticleResponse();
        if(newArticle == null){
            articleResponse.setStatus(false);
            articleResponse.setMessage("Article failed");
        }

        else{
            articleResponse.setStatus(true);
            articleResponse.setMessage("Article added successfully");
        }
        System.out.println(articleResponse.getMessage());
        return articleResponse;
    }
}
