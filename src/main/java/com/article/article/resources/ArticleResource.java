package com.article.article.resources;

import antlr.ASTNULLType;
import antlr.collections.AST;
import com.article.article.dto.ArticleDto;
import com.article.article.model.Article;
import com.article.article.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import sun.net.www.MimeTable;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Path("/articles")
public class ArticleResource {

    @Autowired
    private ArticleRepository articleRepository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleDetails() {
        List<Article> all = articleRepository.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleById(@PathParam("id") Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent())
            return Response.ok(article).build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArticle(ArticleDto article) {
        Article savedObject = articleRepository.save(new Article(article.getTitle(), article.getBody()));
        return Response.status(Response.Status.CREATED).entity(savedObject).build();
    }

    @PUT
    @Path("/{id}/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateArticle(@PathParam("id") long id,Article input) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()){
            Article fetchArticle = article.get();
            fetchArticle.setTitle(input.getTitle());
            fetchArticle.setBody(input.getBody());
            articleRepository.save(fetchArticle);
            return Response.ok(fetchArticle).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteArticle(@PathParam("id") Long id) {
        articleRepository.deleteById(id);
        return Response.noContent().build();
    }
}

