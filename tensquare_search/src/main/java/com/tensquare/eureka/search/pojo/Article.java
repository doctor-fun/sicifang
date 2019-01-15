package com.tensquare.eureka.search.pojo;



import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

//数据库一行相当于一个文档
//indexName文档属于哪一个索引（数据库），哪个表type
@Document(indexName = "tensquare_article",type = "article")
public class Article implements Serializable {
    @Id
    private String id;//ID

    //Field是域，对应数据库中的列，index表示是否索引，确定该域能否被搜索
    //是否匹配，搜索时候整体匹配还是单词匹配//要分词的话要指定分词器
    //是否存储，就是是否在页面显示，搜索时会整体显示在页面上
    //content文章内容分词后保存，用的是analyzer,搜索时用的分词器searchAnalyzer
    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;//标题

    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String content;//文章正文

    private String state;//审核状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}