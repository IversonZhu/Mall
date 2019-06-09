package com.iverson.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description: blog
 * <p>Blog控制器
 * Created by Iverson on 2019/6/9 23:04
 */
@Controller
@RequestMapping("/blogs")
@Slf4j
public class BlogController {

    @GetMapping
    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order, @RequestParam(value = "keyword", required = false) String keyword){
        log.info("order:" + order + ",keyword:" + keyword);
        return "redirect/index/order=" + order + "&tag=" + keyword;
    }
}
