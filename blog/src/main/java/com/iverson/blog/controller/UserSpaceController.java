package com.iverson.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description: blog
 * <p> 用户主页控制器
 * Created by Iverson on 2019/6/9 23:18
 */
@Controller
@RequestMapping("user")
@Slf4j
public class UserSpaceController {

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username){
        log.info("username:" + username);
        return "/userspace/user";
    }

    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
                                   @RequestParam(value = "category", required = false) Long category,
                                   @RequestParam(value="keyword",required=false ) String keyword){
        if (category != null) {
            log.info("category:" + category);
            log.info("selflink:" + "redirect:/u/"+ username +"/blogs?category="+category);
            return "/user";

        } else if (keyword != null && keyword.isEmpty() == false) {

            log.info("keyword:" +keyword);
            log.info("selflink:" + "redirect:/u/"+ username +"/blogs?keyword="+keyword);
            return "/user";
        }
        log.info("order:" +order);
        log.info("selflink:" + "redirect:/u/"+ username +"/blogs?order="+order);
        return "/user";
    }
}
