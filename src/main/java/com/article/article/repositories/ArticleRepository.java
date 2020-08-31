package com.article.article.repositories;

import com.article.article.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findAll();
}