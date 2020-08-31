package com.article.article.config;

import com.article.article.resources.ArticleResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class RestConfig extends ResourceConfig {
    public RestConfig() {

        register(ArticleResource.class);
    }
}