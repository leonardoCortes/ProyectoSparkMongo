package com.spark;

import com.google.gson.Gson;
import com.spark.model.Blog;
import com.spark.service.UserService;

import static spark.Spark.get;
import static spark.Spark.post;

public class Api {


    public static UserService userService = new UserService();

    public static void main(String[] args) {
        final Gson gson = new Gson();

        post("/add-post", (req, res)->{
            res.type("application/json");
            Blog blog = gson.fromJson(req.body(), Blog.class);
            return userService.addPost(blog);
        }, gson ::toJson);

        get("/", (req,res)->{
            res.type("application/json");
            return userService.getAllPost();
        }, gson ::toJson);

        //Get post by other name
        get("/:userName", (req, res)->{
            res.type("application/json");
            Blog blog = userService.getPostByName(req.params("userName"));
            if(blog != null){
                return blog;
            }else {
                return "No post Found";
            }
        }, gson ::toJson);
    }
}
