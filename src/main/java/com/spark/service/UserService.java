package com.spark.service;


import com.mongodb.MongoClient;
import com.spark.model.Blog;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public class UserService {

    MongoClient client = new MongoClient("localhost", 27017);
    Datastore datastore = new Morphia().createDatastore(client, "blog");

    public UserService()  {
    }

    public String addPost(Blog blog){
        datastore.save(blog);
        return "add post";
    }

    public List<Blog> getAllPost(){
        List<Blog> list = datastore.find(Blog.class).asList();
        if(list != null ){
            return list;
        }
        return null;
    }

    public Blog getPostByName(String userName){
        Blog blog = datastore.find(Blog.class, "oth", userName).get();
        if(blog != null){
            return blog;
        }else {
            return null;
        }
    }
}
