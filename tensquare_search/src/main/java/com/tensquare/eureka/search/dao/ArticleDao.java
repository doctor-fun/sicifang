package com.tensquare.eureka.search.dao;

import com.tensquare.eureka.search.pojo.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleDao extends ElasticsearchRepository<Article,String> {
    public Page<Article> findByTitleOrContentLike(String title, String content,
                                                  Pageable pageable);
    //这里表示title,content两个内容进行搜索
}